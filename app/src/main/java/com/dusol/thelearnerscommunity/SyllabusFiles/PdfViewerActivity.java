package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dusol.thelearnerscommunity.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PdfViewerActivity extends AppCompatActivity {

    public static final String EXTRA_PDF_URL = "extra_pdf_url";
    public static final String EXTRA_PDF_TITLE = "extra_pdf_title";

    // Existing fields
    private PDFView pdfView;
    private String pdfUrl;
    private String pdfTitle;

    // New fields
    private LinearLayout topBar, bottomBar, loadingLayout, errorLayout;
    private View progressFill, progressTrack;
    private TextView tvPageIndicator, tvLoadingText, tvErrorMessage, tvTitle;
    private SeekBar pageSeekBar;
    private ImageButton btnNightMode, btnBack, btnMenu, btnPrevPage, btnNextPage;
    private Button btnRetry;

    private boolean barsVisible = true;
    private boolean nightModeOn = false;
    private int currentPage = 0;
    private int totalPages = 0;

    private final Handler pageIndicatorHandler = new Handler(Looper.getMainLooper());
    private final Runnable hidePageIndicator = () ->
            tvPageIndicator.animate().alpha(0f).setDuration(250)
                    .withEndAction(() -> tvPageIndicator.setVisibility(View.INVISIBLE)).start();

    private float lastTouchY = 0f;
    private static final int SCROLL_THRESHOLD_DP = 12;
    private float scrollThresholdPx;

    private static final String PREFS_NAME = "pdf_last_page";
    
    private PdfDownloadHelper downloadHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        pdfUrl = getIntent().getStringExtra(EXTRA_PDF_URL);
        pdfTitle = getIntent().getStringExtra(EXTRA_PDF_TITLE);

        if (pdfUrl == null || (!pdfUrl.startsWith("http://") && !pdfUrl.startsWith("https://"))) {
            Toast.makeText(this, "Invalid PDF link.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Prevent screenshots and screen recording for security
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        // 1. Set layout
        setContentView(R.layout.activity_pdf_viewer);

        // 2. Bind all views by ID
        bindViews();

        // 3. Set toolbar title from intent extra
        tvTitle.setText(pdfTitle != null ? pdfTitle : "Notes");

        // 4. Status bar colour — match toolbar (use WindowCompat or Window.setStatusBarColor)
        setStatusBarColor();

        // 5. Back button
        btnBack.setOnClickListener(v -> onBackPressed());

        // 6. Night mode toggle
        btnNightMode.setOnClickListener(v -> toggleNightMode());

        // 7. Overflow menu
        btnMenu.setOnClickListener(v -> showOverflowMenu(v));

        // 8. Prev / Next buttons
        btnPrevPage.setOnClickListener(v -> {
            if (currentPage > 0) pdfView.jumpTo(currentPage - 1, true);
        });
        btnNextPage.setOnClickListener(v -> {
            if (currentPage < totalPages - 1) pdfView.jumpTo(currentPage + 1, true);
        });

        // 9. Seekbar listener
        pageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) pdfView.jumpTo(progress, true);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 10. Scroll threshold in px
        scrollThresholdPx = SCROLL_THRESHOLD_DP * getResources().getDisplayMetrics().density;

        // 11. Start PDF download + load
        downloadHelper = new PdfDownloadHelper();
        startPdfLoad();
    }

    private void bindViews() {
        pdfView = findViewById(R.id.pdfView);
        topBar = findViewById(R.id.topBar);
        bottomBar = findViewById(R.id.bottomBar);
        loadingLayout = findViewById(R.id.loadingLayout);
        errorLayout = findViewById(R.id.errorLayout);
        
        progressFill = findViewById(R.id.progressFill);
        progressTrack = findViewById(R.id.progressTrack);
        
        tvPageIndicator = findViewById(R.id.tvPageIndicator);
        tvLoadingText = findViewById(R.id.tvLoadingText);
        tvErrorMessage = findViewById(R.id.tvErrorMessage);
        tvTitle = findViewById(R.id.tvTitle);
        
        pageSeekBar = findViewById(R.id.pageSeekBar);
        
        btnNightMode = findViewById(R.id.btnNightMode);
        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.btnMenu);
        btnPrevPage = findViewById(R.id.btnPrevPage);
        btnNextPage = findViewById(R.id.btnNextPage);
        btnRetry = findViewById(R.id.btnRetry);
    }
    
    private void setStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // Using same color as toolbar_bg #FFFFFF
        window.setStatusBarColor(0xFFFFFFFF);
        // Make sure status bar icons are dark since background is white
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void startPdfLoad() {
        showState(STATE_LOADING);
        tvLoadingText.setText("Opening PDF…");

        downloadHelper.download(this, pdfUrl, new PdfDownloadHelper.DownloadCallback() {
            @Override
            public void onProgress(int percent) {
                runOnUiThread(() ->
                        tvLoadingText.setText(percent < 100
                                ? "Downloading… " + percent + "%"
                                : "Rendering PDF…")
                );
            }
            @Override
            public void onSuccess(File file) {
                runOnUiThread(() -> loadPdfFromFile(file));
            }
            @Override
            public void onFailure(String message) {
                runOnUiThread(() -> {
                    tvErrorMessage.setText(message);
                    showState(STATE_ERROR);
                });
            }
        });

        btnRetry.setOnClickListener(v -> startPdfLoad());
    }

    private void loadPdfFromFile(File file) {
        int savedPage = getSavedPage();

        pdfView.setTag(file);

        pdfView.fromFile(file)
                .defaultPage(savedPage)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAnnotationRendering(false)
                .spacing(8)                          // 8dp gap between pages
                .pageSnap(false)                     // free scroll, not per-page snap
                .autoSpacing(false)
                .nightMode(false) // Never invert PDF colors, just change UI
                .onLoad(nbPages -> {
                    totalPages = nbPages;
                    pageSeekBar.setMax(Math.max(0, nbPages - 1));
                    pageSeekBar.setProgress(savedPage);
                    showState(STATE_PDF);
                    updatePageIndicator(savedPage, nbPages);
                    updateProgressBar(savedPage, nbPages);
                })
                .onPageChange((page, pageCount) -> {
                    currentPage = page;
                    pageSeekBar.setProgress(page);
                    updatePageIndicator(page, pageCount);
                    updateProgressBar(page, pageCount);
                })
                .onPageScroll((page, positionOffset) -> {
                    // positionOffset is not scroll direction — use onTouchListener for that (see below)
                })
                .onError(t -> {
                    tvErrorMessage.setText("Could not render this PDF. Please try again.");
                    showState(STATE_ERROR);
                })
                .onTap(e -> {
                    toggleBars();
                    return true;
                })
                .load();
    }

    private void setBarsVisible(boolean visible) {
        barsVisible = visible;
        
        topBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        bottomBar.setVisibility(visible ? View.VISIBLE : View.GONE);

        // Immersive mode — hide/show system status bar with bars
        if (!visible) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void toggleBars() {
        setBarsVisible(!barsVisible);
    }

    private void updatePageIndicator(int page, int total) {
        tvPageIndicator.setText((page + 1) + " / " + total);
        tvPageIndicator.setAlpha(1f);
        tvPageIndicator.setVisibility(View.VISIBLE);

        // Cancel any pending fade and restart 2-second timer
        pageIndicatorHandler.removeCallbacks(hidePageIndicator);
        pageIndicatorHandler.postDelayed(hidePageIndicator, 2000);
    }

    private void updateProgressBar(int page, int total) {
        if (total <= 0) return;
        // progressFill width = (page+1/total) * parent width
        // Use ViewTreeObserver to get parent width safely
        progressTrack.post(() -> {
            int trackWidth = progressTrack.getWidth();
            int fillWidth = (int) (trackWidth * ((page + 1f) / total));
            ViewGroup.LayoutParams params = progressFill.getLayoutParams();
            params.width = fillWidth;
            progressFill.setLayoutParams(params);
        });
    }

    private void toggleNightMode() {
        nightModeOn = !nightModeOn;
        btnNightMode.setImageResource(nightModeOn
                ? R.drawable.ic_day_mode
                : R.drawable.ic_night_mode);

        // Change UI colors without reloading PDF
        int bgColor = android.graphics.Color.parseColor(nightModeOn ? "#121212" : "#F5F5F5");
        int toolbarColor = android.graphics.Color.parseColor(nightModeOn ? "#1E1E1E" : "#FFFFFF");
        int textColor = android.graphics.Color.parseColor(nightModeOn ? "#FFFFFF" : "#1A1A1A");
        
        findViewById(R.id.mainContainer).setBackgroundColor(bgColor);
        topBar.setBackgroundColor(toolbarColor);
        bottomBar.setBackgroundColor(toolbarColor);
        tvTitle.setTextColor(textColor);
        
        // Update icon tints
        btnBack.setColorFilter(textColor);
        btnNightMode.setColorFilter(textColor);
        btnMenu.setColorFilter(textColor);
        btnPrevPage.setColorFilter(textColor);
        btnNextPage.setColorFilter(textColor);
    }

    private void showOverflowMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenu().add(0, 1, 0, nightModeOn ? "Day mode" : "Night mode");
        popup.getMenu().add(0, 2, 0, "Go to page…");

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == 1) { toggleNightMode(); return true; }
            if (item.getItemId() == 2) { showGoToPageDialog(); return true; }
            return false;
        });
        popup.show();
    }

    private void showGoToPageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Go to page");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("1 – " + totalPages);
        builder.setView(input);

        builder.setPositiveButton("Go", (dialog, which) -> {
            String text = input.getText().toString().trim();
            if (!text.isEmpty()) {
                int page = Integer.parseInt(text) - 1; // convert to 0-indexed
                if (page >= 0 && page < totalPages) {
                    pdfView.jumpTo(page, true);
                } else {
                    Toast.makeText(this, "Page must be between 1 and " + totalPages, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private String getPagePrefsKey() {
        // Key = MD5 hash of URL (same approach as PdfDownloadHelper's cache key)
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(pdfUrl.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return "page_" + hex;
        } catch (Exception e) {
            return "page_" + pdfUrl.hashCode();
        }
    }

    private int getSavedPage() {
        return getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getInt(getPagePrefsKey(), 0);
    }

    private void saveCurrentPage() {
        if (currentPage > 0) { // don't save page 0 — that's the default
            getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
                    .putInt(getPagePrefsKey(), currentPage)
                    .apply();
        }
    }

    @Override
    public void onBackPressed() {
        saveCurrentPage();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveCurrentPage();
    }

    private static final int STATE_LOADING = 0;
    private static final int STATE_PDF     = 1;
    private static final int STATE_ERROR   = 2;

    private void showState(int state) {
        loadingLayout.setVisibility(state == STATE_LOADING ? View.VISIBLE : View.GONE);
        pdfView.setVisibility(state == STATE_PDF ? View.VISIBLE : View.INVISIBLE);
        progressTrack.setVisibility(state == STATE_PDF ? View.VISIBLE : View.GONE);
        progressFill.setVisibility(state == STATE_PDF ? View.VISIBLE : View.GONE);
        errorLayout.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pageIndicatorHandler.removeCallbacks(hidePageIndicator);
        if (downloadHelper != null) downloadHelper.shutdown(); // Prevent ExecutorService thread leak
        if (pdfView != null) pdfView.recycle();
        // Restore status bar visibility if it was hidden
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
