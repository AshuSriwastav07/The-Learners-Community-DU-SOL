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
import android.content.pm.ResolveInfo;
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

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStore_HomePage;
import com.dusol.thelearnerscommunity.SyllabusFiles.SyllabusTabLayoutActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.dusol.thelearnerscommunity.FunctionManager.functionManager;
import java.util.List;

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

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE); //Prevent taking screenshot

        setContentView(R.layout.link_page_activity_main);
        // Obtain the FirebaseAnalytics instance.

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        checkNotificationPermission();


        Button du_and_sol_notes_page = findViewById(R.id.button1_du_sol_notes_page); //button1
        Button solUpdates = findViewById(R.id.button2_SOL_Updates); //Button 2
        Button QuestionPapers = findViewById(R.id.button3_QP); //Button 3
        Button SOL_Syllabus = findViewById(R.id.button4_SOL_Syllabus); //Button 4
        Button sol_portal = findViewById(R.id.button5_Portal3); //Button 5
        Button shop = findViewById(R.id.button6_notes_store); //button 6
        Button watch_videos = findViewById(R.id.button7_Videos); //button 7
        Button Connect_with_us = findViewById(R.id.button9_connect_us); //button 8
        Button sol_materials = findViewById(R.id.button10_SOL_Study_Material); //button 10

        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);

        functionManager.managerNewSignLogo(this,this);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }

        // Navigation Bar Button

        // Videos
        NavVideos.setOnClickListener(view -> {
            // Define the YouTube channel URL
            String youtubeChannelUrl = "https://www.youtube.com/@TheLearnersCommunityDUSOL/videos";

            // Create an Intent with the ACTION_VIEW action and the YouTube channel URL
            Uri youtubeUri = Uri.parse(youtubeChannelUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

            // Set the package name of the YouTube app
            intent.setPackage("com.google.android.youtube");

            // Check if the YouTube app is installed
            if (intent.resolveActivity(getPackageManager()) != null) {
                // The YouTube app is installed, so open it
                startActivity(intent);
            } else {
                // The YouTube app is not installed, you can handle this case as needed
                // For example, you can open the YouTube website in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeChannelUrl)));
            }
        });

        // Notes Books
        NavBooks.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Notes_Open", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Notes_Open", bundle);

            Intent intent = new Intent(getApplicationContext(), DU_SOL_NOTES__MainActivity.class);
            startActivity(intent);
        });

        // Students Portal
        NavStudents.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);

            Intent intent = new Intent(getApplicationContext(), studentsBoard.class);
            startActivity(intent);
        });

        // Main Buttons

        // Text Marquee
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
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        // Notes Store Button
        shop.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("Notes_Store", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("Notes_Store", bundle);

            Intent intent = new Intent(getApplicationContext(), NotesStore_HomePage.class);
            startActivity(intent);
        });

        // Button 1
        du_and_sol_notes_page.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Notes_Open", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Notes_Open", bundle);

            Intent intent = new Intent(getApplicationContext(), DU_SOL_NOTES__MainActivity.class);
            startActivity(intent);
        });

        // Button 2
        QuestionPapers.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("Question_Paper_Open", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("Question_Paper_Open", bundle);

            Intent intent = new Intent(this, QP_TabLayout_Activity.class);
            startActivity(intent);
        });


        // Button 3
        solUpdates.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Updates", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Updates", bundle);

            String urlToOpen = "https://web.sol.du.ac.in/home";

            // Create an Intent to open the web browser with the specified URL
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToOpen));

            // Check if there's a web browser available to handle the Intent
            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
            boolean isIntentSafe = activities.size() > 0;
            startActivity(intent);
        });

        // Button 5
        SOL_Syllabus.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Syllabus", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Syllabus", bundle);

            Intent intent = new Intent(getApplicationContext(), SyllabusTabLayoutActivity.class);
            startActivity(intent);
        });

        watch_videos.setOnClickListener(view -> {
            // Define the YouTube channel URL
            String youtubeChannelUrl = "https://www.youtube.com/@TheLearnersCommunityDUSOL/videos";

            // Create an Intent with the ACTION_VIEW action and the YouTube channel URL
            Uri youtubeUri = Uri.parse(youtubeChannelUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

            // Set the package name of the YouTube app
            intent.setPackage("com.google.android.youtube");

            // Check if the YouTube app is installed
            if (intent.resolveActivity(getPackageManager()) != null) {
                // The YouTube app is installed, so open it
                startActivity(intent);
            } else {
                // The YouTube app is not installed, you can handle this case as needed
                // For example, you can open the YouTube website in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeChannelUrl)));
            }
        });


        // Button 8
        sol_portal.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);

            Intent intent = new Intent(getApplicationContext(), studentsBoard.class);
            startActivity(intent);
        });


        // Button 10
        sol_materials.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);

            Intent intent = new Intent(getApplicationContext(), study_materials.class);
            startActivity(intent);
        });

        // Button 9
        Connect_with_us.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("Join_US", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("Join_US", bundle);

            Intent intent = new Intent(getApplicationContext(), connect_with_us_MainActivity.class);
            startActivity(intent);

        });

        // Firebase FCM
        FirebaseMessaging.getInstance().subscribeToTopic("All_Notification")
                .addOnCompleteListener(task -> {
                    // Check if subscribing to the topic was successful
                    if (!task.isSuccessful()) {
                        // If not successful, you can handle any failure here
                        // For example, you can uncomment the line below to set the message to "Subscribe failed"
                        // msg = "Subscribe failed";
                    } else {
                        // If subscription is successful, you can perform any necessary actions here
                        // For example, display a toast message to indicate success
                        // Toast.makeText(MainActivity3.this, "Done", Toast.LENGTH_SHORT).show();

                        // Log a message to check whether the user successfully subscribed to the topic
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

        //get Teg Code
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        Log.d("FirebaseRegToken", token);

                    }
                });


    }

    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 and above
            // Check if the notification permission is granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // If permission is not granted, request it
                requestNotificationPermission();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void requestNotificationPermission() {
        // Request permission for notifications (Android 13+)
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
    }

    // Activity result launcher for requesting notification permission
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    if (isGranted) {
                        // Permission granted
                        Log.d("Notification", "Permission granted for notifications.");
                        Toast.makeText(LinkPage_MainActivity.this, "Notifications are enabled!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Permission denied
                        Log.d("Notification", "Permission denied for notifications.");
                        Toast.makeText(LinkPage_MainActivity.this, "Notifications are disabled.", Toast.LENGTH_SHORT).show();
                    }
                }
            });



}
