/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.dusol.thelearnerscommunity.Network.GetNetworkDetails;
import com.dusol.thelearnerscommunity.R;

public class Syllabus_Web_MainActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_web_main);

        // Set up modern back press handling
        setupBackPressHandler();

        //Stop Taking SS
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE
//        );

        webView = findViewById(R.id.syllabus_webview);
        progressBar = findViewById(R.id.progress_bar);

        webView.setWebViewClient(new WebViewClient());

        // Enable JavaScript and other settings as needed
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDisplayZoomControls(true);


        String link = getIntent().getStringExtra("link");


        // Set up WebView to show progress
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                view.loadUrl("javascript:(function() { " +
                        "document.querySelector('nav').style.display='none'; " +
                        "})()");

                // Update the progress bar
                progressBar.setProgress(newProgress);

                // If the page has finished loading, hide the progress bar
                if (newProgress == 100) {
                    progressBar.setVisibility(android.view.View.GONE);
                }
            }
        });


        boolean networkStatus = GetNetworkDetails.isNetworkAvailable(this);

        Log.d("NetworkData", String.valueOf(networkStatus));

        if (!networkStatus) {
            // Internet is not available, show a message
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_LONG).show();
        } else {
            // Internet is available, so load the URL
            if (link != null) {
                webView.loadUrl(link);
            }
        }
    }

    private void setupBackPressHandler() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}
