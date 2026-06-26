package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This Activity used to load notes in a WebView.
 * It has been repurposed as a transparent forwarding Activity to centralize link routing.
 * All incoming intents are routed through NoteLinkRouter, ensuring Drive PDFs 
 * open in the native PDF viewer instead of the broken WebView.
 */
public class Notes_HomeWeb_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String link = getIntent().getStringExtra("link");
        String name = getIntent().getStringExtra("PdfName");
        if (name == null) name = "Notes";

        com.dusol.thelearnerscommunity.SyllabusFiles.NoteLinkRouter.route(this, link, name);
        finish();
    }
}
