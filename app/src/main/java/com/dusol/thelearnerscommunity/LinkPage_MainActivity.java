/**
 * App developed by:
 * Ashu Sriwastav
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class LinkPage_MainActivity extends AppCompatActivity {
    private long Timeback;
    private  InterstitialAd mInterstitialAd;
    Long AdsClick;
    int NumberOfClickToShowAsd=1;




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

        loadads();




        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        Button du_and_sol_notes_page=findViewById(R.id.button1_du_sol_notes_page); //button1
        Button solUpdates = findViewById(R.id.button2_SOL_Updates); //Button 2
        Button QuestionPapers = findViewById(R.id.button3_QP); //Button 3
        Button SOL_Syllabus  = findViewById(R.id.button4_SOL_Syllabus); //Button 4
        Button sol_portal = findViewById(R.id.button5_Portal); //Button 5
        Button shop=findViewById(R.id.button6_notes_store); //button 6
        Button watch_videos=findViewById(R.id.button7_Videos); //button 7
        Button Connect_with_us =findViewById(R.id.button9_connect_us); //button 8
        Button sol_materials=findViewById(R.id.button10_SOL_Study_Material); //button 10

        ImageButton NavBooks = (ImageButton)findViewById(R.id.navbarBooks);
        ImageButton NavStudents = (ImageButton)findViewById(R.id.navbarStudent);
        ImageButton NavVideos = (ImageButton)findViewById(R.id.navbarVideos);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }

        MobileAds.initialize(this, initializationStatus -> {
            // Ads SDK initialization is complete
            // Start loading ads

        });


//Navigation Bar Button

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

        //Notes Books
        NavBooks.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString("SOL_Notes_Open", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Notes_Open", bundle);

            Intent intent=new Intent(getApplicationContext(),Semester_Select_MainActivity.class);
            startActivity(intent);

        });

        //Students Portal
        NavStudents.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);

            Intent intent=new Intent(getApplicationContext(),studentsBoard.class);
            startActivity(intent);

        });//Nav Ends Here


        //Main Buttons Starts Here


        //Text Marquee
        TextView textView=findViewById(R.id.LinkPageMarquee);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        DatabaseReference MarqueeText= FirebaseDatabase.getInstance().getReference("MainPageBanner");
        MarqueeText.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String TextToShow=snapshot.getValue(String.class);
                if(TextToShow.equals("N/A")){
                    textView.setVisibility(View.GONE);
                }
                else{
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

            Intent intent=new Intent(getApplicationContext(),Notes_Store.class);
            startActivity(intent);

        });


        //Button 1
        du_and_sol_notes_page.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString("SOL_Notes_Open", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Notes_Open", bundle);
            start_Ads(this,DU_SOL_NOTES__MainActivity.class,2);
//            Intent intent=new Intent(getApplicationContext(),DU_SOL_NOTES__MainActivity.class);
//            startActivity(intent);

        });


        //Button 2
        QuestionPapers.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString("Question_Paper_Open", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("Question_Paper_Open", bundle);
            start_Ads(this, QuestionPapers_MainActivity.class,2);
//            Intent intent=new Intent(this,QuestionPapers_MainActivity.class);
//            startActivity(intent);

        });


        //Button 3
        solUpdates.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putString("SOL_Updates", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Updates", bundle);

            String urlToOpen = "https://web.sol.du.ac.in/home"; // Replace with your desired URL

            // Create an Intent to open the web browser with the specified URL
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToOpen));

            // Check if there's a web browser available to handle the Intent
            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
            boolean isIntentSafe = activities.size() > 0;
            startActivity(intent);

        });


        //Button 5
        SOL_Syllabus.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString("SOL_Syllabus", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("SOL_Syllabus", bundle);
            start_Ads(LinkPage_MainActivity.this,SyllabusMainActivity.class,2);
//            Intent intent=new Intent(getApplicationContext(),Syllabus_MainActivity.class);
//            startActivity(intent);
        });

        watch_videos.setOnClickListener(new View.OnClickListener() {
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

        //Button 8
        sol_portal.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(LinkPage_MainActivity.this).logEvent("SOL_Portal", bundle);
            start_Ads(LinkPage_MainActivity.this,studentsBoard.class,2);
//            Intent intent=new Intent(getApplicationContext(),studentsBoard.class);
//            startActivity(intent);
        });

        //Button 10
        sol_materials.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Portal", "button_clicked");
            FirebaseAnalytics.getInstance(LinkPage_MainActivity.this).logEvent("SOL_Portal", bundle);
            start_Ads(LinkPage_MainActivity.this, study_materials.class,2);

//            Intent intent=new Intent(getApplicationContext(),study_materials.class);
//            startActivity(intent);
        });


        //Button 9
        Connect_with_us.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putString("Join_US", "button_clicked");
            FirebaseAnalytics.getInstance(this).logEvent("Join_US", bundle);

            Intent intent=new Intent(getApplicationContext(),connect_with_us_MainActivity.class);
            startActivity(intent);

            addContentView(Connect_with_us, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        });


        //Firebase FCM
        FirebaseMessaging.getInstance().subscribeToTopic("All_Notification")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

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
                    }
                });


    }


    public void loadads(){
        MobileAds.initialize(this, initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-7092743628840352/8393735655", adRequest,
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
                        loadads();
                    }
                });
    }


public void start_Ads(Activity sourceActivity, Class<?> targetActivityClass,int click){
    click++;
    if (mInterstitialAd != null && click %NumberOfClickToShowAsd !=1) {
        mInterstitialAd.show(LinkPage_MainActivity.this);
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){

            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d("LinkPageAds", "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
//                        startActivity(new Intent(getApplicationContext(),Syllabus_MainActivity.class));
                loadads();
                startActivity(new Intent(sourceActivity,targetActivityClass));

            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                // Called when ad fails to show.
                Log.e("LinkPageAds", "Ad failed to show fullscreen content.");
//                mInterstitialAd = null;
                loadads();
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d("LinkPageAds", "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d("LinkPageAds", "Ad showed fullscreen content.");
            }
        });
    } else {
        startActivity(new Intent(sourceActivity,targetActivityClass));
        loadads();

    }


}



}