package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.net.Uri;
import android.util.Log;

public class DriveUrlConverter {

    /**
     * Converts any Google Drive share/view URL to a direct download URL.
     *
     * Handles all common Drive URL formats:
     *   https://drive.google.com/file/d/{FILE_ID}/view
     *   https://drive.google.com/file/d/{FILE_ID}/view?usp=sharing
     *   https://drive.google.com/open?id={FILE_ID}
     *   https://drive.google.com/uc?id={FILE_ID}
     *   https://docs.google.com/document/d/{FILE_ID}/edit
     *   https://docs.google.com/presentation/d/{FILE_ID}/edit
     *   https://docs.google.com/spreadsheets/d/{FILE_ID}/edit
     *
     * Output: https://drive.google.com/uc?export=download&id={FILE_ID}&confirm=t
     * The confirm=t parameter bypasses Google's large-file virus scan warning page.
     */
    public static String toDirectDownloadUrl(String driveUrl) {
        if (driveUrl == null || driveUrl.isEmpty()) return driveUrl;

        String fileId = extractFileId(driveUrl);

        if (fileId != null && !fileId.isEmpty()) {
            return "https://drive.google.com/uc?export=download&id=" + fileId + "&confirm=t";
        }

        // Could not extract ID — return original and let downloader handle the error
        return driveUrl;
    }

    private static String extractFileId(String url) {
        try {
            // Pattern 1: /file/d/{id}/ or /document/d/{id}/ or /presentation/d/{id}/
            if (url.contains("/d/")) {
                String afterD = url.split("/d/")[1];
                return afterD.split("[/?]")[0]; // stop at /, ?, or end
            }

            // Pattern 2: ?id={id} or &id={id}
            if (url.contains("id=")) {
                Uri uri = Uri.parse(url);
                String id = uri.getQueryParameter("id");
                if (id != null && !id.isEmpty()) return id;
            }

        } catch (Exception e) {
            Log.e("DriveUrlConverter", "Failed to extract file ID from: " + url, e);
        }
        return null;
    }
}
