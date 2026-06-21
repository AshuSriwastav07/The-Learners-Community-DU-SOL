package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.dusol.thelearnerscommunity.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.io.File;

/**
 * Displays a PDF natively using AndroidPdfViewer (mhiew fork).
 * <p>
 * Receives EXTRA_PDF_URL (Cloudinary URL) and EXTRA_PDF_TITLE (display name)
 * via Intent extras. Downloads the PDF in the background with progress,
 * caches it locally, and renders it with smooth zoom/swipe.
 */
public class PdfViewerActivity extends AppCompatActivity {

    public static final String EXTRA_PDF_URL = "extra_pdf_url";
    public static final String EXTRA_PDF_TITLE = "extra_pdf_title";

    private static final String TAG = "PdfViewerActivity";

    // Views
    private PDFView pdfView;
    private LinearLayout loadingContainer;
    private ProgressBar pdfProgressBar;
    private TextView progressText;
    private LinearLayout errorContainer;
    private TextView errorMessage;
    private MaterialButton retryButton;
    private TextView pageIndicator;

    // Download helper
    private PdfDownloadHelper downloadHelper;
    private String pdfUrl;
    private String pdfTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        // Extract intent extras
        pdfUrl = getIntent().getStringExtra(EXTRA_PDF_URL);
        pdfTitle = getIntent().getStringExtra(EXTRA_PDF_TITLE);

        if (pdfUrl == null || pdfUrl.isEmpty()) {
            Log.e(TAG, "No PDF URL provided");
            finish();
            return;
        }

        // Initialize views
        initViews();

        // Setup toolbar
        setupToolbar();

        // Setup back press
        setupBackPressHandler();

        // Setup retry button
        retryButton.setOnClickListener(v -> loadPdf());

        // Start loading
        downloadHelper = new PdfDownloadHelper();
        loadPdf();
    }

    private void initViews() {
        pdfView = findViewById(R.id.pdfView);
        loadingContainer = findViewById(R.id.loadingContainer);
        pdfProgressBar = findViewById(R.id.pdfProgressBar);
        progressText = findViewById(R.id.progressText);
        errorContainer = findViewById(R.id.errorContainer);
        errorMessage = findViewById(R.id.errorMessage);
        retryButton = findViewById(R.id.retryButton);
        pageIndicator = findViewById(R.id.pageIndicator);
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.pdfToolbar);
        toolbar.setTitle(pdfTitle != null ? pdfTitle : "PDF Viewer");
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupBackPressHandler() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    /**
     * Shows loading state and initiates PDF download/cache lookup.
     */
    private void loadPdf() {
        showLoadingState();

        downloadHelper.download(this, pdfUrl, new PdfDownloadHelper.DownloadCallback() {
            @Override
            public void onProgress(int percent) {
                if (isFinishing() || isDestroyed()) return;
                pdfProgressBar.setProgress(percent);
                progressText.setText("Downloading… " + percent + "%");
            }

            @Override
            public void onSuccess(File file) {
                if (isFinishing() || isDestroyed()) return;
                displayPdf(file);
            }

            @Override
            public void onFailure(String error) {
                if (isFinishing() || isDestroyed()) return;
                showErrorState(error);
            }
        });
    }

    /**
     * Loads the downloaded PDF file into the PDFView component.
     */
    private void displayPdf(File pdfFile) {
        showPdfState();

        pdfView.fromFile(pdfFile)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .pageSnap(true)
                .pageFling(true)
                .autoSpacing(true)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        // page is 0-indexed
                        pageIndicator.setText((page + 1) + " / " + pageCount);
                        pageIndicator.setVisibility(View.VISIBLE);
                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        Log.d(TAG, "PDF loaded: " + nbPages + " pages");
                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "Error loading PDF", t);
                        showErrorState("This PDF file appears to be corrupted.\nPlease try again.");
                    }
                })
                .onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {
                        Log.e(TAG, "Error on page " + page, t);
                    }
                })
                .load();
    }

    // ---- State management ----

    private void showLoadingState() {
        loadingContainer.setVisibility(View.VISIBLE);
        pdfView.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        pageIndicator.setVisibility(View.GONE);
        pdfProgressBar.setProgress(0);
        progressText.setText("Downloading…");
    }

    private void showPdfState() {
        loadingContainer.setVisibility(View.GONE);
        pdfView.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
    }

    private void showErrorState(String message) {
        loadingContainer.setVisibility(View.GONE);
        pdfView.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        pageIndicator.setVisibility(View.GONE);
        errorMessage.setText(message != null ? message
                : "Couldn't load this PDF.\nCheck your internet connection.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel any in-progress download and release resources
        if (downloadHelper != null) {
            downloadHelper.shutdown();
        }
        if (pdfView != null) {
            pdfView.recycle();
        }
    }
}
