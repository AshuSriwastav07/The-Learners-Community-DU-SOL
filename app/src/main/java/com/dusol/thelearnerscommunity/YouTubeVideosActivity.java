package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity that fetches and displays all YouTube videos from
 * The Learners Community DU SOL channel using YouTube Data API v3.
 */
public class YouTubeVideosActivity extends BaseActivity {

    private static final String TAG = "YouTubeVideosActivity";
    public static final String API_KEY = "YOUTUBE_API_KEY_REMOVED_FROM_HISTORY";
    public static final String CHANNEL_HANDLE = "@TheLearnersCommunityDUSOL";
    private static final int MAX_RESULTS_PER_PAGE = 50;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView errorText;

    private final List<YouTubeVideoItem> videoList = new ArrayList<>();
    private YouTubeVideoAdapter adapter;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_videos);

        // Initialize views
        recyclerView = findViewById(R.id.youtubeVideosRecyclerView);
        progressBar = findViewById(R.id.youtubeVideosProgressBar);
        errorText = findViewById(R.id.youtubeVideosErrorText);
        ImageButton backButton = findViewById(R.id.ytBackButton);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new YouTubeVideoAdapter(this, videoList);
        recyclerView.setAdapter(adapter);

        // Back button
        backButton.setOnClickListener(v -> finish());

        // Setup bottom navigation bar
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavBar);
        BottomNavHelper.setup(this, bottomNav, R.id.nav_videos);

        // Fetch videos
        fetchChannelVideos();
    }

    /**
     * Step 1: Resolve channel handle → channel ID → uploads playlist ID
     * Step 2: Fetch all videos from the uploads playlist with pagination
     */
    private void fetchChannelVideos() {
        showLoading();
        YouTubeApiHelper.fetchVideos(YouTubeVideosActivity.this, Integer.MAX_VALUE, new YouTubeApiHelper.YouTubeVideoCallback() {
            @Override
            public void onSuccess(List<YouTubeVideoItem> videos) {
                if (videos.isEmpty()) {
                    showError("No videos found on this channel.");
                } else {
                    videoList.clear();
                    videoList.addAll(videos);
                    adapter.notifyDataSetChanged();
                    showContent();
                }
            }

            @Override
            public void onError(String error) {
                showError(error);
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
    }

    private void showContent() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.GONE);
    }

    private void showError(String message) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
