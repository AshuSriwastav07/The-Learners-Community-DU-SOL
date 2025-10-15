/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */

// Import necessary Android libraries and packages
package com.dusol.thelearnerscommunity;

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


public class QP_Web_Page_MainActivity extends AppCompatActivity {

    // Declare WebView and ProgressBar variables
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE); //Prevent Taking Screen Shot

        setContentView(R.layout.activity_qp_web_page_main);

        // Set up modern back press handling
        setupBackPressHandler();

//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE
//        );

        // Initialize WebView and ProgressBar from the layout XML
        webView = findViewById(R.id.qpWebView);
        progressBar = findViewById(R.id.progress_bar);

        // Set a WebViewClient to handle web page loading inside the WebView
        webView.setWebViewClient(new WebViewClient());

        // Enable JavaScript and other settings in the WebView
        webView.getSettings().setJavaScriptEnabled(true);

        // Get the URL link to be displayed from the intent
        String link = getIntent().getStringExtra("link");

        // Set up WebView to show progress using a ProgressBar
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                // Update the progress bar
                progressBar.setProgress(newProgress);

                // If the page has finished loading, hide the progress bar
                if (newProgress == 100) {
                    progressBar.setVisibility(android.view.View.GONE);
                }
            }
        });

        // Check if the device has an active network connection
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
