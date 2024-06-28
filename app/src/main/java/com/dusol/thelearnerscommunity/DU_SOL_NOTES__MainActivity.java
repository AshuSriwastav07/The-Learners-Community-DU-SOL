/**
 * App developed by:
 * Ashu Sriwastav
 * <p>
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_HomePage;
import com.dusol.thelearnerscommunity.SyllabusFiles.SyllabusMainActivity;

public class DU_SOL_NOTES__MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_du_sol_notes_main);
        Button sol_notes = findViewById(R.id.button1_SOL_CBCS_NOTES); //Button 1
        Button nep_notes = findViewById(R.id.button2_NEP_Notes); //Button 2
        Button StudyMaterial = findViewById(R.id.button3_SOL_Material); //Button 3
        Button Syllabus = findViewById(R.id.button4_SOL_Syllabus); //Button 3

        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);


        //Button 1
        sol_notes.setOnClickListener(view -> startActivity(new Intent(DU_SOL_NOTES__MainActivity.this,Semester_Select_MainActivity.class)));

//        button2
        nep_notes.setOnClickListener(v -> startActivity(new Intent(DU_SOL_NOTES__MainActivity.this, NEP_HomePage.class)));

//        button3
        StudyMaterial.setOnClickListener(v -> startActivity(new Intent(DU_SOL_NOTES__MainActivity.this, study_materials.class)));

//        button4
        Syllabus.setOnClickListener(v -> startActivity(new Intent(DU_SOL_NOTES__MainActivity.this, SyllabusMainActivity.class)));




//Navigation Bar Button

        //Home
        NavHome.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),LinkPage_MainActivity.class);
            startActivity(intent);

        });

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
        //Notes Books
        NavBooks.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),Semester_Select_MainActivity.class);
            startActivity(intent);

        });

        //Students Portal
        NavStudents.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),studentsBoard.class);
            startActivity(intent);

        });




        /*DU_notes.setOnClickListener(view -> {
            click1++; //count number of click
            if (mInterstitialAd != null && click1 % NumberOfClickToShowAsd1==0) {
                mInterstitialAd.show(DU_SOL_NOTES__MainActivity.this); // Google ads will node if user click on question paper button every second time

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){


                    @Override
                    public void onAdDismissedFullScreenContent() {
                        startActivity(new Intent(DU_SOL_NOTES__MainActivity.this,DU_NOTES_Semester_Select.class));  //If Google ads load in form of video and when user cut the app user will send to the next activity that is Question Paper activity
                        loadads();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when ad fails to show.
                        Log.e(TAG, "Ad failed to show fullscreen content.");
                        mInterstitialAd = null;
                    }

                });

            } else {
                startActivity(new Intent(DU_SOL_NOTES__MainActivity.this, DU_NOTES_Semester_Select.class));// if ads Did not load user will directly send to the next activity
                Log.d("TAG", "The interstitial ad wasn't ready yet.");

            }

        });*/


    }
    }

