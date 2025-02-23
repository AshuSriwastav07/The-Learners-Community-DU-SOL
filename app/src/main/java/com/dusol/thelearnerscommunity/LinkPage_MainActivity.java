/**
 * App developed by:
 * Ashu Sriwastav
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.dusol.thelearnerscommunity.FunctionManager.functionManager;
import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStore_HomePage;
import com.dusol.thelearnerscommunity.SyllabusFiles.SyllabusTabLayoutActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class LinkPage_MainActivity extends AppCompatActivity {
    private long Timeback;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - Timeback > 2000) {
            Timeback = System.currentTimeMillis();
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            finishAffinity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_page_activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        // Check and request notification permission every time the app starts
        checkAndRequestNotificationPermission();

        // Initialize UI elements
        Button du_and_sol_notes_page = findViewById(R.id.button1_du_sol_notes_page);
        Button solUpdates = findViewById(R.id.button2_SOL_Updates);
        Button QuestionPapers = findViewById(R.id.button3_QP);
        Button SOL_Syllabus = findViewById(R.id.button4_SOL_Syllabus);
        Button sol_portal = findViewById(R.id.button5_Portal3);
        Button shop = findViewById(R.id.button6_notes_store);
        Button watch_videos = findViewById(R.id.button7_Videos);
        Button Connect_with_us = findViewById(R.id.button9_connect_us);
        Button sol_materials = findViewById(R.id.button10_SOL_Study_Material);
        ImageButton askDoubt = findViewById(R.id.askHere);

        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);

        functionManager.managerNewSignLogo(this, this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }

        // Navigation Bar Button Listeners (unchanged)
        NavVideos.setOnClickListener(view -> openYouTubeChannel());
        NavBooks.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Notes_Open", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Notes_Open", bundle);
            startActivity(new Intent(getApplicationContext(), DU_SOL_NOTES__MainActivity.class));
        });
        NavStudents.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);
            startActivity(new Intent(getApplicationContext(), studentsBoard.class));
        });

        // Text Marquee (unchanged)
        TextView textView = findViewById(R.id.LinkPageMarquee);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        DatabaseReference MarqueeText = FirebaseDatabase.getInstance().getReference("MainPageBanner");
        MarqueeText.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String TextToShow = snapshot.getValue(String.class);
                assert TextToShow != null;
                if (TextToShow.equals("N/A")) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setText(TextToShow);
                    textView.setSelected(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        // Main Buttons Listeners (unchanged)
        shop.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("Notes_Store", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("Notes_Store", bundle);
            startActivity(new Intent(getApplicationContext(), NotesStore_HomePage.class));
        });

        du_and_sol_notes_page.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Notes_Open", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Notes_Open", bundle);
            startActivity(new Intent(getApplicationContext(), DU_SOL_NOTES__MainActivity.class));
        });

        QuestionPapers.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("Question_Paper_Open", "button_clicked");
//            FirebaseAnalytics.getInstance(this).logEvent("Question_Paper_Open", bundle);
            startActivity(new Intent(this, selectCourseForQP.class));
        });

        solUpdates.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Updates", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Updates", bundle);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.sol.du.ac.in/home")));
        });

        SOL_Syllabus.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Syllabus", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Syllabus", bundle);
            startActivity(new Intent(getApplicationContext(), SyllabusTabLayoutActivity.class));
        });

        watch_videos.setOnClickListener(view -> openYouTubeChannel());

        sol_portal.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);
            startActivity(new Intent(getApplicationContext(), studentsBoard.class));
        });

        sol_materials.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);
            startActivity(new Intent(getApplicationContext(), study_materials.class));
        });

        Connect_with_us.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("Join_US", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("Join_US", bundle);
            startActivity(new Intent(getApplicationContext(), connect_with_us_MainActivity.class));
        });

        askDoubt.setOnClickListener(view -> functionManager.askDoubtHere(LinkPage_MainActivity.this));

        // Firebase FCM (unchanged)
        FirebaseMessaging.getInstance().subscribeToTopic("All_Notification")
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Subscription to topic failed");
                    }
                });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "myNotificationChannel",
                    "General Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Channel for general app notifications");
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    String token = task.getResult();
                    Log.d("FirebaseRegToken", token);
                });
    }

    // Combined method to check and request notification permission
    private void checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Permission not granted, request it every time
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                Log.d("Notification", "Permission already granted.");
                Toast.makeText(this, "Notifications are already enabled!", Toast.LENGTH_SHORT).show();
            }
        } else { // Pre-Android 13
            if (!androidx.core.app.NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                Toast.makeText(this, "Please enable notifications in settings.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, getPackageName());
                startActivity(intent);
            }
        }
    }

    // Activity result launcher for requesting notification permission
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Log.d("Notification", "Permission granted for notifications.");
                    Toast.makeText(this, "Notifications are enabled!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Notification", "Permission denied for notifications.");
                    Toast.makeText(this, "Notifications are disabled. You can enable them in settings.", Toast.LENGTH_SHORT).show();
                }
            });

    // Helper method to open YouTube channel (to avoid code repetition)
    private void openYouTubeChannel() {
        String youtubeChannelUrl = "https://www.youtube.com/@TheLearnersCommunityDUSOL/videos";
        Uri youtubeUri = Uri.parse(youtubeChannelUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);
        intent.setPackage("com.google.android.youtube");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeChannelUrl)));
        }
    }
}