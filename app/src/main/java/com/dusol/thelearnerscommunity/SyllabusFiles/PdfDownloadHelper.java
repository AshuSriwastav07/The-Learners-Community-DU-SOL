package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Downloads PDFs from remote URLs and caches them locally in app-internal storage.
 * <p>
 * Cache key: MD5 hash of URL → guarantees same URL always maps to same file.
 * Cache location: context.getCacheDir()/pdf_cache/ — no storage permissions needed.
 * Cache limit: ~150 MB — oldest files deleted first after each successful download.
 */
public class PdfDownloadHelper {

    private static final String TAG = "PdfDownloadHelper";
    private static final String CACHE_DIR_NAME = "pdf_cache";
    private static final int CONNECT_TIMEOUT_MS = 15_000;
    private static final int READ_TIMEOUT_MS = 20_000;
    private static final long MAX_CACHE_SIZE_BYTES = 150L * 1024 * 1024; // 150 MB
    private static final int BUFFER_SIZE = 8192;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final AtomicBoolean cancelled = new AtomicBoolean(false);

    /**
     * Callback interface for download progress and result.
     */
    public interface DownloadCallback {
        void onProgress(int percent);
        void onSuccess(File file);
        void onFailure(String errorMessage);
    }

    /**
     * Downloads the PDF at the given URL, or returns a cached copy if available.
     *
     * @param context  Application or Activity context (for getCacheDir())
     * @param pdfUrl   The remote PDF URL (e.g. Cloudinary)
     * @param callback Receives progress/success/failure on the main thread
     */
    public void download(Context context, String pdfUrl, DownloadCallback callback) {
        cancelled.set(false);

        executor.execute(() -> {
            try {
                File cacheDir = getCacheDirectory(context);
                String fileName = md5(pdfUrl) + ".pdf";
                File cachedFile = new File(cacheDir, fileName);

                // Return cached copy if it exists and is non-empty
                if (cachedFile.exists() && cachedFile.length() > 0) {
                    Log.d(TAG, "Cache hit: " + cachedFile.getAbsolutePath());
                    postSuccess(callback, cachedFile);
                    return;
                }

                // Download from network
                Log.d(TAG, "Downloading: " + pdfUrl);
                downloadToFile(pdfUrl, cachedFile, callback);

                if (cancelled.get()) {
                    // Clean up partial file
                    cachedFile.delete();
                    return;
                }

                // Enforce cache size limit
                enforceCacheSizeLimit(cacheDir, cachedFile);

                postSuccess(callback, cachedFile);

            } catch (IOException e) {
                Log.e(TAG, "Download failed", e);
                if (!cancelled.get()) {
                    postFailure(callback, "Download failed: " + e.getMessage());
                }
            } catch (Exception e) {
                Log.e(TAG, "Unexpected error", e);
                if (!cancelled.get()) {
                    postFailure(callback, "An unexpected error occurred.");
                }
            }
        });
    }

    /**
     * Cancel any in-progress download.
     */
    public void cancel() {
        cancelled.set(true);
    }

    /**
     * Shut down the executor. Call in Activity.onDestroy().
     */
    public void shutdown() {
        cancel();
        executor.shutdownNow();
    }

    // ---- Private helpers ----

    private void downloadToFile(String pdfUrl, File outputFile, DownloadCallback callback) throws IOException {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            URL url = new URL(pdfUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(CONNECT_TIMEOUT_MS);
            connection.setReadTimeout(READ_TIMEOUT_MS);
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Server returned HTTP " + responseCode);
            }

            int contentLength = connection.getContentLength();
            inputStream = connection.getInputStream();
            outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[BUFFER_SIZE];
            long totalBytesRead = 0;
            int bytesRead;
            int lastReportedProgress = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                if (cancelled.get()) {
                    return;
                }
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                if (contentLength > 0) {
                    int progress = (int) (totalBytesRead * 100 / contentLength);
                    if (progress != lastReportedProgress) {
                        lastReportedProgress = progress;
                        postProgress(callback, progress);
                    }
                }
            }

            outputStream.flush();

        } finally {
            if (outputStream != null) {
                try { outputStream.close(); } catch (IOException ignored) {}
            }
            if (inputStream != null) {
                try { inputStream.close(); } catch (IOException ignored) {}
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private File getCacheDirectory(Context context) {
        File cacheDir = new File(context.getCacheDir(), CACHE_DIR_NAME);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    /**
     * If total cache exceeds MAX_CACHE_SIZE_BYTES, delete oldest-modified files
     * until under the limit. Never deletes the file that was just downloaded.
     */
    private void enforceCacheSizeLimit(File cacheDir, File justDownloaded) {
        File[] files = cacheDir.listFiles();
        if (files == null || files.length == 0) return;

        long totalSize = 0;
        for (File f : files) {
            totalSize += f.length();
        }

        if (totalSize <= MAX_CACHE_SIZE_BYTES) return;

        // Sort by last modified (oldest first)
        Arrays.sort(files, (a, b) -> Long.compare(a.lastModified(), b.lastModified()));

        for (File f : files) {
            if (totalSize <= MAX_CACHE_SIZE_BYTES) break;
            if (f.equals(justDownloaded)) continue; // Don't delete the file we just wrote

            long fileSize = f.length();
            if (f.delete()) {
                totalSize -= fileSize;
                Log.d(TAG, "Cache cleanup: deleted " + f.getName());
            }
        }
    }

    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // MD5 is always available on Android; fallback just in case
            return String.valueOf(input.hashCode());
        }
    }

    private void postProgress(DownloadCallback callback, int percent) {
        mainHandler.post(() -> callback.onProgress(percent));
    }

    private void postSuccess(DownloadCallback callback, File file) {
        mainHandler.post(() -> callback.onSuccess(file));
    }

    private void postFailure(DownloadCallback callback, String message) {
        mainHandler.post(() -> callback.onFailure(message));
    }
}
