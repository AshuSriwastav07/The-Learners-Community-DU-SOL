package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class NoteLinkRouter {

    // ── Link type detection ────────────────────────────────────────────────

    public static boolean isYouTubeLink(String url) {
        if (url == null) return false;
        return url.contains("youtube.com") || url.contains("youtu.be");
    }

    public static boolean isDriveLink(String url) {
        if (url == null) return false;
        return url.contains("drive.google.com") || url.contains("docs.google.com");
    }

    // Anything else = browser/sell link
    public static boolean isBrowserLink(String url) {
        return url != null && !isYouTubeLink(url) && !isDriveLink(url);
    }

    // ── Routing ────────────────────────────────────────────────────────────

    /**
     * Call this on every note item click. Handles all three link types.
     *
     * @param context       Activity or Fragment context
     * @param url           The note's link (YouTube / Drive / browser)
     * @param title         Display title — passed to PDF viewer for toolbar
     */
    public static void route(Context context, String url, String title) {
        if (url == null || url.trim().isEmpty() || url.equals("N/A")) {
            Toast.makeText(context, "This note is not available.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isYouTubeLink(url)) {
            openYouTube(context, url);
        } else if (isDriveLink(url)) {
            openInPdfViewer(context, url, title);
        } else {
            openInBrowser(context, url);
        }
    }

    // ── YouTube ────────────────────────────────────────────────────────────

    private static void openYouTube(Context context, String url) {
        try {
            // Try YouTube app first
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setPackage("com.google.android.youtube");
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // YouTube not installed — open in browser
            openInBrowser(context, url);
        }
    }

    // ── Browser ────────────────────────────────────────────────────────────

    private static void openInBrowser(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No browser found to open this link.", Toast.LENGTH_SHORT).show();
        }
    }

    // ── PDF Viewer ─────────────────────────────────────────────────────────

    private static void openInPdfViewer(Context context, String driveUrl, String title) {
        // Convert Drive share URL → direct download URL before passing to viewer
        String downloadUrl = DriveUrlConverter.toDirectDownloadUrl(driveUrl);

        // Launch the EXISTING PDF viewer activity (same one used for Syllabus)
        // Use the same Intent extra keys already defined in that activity
        Intent intent = new Intent(context, PdfViewerActivity.class);
        intent.putExtra(PdfViewerActivity.EXTRA_PDF_URL, downloadUrl);
        intent.putExtra(PdfViewerActivity.EXTRA_PDF_TITLE, title != null ? title : "Notes");
        context.startActivity(intent);
    }
}
