package com.dusol.thelearnerscommunity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YouTubeApiHelper {

    private static final String TAG = "YouTubeApiHelper";
    // We get API_KEY from YouTubeVideosActivity or hardcode if needed, but it's better to pass it.
    private static final String API_KEY = YouTubeVideosActivity.API_KEY; 
    private static final String CHANNEL_HANDLE = YouTubeVideosActivity.CHANNEL_HANDLE;

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    private static final String PREFS_NAME = "YouTubeCachePrefs";
    private static final String CACHE_KEY = "CachedVideosJson";

    public interface YouTubeVideoCallback {
        void onSuccess(List<YouTubeVideoItem> videos);
        void onError(String error);
    }

    /**
     * Fetches videos from the channel.
     * @param context Application or Activity context for caching.
     * @param maxTotalVideos The maximum number of videos to fetch overall (e.g., 10 for home page, or Integer.MAX_VALUE for all).
     * @param callback Callback to return results on the main thread.
     */
    public static void fetchVideos(Context context, int maxTotalVideos, YouTubeVideoCallback callback) {
        executor.execute(() -> {
            SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String cachedJson = prefs.getString(CACHE_KEY, null);
            
            // Try to load and show cache immediately
            if (cachedJson != null) {
                try {
                    List<YouTubeVideoItem> cachedVideos = parseJsonArray(new JSONArray(cachedJson));
                    if (!cachedVideos.isEmpty()) {
                        mainHandler.post(() -> callback.onSuccess(cachedVideos));
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Failed to parse cached videos", e);
                }
            }

            try {
                // Step 1: Resolve channel ID and uploads playlist ID
                String uploadsPlaylistId = null;

                // Try forHandle first
                String forHandleUrl = "https://www.googleapis.com/youtube/v3/channels"
                        + "?part=contentDetails"
                        + "&forHandle=" + CHANNEL_HANDLE.replace("@", "%40")
                        + "&key=" + API_KEY;

                try {
                    String handleResponse = makeHttpRequest(forHandleUrl);
                    JSONObject handleJson = new JSONObject(handleResponse);
                    JSONArray items = handleJson.optJSONArray("items");
                    if (items != null && items.length() > 0) {
                        uploadsPlaylistId = items.getJSONObject(0)
                                .getJSONObject("contentDetails")
                                .getJSONObject("relatedPlaylists")
                                .getString("uploads");
                    }
                } catch (Exception e) {
                    Log.w(TAG, "forHandle lookup failed, trying search fallback", e);
                }

                // Fallback: use search API
                if (uploadsPlaylistId == null) {
                    String searchUrl = "https://www.googleapis.com/youtube/v3/search"
                            + "?part=snippet"
                            + "&q=" + CHANNEL_HANDLE
                            + "&type=channel"
                            + "&maxResults=1"
                            + "&key=" + API_KEY;

                    String searchResponse = makeHttpRequest(searchUrl);
                    JSONObject searchJson = new JSONObject(searchResponse);
                    JSONArray searchItems = searchJson.optJSONArray("items");

                    if (searchItems != null && searchItems.length() > 0) {
                        String channelId = searchItems.getJSONObject(0)
                                .getJSONObject("id")
                                .getString("channelId");

                        String channelByIdUrl = "https://www.googleapis.com/youtube/v3/channels"
                                + "?part=contentDetails"
                                + "&id=" + channelId
                                + "&key=" + API_KEY;

                        String channelByIdResponse = makeHttpRequest(channelByIdUrl);
                        JSONObject channelByIdJson = new JSONObject(channelByIdResponse);
                        JSONArray channelItems = channelByIdJson.optJSONArray("items");

                        if (channelItems != null && channelItems.length() > 0) {
                            uploadsPlaylistId = channelItems.getJSONObject(0)
                                    .getJSONObject("contentDetails")
                                    .getJSONObject("relatedPlaylists")
                                    .getString("uploads");
                        }
                    }
                }

                if (uploadsPlaylistId == null) {
                    mainHandler.post(() -> callback.onError("Unable to find the YouTube channel."));
                    return;
                }

                // Step 2: Fetch videos from the uploads playlist
                List<YouTubeVideoItem> allVideos = new ArrayList<>();
                String nextPageToken = null;

                do {
                    int resultsToFetch = Math.min(50, maxTotalVideos - allVideos.size());
                    if (resultsToFetch <= 0) break;

                    String playlistApiUrl = "https://www.googleapis.com/youtube/v3/playlistItems"
                            + "?part=snippet"
                            + "&playlistId=" + uploadsPlaylistId
                            + "&maxResults=" + resultsToFetch
                            + "&key=" + API_KEY;

                    if (nextPageToken != null) {
                        playlistApiUrl += "&pageToken=" + nextPageToken;
                    }

                    String playlistResponse = makeHttpRequest(playlistApiUrl);
                    JSONObject playlistJson = new JSONObject(playlistResponse);

                    JSONArray videoItems = playlistJson.optJSONArray("items");
                    if (videoItems != null) {
                        for (int i = 0; i < videoItems.length(); i++) {
                            if (allVideos.size() >= maxTotalVideos) break;

                            JSONObject snippet = videoItems.getJSONObject(i).getJSONObject("snippet");
                            String videoId = snippet.getJSONObject("resourceId").getString("videoId");
                            String title = snippet.getString("title");
                            String publishedAt = snippet.getString("publishedAt");
                            String thumbnailUrl = getBestThumbnail(snippet.getJSONObject("thumbnails"));

                            allVideos.add(new YouTubeVideoItem(videoId, title, thumbnailUrl, publishedAt));
                        }
                    }

                    nextPageToken = playlistJson.optString("nextPageToken", null);
                    if ("null".equals(nextPageToken)) nextPageToken = null;

                } while (nextPageToken != null && allVideos.size() < maxTotalVideos);

                // Serialize the new videos to JSON
                JSONArray newVideosJsonArray = createJsonArray(allVideos);
                String newVideosJsonString = newVideosJsonArray.toString();

                // If cache differs from newly fetched, update cache and UI
                if (!newVideosJsonString.equals(cachedJson)) {
                    prefs.edit().putString(CACHE_KEY, newVideosJsonString).apply();
                    mainHandler.post(() -> callback.onSuccess(allVideos));
                }

            } catch (Exception e) {
                Log.e(TAG, "Error fetching YouTube videos", e);
                mainHandler.post(() -> callback.onError("Failed to load videos. Please check your internet connection."));
            }
        });
    }

    private static String getBestThumbnail(JSONObject thumbnails) {
        if (thumbnails.has("high")) {
            return thumbnails.optJSONObject("high").optString("url", "");
        } else if (thumbnails.has("medium")) {
            return thumbnails.optJSONObject("medium").optString("url", "");
        } else if (thumbnails.has("default")) {
            return thumbnails.optJSONObject("default").optString("url", "");
        }
        return "";
    }

    private static List<YouTubeVideoItem> parseJsonArray(JSONArray array) throws JSONException {
        List<YouTubeVideoItem> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            list.add(new YouTubeVideoItem(
                    obj.getString("videoId"),
                    obj.getString("title"),
                    obj.getString("thumbnailUrl"),
                    obj.getString("publishedAt")
            ));
        }
        return list;
    }

    private static JSONArray createJsonArray(List<YouTubeVideoItem> list) throws JSONException {
        JSONArray array = new JSONArray();
        for (YouTubeVideoItem item : list) {
            JSONObject obj = new JSONObject();
            obj.put("videoId", item.getVideoId());
            obj.put("title", item.getTitle());
            obj.put("thumbnailUrl", item.getThumbnailUrl());
            obj.put("publishedAt", item.getPublishedAt());
            array.put(obj);
        }
        return array;
    }

    private static String makeHttpRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000); // 10 seconds timeout
        connection.setReadTimeout(10000);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder errorBody = new StringBuilder();
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                errorBody.append(errorLine);
            }
            errorReader.close();
            throw new Exception("HTTP error code: " + responseCode + ", Response: " + errorBody.toString());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();

        return response.toString();
    }
}
