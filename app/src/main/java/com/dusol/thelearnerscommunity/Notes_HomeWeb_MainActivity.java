package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dusol.thelearnerscommunity.Network.GetNetworkDetails;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Notes_HomeWeb_MainActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set up modern back press handling
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.notes_webpage_activity_main);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        webView = findViewById(R.id.notes_webview);
        progressBar = findViewById(R.id.progress_bar);

        webView.setOnLongClickListener(v -> true);
        webView.setLongClickable(false);

        // Enable JavaScript and other necessary settings
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setBuiltInZoomControls(true);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); // Needed for Drive
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);


        WebView.setWebContentsDebuggingEnabled(false);

        String link = getIntent().getStringExtra("link");
        String name = getIntent().getStringExtra("PdfName");
        String path = getIntent().getStringExtra("Path");

        if (path != null) {
            Log.d("AnalyticsData", path);
        } else {
            Log.d("AnalyticsData", "Path is null");
        }
        // Set up WebChromeClient to show progress
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(android.view.View.GONE);
                }
            }
        });

        // Set up WebViewClient to handle link clicks
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();

                if (url.contains("drive.google.com")) {
                    return false;
                } else {
                    // Open other links in their respective apps or browsers
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // For older Android versions
                if (url.contains("drive.google.com")) {
                    return false;
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            }
        });

        // Check network availability before loading the link
        boolean networkStatus = GetNetworkDetails.isNetworkAvailable(this);

        if (!networkStatus) {
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_LONG).show();
        } else {
            Bundle bundle = new Bundle();
            new android.os.Handler().postDelayed(() -> {
                if (path != null && name != null)
                    if (path.contains("Question")) {
                    bundle.putString("pdf_name", "QP_" + name);
                } else {
                    bundle.putString("pdf_name", "Notes_" + name);
                }
                firebaseAnalytics.logEvent("pdf_opened", bundle);
            }, 500);  // Delay of 500 milliseconds

            if (link != null) {
                webView.loadUrl(link);
            }
        }
        }
    }

