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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStoreTabActivity;
import com.google.firebase.messaging.FirebaseMessaging;

public class Semester_Select_MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 123; // Replace with your desired request code

    /*@Override
    public void onBackPressed() {
        // Call finish() to close the activity when the back button is pressed
        super.onBackPressed();
        Intent intent = new Intent(this, DU_SOL_NOTES__MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);  //This will clear activities from stack and send user to specific activity
        startActivity(intent);

    } //important function that clears the stack and sends you back
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_semester_activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);


        Button buyNotes=findViewById(R.id.Buy_Notes_PDF);
        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);

        CardView sem1 = findViewById(R.id.CBCS_sem1_notes);
        CardView sem2 = findViewById(R.id.CBCS_sem2_notes);
        CardView sem3 = findViewById(R.id.CBCS_sem3_notes);
        CardView sem4 = findViewById(R.id.CBCS_sem4_notes);
        CardView sem5 = findViewById(R.id.CBCS_sem5_notes);
        CardView sem6 = findViewById(R.id.CBCS_sem6_notes);

        //Videos // Left to make
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
        }


// Subscribe the device to a specific topic named "SOL_NOTICE" using Firebase Cloud Messaging (FCM).
        FirebaseMessaging.getInstance().subscribeToTopic("SOL_NOTICE")
                .addOnCompleteListener(task -> {
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

        sem1.setOnClickListener(v-> Toast.makeText(this, "Notes Not Available", Toast.LENGTH_SHORT).show());
        sem2.setOnClickListener(v-> Toast.makeText(getApplicationContext(),"Notes Not Available",Toast.LENGTH_SHORT).show());
        sem3.setOnClickListener(v-> Toast.makeText(getApplicationContext(),"Notes Not Available",Toast.LENGTH_SHORT).show());
        sem4.setOnClickListener(v->{
            Intent intent = new Intent(this, SubjectSelect_4thSem_MainActivity.class);
            startActivity(intent);
        });
        sem5.setOnClickListener(v->{
            Intent intent = new Intent(this, SubjectSelect_5thSem_MainActivity.class);
            startActivity(intent);
        });
        sem6.setOnClickListener(v->{
            Intent intent = new Intent(this, SubjectSelect_6thSem_MainActivity.class);
            startActivity(intent);
        });

        buyNotes.setOnClickListener(view -> {
            Intent intent = new Intent(this, NotesStoreTabActivity.class);
            startActivity(intent);
        });

    }

/*

    //Buttons connect with semester notes

    public void openStore(View v) { // This activity will send you to the notes store page
        Intent intent = new Intent(this, NotesStoreTabActivity.class);
        startActivity(intent);
    }

    public void openSem1Notes(View v) {
        Toast.makeText(this, "CBCS 1st Semester Notes Not Available", Toast.LENGTH_SHORT).show();
    }
    public void openSem2Notes(View v) { // This activity will send you to the 2nd-semester notes page
        Toast.makeText(getApplicationContext(),"CBCS 2nd Semester Notes Not Available ",Toast.LENGTH_SHORT).show();

    }
    public void openSem3Notes(View v) { // This activity will send you to the 3rd-semester notes page
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

    */
}
