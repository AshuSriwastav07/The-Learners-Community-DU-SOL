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
import android.os.Handler;
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
import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStoreTabActivity;
import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStore_HomePage;
import com.dusol.thelearnerscommunity.SyllabusFiles.SyllabusTabLayoutActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

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

        TextView currentUserNumber = findViewById(R.id.currentUserNumber);

        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);

        functionManager.managerNewSignLogo(this, this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }

        showRealisticUserCount(currentUserNumber);

        //Set Review ans connect us button name
        DatabaseReference reviewData = FirebaseDatabase.getInstance().getReference("ReviewUSNow");
        final String[] buttonName = new String[1];
        reviewData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                buttonName[0] = snapshot.getValue(String.class);
                assert buttonName[0] != null;
                if(!buttonName[0].equals("N/A")){
                    Connect_with_us.setText(buttonName[0]);
                }
                else {
                    Connect_with_us.setText(R.string.connect);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
            bundle.putString("Notes_Store", "Notes_Store_Opens");
            FirebaseAnalytics.getInstance(this).logEvent("Notes_Store", bundle);
            startActivity(new Intent(getApplicationContext(), NotesStoreTabActivity.class));
        });

        du_and_sol_notes_page.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Notes_Open", "SOL_Notes_Open");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Notes_Open", bundle);
            startActivity(new Intent(getApplicationContext(), DU_SOL_NOTES__MainActivity.class));
        });

        QuestionPapers.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("Question_Paper_Open", "Question_Paper_Open");
            FirebaseAnalytics.getInstance(this).logEvent("Question_Paper_Open", bundle);
            startActivity(new Intent(this, selectCourseForQP.class));
        });

        solUpdates.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Updates", "SOL_Updates_Opens");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Updates", bundle);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.sol.du.ac.in/home")));
        });

        SOL_Syllabus.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Syllabus", "SOL_Syllabus_Opens");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Syllabus", bundle);
            startActivity(new Intent(getApplicationContext(), SyllabusTabLayoutActivity.class));
        });

        watch_videos.setOnClickListener(view -> openYouTubeChannel());

        sol_portal.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "SOL_Portal_Opens");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);
            startActivity(new Intent(getApplicationContext(), studentsBoard.class));
        });

        sol_materials.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Materials", "SOL_Materials_Opens");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Materials", bundle);
            startActivity(new Intent(getApplicationContext(), study_materials.class));
        });

       Connect_with_us.setOnClickListener(view -> {
           if(!buttonName[0].equals("N/A")){
               reviewUsPageOpen();
           }
           else{
               Bundle bundle = new Bundle();
               bundle.putString("Join_US", "JoinUS_Opens");
               FirebaseAnalytics.getInstance(this).logEvent("Join_US", bundle);
               startActivity(new Intent(getApplicationContext(), connect_with_us_MainActivity.class));
           }

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

    private void reviewUsPageOpen(){
        String playStorePage = "https://play.google.com/store/apps/details?id=com.dusol.thelearnerscommunity";
        Uri youtubeUri = Uri.parse(playStorePage);
        Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);
        intent.setPackage("com.google.android.playstore");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(playStorePage)));
        }
    }



    //Show current user number 
    private final Handler handler = new Handler();
    private Runnable updateNumberRunnable;
    private final Random random = new Random();

    private void showRealisticUserCount(TextView textView) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("exam_status");
        databaseRef.keepSynced(true);

        final Boolean[] lastExamStatus = {null};

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean isExamOngoing = snapshot.getValue(Boolean.class);
                if (isExamOngoing == null) {
                    Log.e("isExamOngoing", "Exam status is null, keeping last state.");
                    return;
                }

                if (lastExamStatus[0] == null || !lastExamStatus[0].equals(isExamOngoing)) {
                    lastExamStatus[0] = isExamOngoing;
                    Log.d("isExamOngoing", "Exam status changed: " + isExamOngoing);

                    // Smooth Start
                    int targetUserCount = isExamOngoing ? 809 : 403;
                    startSmoothUserIncrease(textView, targetUserCount, isExamOngoing);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error fetching exam status", error.toException());
            }
        });

        startSmoothUserIncrease(textView, 403, false); // Default start with non-exam mode
    }

    // Slowly increases user count instead of jumping instantly
    private void startSmoothUserIncrease(TextView textView, int targetUserCount, boolean isExamOngoing) {
        if (updateNumberRunnable != null) {
            handler.removeCallbacks(updateNumberRunnable);
        }

        updateNumberRunnable = new Runnable() {
            private int currentUserCount = targetUserCount - random.nextInt(20) - 30; // Start lower by 30-50 users

            @Override
            public void run() {
                if (currentUserCount < targetUserCount) {
                    currentUserCount += random.nextInt(5) + 2; // Increase by 2-6 each time

                    if (currentUserCount > targetUserCount) {
                        currentUserCount = targetUserCount; // Stop at exact target
                    }

                    Log.e("isExamOngoing", "Increasing user count: " + currentUserCount);
                    textView.setText(String.format(Locale.getDefault(), "%d", currentUserCount));

                    handler.postDelayed(this, random.nextInt(500) + 500); // Update every 0.5 - 1 sec
                } else {
                    // Once smooth increase is done, start normal updates
                    startUserCountUpdater(textView, isExamOngoing, targetUserCount);
                }
            }
        };

        handler.post(updateNumberRunnable);
    }

    private void startUserCountUpdater(TextView textView, boolean isExamOngoing, int initialUserCount) {
        if (updateNumberRunnable != null) {
            handler.removeCallbacks(updateNumberRunnable);
        }

        updateNumberRunnable = new Runnable() {
            private int currentUserCount = initialUserCount; // Start with the given initial value

            @Override
            public void run() {
                int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                boolean isPeakHours = (currentHour >= 6 && currentHour <= 10) || (currentHour >= 18 && currentHour <= 23);

                // Define realistic min-max range based on exam status
                int targetMin = isExamOngoing ? (isPeakHours ? 800 : 500) : (isPeakHours ? 400 : 250);
                int targetMax = isExamOngoing ? (isPeakHours ? 1200 : 700) : (isPeakHours ? 450 : 300);

                // Ensure user count remains within range
                if (currentUserCount < targetMin) {
                    currentUserCount = targetMin;
                } else if (currentUserCount > targetMax) {
                    currentUserCount = targetMax;
                }

                // Create a more realistic fluctuation pattern
                int change = random.nextInt(6) - 2; // -2 to +3 variation (some leaving, some joining)

                if (isExamOngoing) {
                    // Exams → Mostly an increase, but some drops
                    if (random.nextInt(5) == 0) { // 1 out of 5 times, decrease
                        currentUserCount -= random.nextInt(3) + 1; // Drop by 1-3
                    } else {
                        currentUserCount += random.nextInt(4) + 1; // Increase by 1-4
                    }
                } else {
                    // Non-exam → More balanced joining/leaving
                    currentUserCount += change;
                }

                // Keep user count within realistic range
                if (currentUserCount < targetMin) currentUserCount = targetMin;
                if (currentUserCount > targetMax) currentUserCount = targetMax;

                // Log realistic user count change
                Log.e("isExamOngoing", "Current user count: " + currentUserCount);

                // Update TextView
                textView.setText(String.format(Locale.getDefault(), "%d", currentUserCount));

                textView.setOnClickListener(v ->
                        textView.setText(String.format(Locale.getDefault(), "Current Users: %d", currentUserCount))
                );

                // Randomize update interval slightly (4 to 7 seconds)
                int nextUpdate = 4000 + random.nextInt(3000);
                handler.postDelayed(this, nextUpdate);
            }
        };

        handler.post(updateNumberRunnable);
    }

}
