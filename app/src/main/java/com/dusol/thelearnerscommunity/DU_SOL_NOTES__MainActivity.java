/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class DU_SOL_NOTES__MainActivity extends AppCompatActivity {


    private  InterstitialAd mInterstitialAd;
    int click1=0;

    int click2=0;
    int NumberOfClickToShowAsd2=1;
    int NumberOfClickToShowAsd1=1;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_du_sol_notes_main);
        Button sol_notes = findViewById(R.id.button1_DU_SOL_NOTES); //Button 1
        Button study_material = findViewById(R.id.button2_SOL_Material); //Button 2
        ImageButton NavHome = (ImageButton)findViewById(R.id.navbarHome);
        ImageButton NavBooks = (ImageButton)findViewById(R.id.navbarBooks);
        ImageButton NavStudents = (ImageButton)findViewById(R.id.navbarStudent);
        ImageButton NavVideos = (ImageButton)findViewById(R.id.navbarVideos);

        loadads();

        //add start

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
//                Toast.makeText(DU_SOL_NOTES__MainActivity.this, "Ads Load", Toast.LENGTH_SHORT).show();
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        add ends

        //Button 2
        sol_notes.setOnClickListener(view -> {
            click2++; //count number of click
            if (mInterstitialAd != null && click2 % NumberOfClickToShowAsd2==0) {
                mInterstitialAd.show(DU_SOL_NOTES__MainActivity.this); // Google ads will node if user click on question paper button every second time

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){


                    @Override
                    public void onAdDismissedFullScreenContent() {
                        startActivity(new Intent(DU_SOL_NOTES__MainActivity.this,Semester_Select_MainActivity.class));  //If Google ads load in form of video and when user cut the app user will send to the next activity that is Question Paper activity
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
                startActivity(new Intent(DU_SOL_NOTES__MainActivity.this,Semester_Select_MainActivity.class));// if ads Did not load user will directly send to the next activity
                Log.d("TAG", "The interstitial ad wasn't ready yet.");

            }

        });

        study_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DU_SOL_NOTES__MainActivity.this,study_materials.class));
            }
        });



//Navigation Bar Button

        //Home
        NavHome.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),LinkPage_MainActivity.class);
            startActivity(intent);

        });

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



    public void loadads(){

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-7092743628840352/2554892090", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
//                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
//                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }
    }

