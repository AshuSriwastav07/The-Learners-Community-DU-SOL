/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class Semester_Select_MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 123; // Replace with your desired request code

    @Override
    public void onBackPressed() {
        // Call finish() to close the activity when the back button is pressed
        super.onBackPressed();
        Intent intent = new Intent(this, LinkPage_MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);  //This will clear activities from stack and send user to specific activity
        startActivity(intent);

    } //important function that clears the stack and sends you back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_semester_activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Add start
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //add ends


        Button buyNotes=findViewById(R.id.button7_Buy_Notes_PDF);
        ImageButton NavHome = (ImageButton)findViewById(R.id.navbarHome);
        ImageButton NavBooks = (ImageButton)findViewById(R.id.navbarBooks);
        ImageButton NavStudents = (ImageButton)findViewById(R.id.navbarStudent);
        ImageButton NavVideos = (ImageButton)findViewById(R.id.navbarVideos);

        //Videos // Left to make
        NavVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        NavHome.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),LinkPage_MainActivity.class);
            startActivity(intent);

        });

        //Students Portal
        NavStudents.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),studentsBoard.class);
            startActivity(intent);

        });


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST_INTERNET);
        } else {
            // Permission is already granted, you can proceed with internet access
        }


// Subscribe the device to a specific topic named "SOL_NOTICE" using Firebase Cloud Messaging (FCM).
        FirebaseMessaging.getInstance().subscribeToTopic("SOL_NOTICE")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";

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
                            Log.d("notes_topic", msg);
                        }
                    }
                });



//Navigation Bar Button

        //Home
        NavHome.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),LinkPage_MainActivity.class);
            startActivity(intent);

        });

        //Videos // Left to make

        //Students Portal
        NavStudents.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),studentsBoard.class);
            startActivity(intent);

        });


/*
        buyNotes.setOnClickListener(v -> {
            // Define the URL you want to open in the web browser
            String urlToOpen = "https://thelearnerscommunitynotes.myinstamojo.com/"; // Replace with your desired URL

            // Create an Intent to open the web browser with the specified URL
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToOpen));

            // Check if there's a web browser available to handle the Intent
            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
            boolean isIntentSafe = activities.size() > 0;
            startActivity(intent);

        });*/

    }



    //Buttons connect with semester notes

    public void openStore(View v) { // This activity will send you to the 4th-semester notes page
        Intent intent = new Intent(this, Notes_Store.class);
        startActivity(intent);
    }

    public void openSem1Notes(View v) { // This activity will send you to the 4th-semester notes page
        Intent intent = new Intent(this, SubjectSelect_1stSem_MainActivity.class);
        startActivity(intent);
    }
    public void openSem2Notes(View v) { // This activity will send you to the 4th-semester notes page
        Intent intent=new Intent(this, SubjectSelect_2ndSem_MainActivity.class);
        startActivity(intent);
    }
    public void openSem3Notes(View v) { // This activity will send you to the 4th-semester notes page
        Toast.makeText(getApplicationContext(),"3rd Semester Notes Will be available Soon",Toast.LENGTH_SHORT).show();
    }
    public void openSem4Notes(View v) { // This activity will send you to the 4th-semester notes page
        Intent intent = new Intent(this, SubjectSelect_4thSem_MainActivity.class);
        startActivity(intent);
    }
    public void openSem5Notes(View v) { // This activity will send you to the 5th-semester notes page
        Intent intent = new Intent(this, SubjectSelect_5thSem_MainActivity.class);
        startActivity(intent);
    }
    public void openSem6Notes(View v) { // This activity will send you to the 6th-semester notes page
        Intent intent = new Intent(this, SubjectSelect_6thSem_MainActivity.class);
        startActivity(intent);
    }
}
