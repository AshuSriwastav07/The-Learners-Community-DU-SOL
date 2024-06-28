/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dusol.thelearnerscommunity.Network.GetNetworkDetails;

public class Notes_HomeWeb_MainActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;

    @Override
    public void onBackPressed() {
        // Call finish() to close the activity when the back button is pressed
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.notes_webpage_activity_main);

        //Stop Taking SS
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE
//        );


        webView = findViewById(R.id.notes_webview);
        progressBar = findViewById(R.id.progress_bar);

        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().getBuiltInZoomControls();
        // Enable JavaScript and other settings as needed
        webView.getSettings().setJavaScriptEnabled(true);

        String link = getIntent().getStringExtra("link");

        // Set up WebView to show progress
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

        /*if (isNetworkAvailable()) {
            // Internet is available, so load the URL
            webView.loadUrl(link);
        } else {
            // Internet is not available, show a message
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }*/



    GetNetworkDetails network = new GetNetworkDetails();  //Obj Created to get network Details
    boolean networkStatus= network.isNetworkAvailable(this);

    Log.d("NetworkData", String.valueOf(networkStatus));

        if (!networkStatus) {
            // Internet is available, so load the URL
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_LONG).show();

        } else {
            // Internet is not available, show a message
            assert link != null;
            webView.loadUrl(link);

        }

    }
}
