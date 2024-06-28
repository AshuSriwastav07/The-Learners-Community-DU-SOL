/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity.SyllabusFiles;

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

import com.dusol.thelearnerscommunity.LinkPage_MainActivity;
import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.Semester_Select_MainActivity;
import com.dusol.thelearnerscommunity.studentsBoard;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Syllabus_MainActivity_NotInUse extends AppCompatActivity {

    private  InterstitialAd mInterstitialAd;
    int click1=0;
    int NumberOfClickToShowAsd=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_main);
        load_banner_ads();
        String[] links={
                "https://pdfhost.io/v/6hnL6wYAx_BA_Programme_1st_Sem",//1st-sem
                "https://pdfhost.io/v/vwYU1qo0E_Microsoft_Word_BCom__P__SemesterI_NewF",
                "https://pdfhost.io/v/LPXjmGiwn_BCom_Hons_1st_sem",

                "https://pdfhost.io/v/CSYzldWCG_BA_Programme_2nd_Sem", //2nd Sem
                "https://pdfhost.io/v/PmyKHKbzt_Microsoft_Word_BCom__P__SemesterI_NewF",
                "https://pdfhost.io/v/1rC23V7RO_BCom_Hons_2nd_sem",

                "https://pdfhost.io/v/n.eHIvv4s_BA_Programme_3rd_Sem", //3rd Sem
                "https://pdfhost.io/v/uMHJJT5ME_BCom_3rd_sem",
                "https://pdfhost.io/v/19RZSiAk6_BCom_Hons_3rd_sem",

                "https://pdfhost.io/v/Zw9ApjjHK_BA_Programme_4th_Sem", //4th Sem
                "https://pdfhost.io/v/IdmtgGXjM_BCom_4th_sem",
                "https://pdfhost.io/v/HejnMxKSy_BCom_Hons_4th_sem",

                "https://pdfhost.io/v/EhioIfyyT_BA_Prog_5", //5th Sem
                "https://pdfhost.io/v/4g7ho3ZtJ_BCom_Prog_5",
                "https://pdfhost.io/v/0FGHxy1Uh_BCom_Hons_5",

                "https://pdfhost.io/v/M3N2~B1sk_Microsoft_Word_BA_Prog_Economicsdocx", //6th sem
                "https://pdfhost.io/v/TBfSK.ti5_BCom_Prog6",
                "https://pdfhost.io/v/u97QpYFEO_BCom_Hons_6",

                "https://pdfhost.io/v/Q.zc8DfAv_UG_Prospectus_2023_24",//Prospectus PDF

                //nep Syllabus 1st

                "https://pdfhost.io/v/SihLC~llN_BA_PROGRAMME_nep", //19
                "https://pdfhost.io/v/mJRB2Z95x_BCom_nep",//20
                "https://pdfhost.io/v/tjIoMS762_BCom_Hons_nep", //21


                //2nd sem  nep
                "https://pdfhost.io/v/YUhO8cyU._BA_PROGRAMME_nd_nep", //22
                "https://pdfhost.io/v/h5uCXKpGy_BCOM_2nd_nep", //23
                "https://pdfhost.io/v/JszxvFMDk_BCom_Hons_2nd_nep", //24


                //3rd sem Nep
                "https://pdfhost.io/v/3qn.Sfeih_BA_Programme_3rd_semester_NEP_SOL", //22
                "https://pdfhost.io/v/nN9nNgdwC_BCom_Programme_3rd_sem_nep_sol", //23
                "https://pdfhost.io/v/6zwcteM7g_BCom_Hons_3rd_sem_nep_sol", //24




        };

        Button prospectusPDF=findViewById(R.id.nep_prospectus);

        Button sem1_BA = findViewById(R.id.sem1_BA);
        Button sem1_BCOM = findViewById(R.id.sem1_bcom);
        Button sem1_BCOM_H = findViewById(R.id.sem1_bcom_hons);

        Button nepsem1_BA = findViewById(R.id.nepsem1_BA);
        Button nepsem1_BCOM = findViewById(R.id.nepsem1_Bcom);
        Button nepsem1_BCOM_H = findViewById(R.id.nepsem1_BcomH);


        Button sem2_BA = findViewById(R.id.sem2_BA);
        Button sem2_BCOM = findViewById(R.id.sem2_bcom);
        Button sem2_BCOM_H = findViewById(R.id.sem2_bcom_hons);

        Button nepsem2_BA = findViewById(R.id.nepsem2_BA);
        Button nepsem2_BCOM = findViewById(R.id.nepsem2_bcom);
        Button nepsem2_BCOM_H = findViewById(R.id.nepsem2_bcom_hons);

        Button sem3_BA = findViewById(R.id.sem3_BA);
        Button sem3_BCOM = findViewById(R.id.sem3_bcom);
        Button sem3_BCOM_H = findViewById(R.id.sem3_bcom_hons);

        Button nepsem3_BA = findViewById(R.id.nepsem3_BA);
        Button nepsem3_BCOM = findViewById(R.id.nepsem3_Bcom);
        Button nepsem3_BCOM_H = findViewById(R.id.nepsem3_BcomH);

        Button sem4_BA = findViewById(R.id.sem4_BA);
        Button sem4_BCOM = findViewById(R.id.sem4_bcom);
        Button sem4_BCOM_H = findViewById(R.id.sem4_bcom_hons);

        Button sem5_BA = findViewById(R.id.sem5_BA);
        Button sem5_BCOM = findViewById(R.id.sem5_bcom);
        Button sem5_BCOM_H = findViewById(R.id.sem5_bcom_hons);

        Button sem6_BA = findViewById(R.id.sem6_BA);
        Button sem6_BCOM = findViewById(R.id.sem6_bcom);
        Button sem6_BCOM_H = findViewById(R.id.sem6_bcom_hons);


        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);



//Navigation Bar Button

        //Home
        NavHome.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(), LinkPage_MainActivity.class);
            startActivity(intent);

        });

        //Videos // Left to make

        //Notes Books
        NavBooks.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(), Semester_Select_MainActivity.class);
            startActivity(intent);

        });

        //Students Portal
        NavStudents.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(), studentsBoard.class);
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

        sem1_BA.setOnClickListener(v -> {
            startads(links,0);
        });


        sem1_BCOM.setOnClickListener(v -> {click1++; //count number of click
            startads(links,1);
        });

        sem1_BCOM_H.setOnClickListener(v -> {
            startads(links,2);
        });

        //2nd sem
        sem2_BA.setOnClickListener(v -> {
            startads(links,3);
        });


        sem2_BCOM.setOnClickListener(v -> {
            startads(links,4);
        });

        sem2_BCOM_H.setOnClickListener(v -> {
            startads(links,5);
        });

        //3rd semester
        sem3_BA.setOnClickListener(v -> {
            startads(links,6);
        });


        sem3_BCOM.setOnClickListener(v -> {
            startads(links,7);
        });

        sem3_BCOM_H.setOnClickListener(v -> {
            startads(links,8);
        });

        //4th sem

        sem4_BA.setOnClickListener(v -> {
            startads(links,9);
        });


        sem4_BCOM.setOnClickListener(v -> {
            startads(links,10);
        });

        sem4_BCOM_H.setOnClickListener(v -> {
            startads(links,11);
        });

        //5th sem

        sem5_BA.setOnClickListener(v -> {
            startads(links,12);
        });


        sem5_BCOM.setOnClickListener(v -> {
            startads(links,13);
        });

        sem5_BCOM_H.setOnClickListener(v -> {
            startads(links,14);
        });

        //6th sem

        sem6_BA.setOnClickListener(v -> {
            startads(links,15);
        });


        sem6_BCOM.setOnClickListener(v -> {
            startads(links,16);
        });

        sem6_BCOM_H.setOnClickListener(v -> {
            startads(links,17);
        });


        prospectusPDF.setOnClickListener(v -> {
            startads(links,18);
        });

        //NEP Syllabus
        nepsem1_BA.setOnClickListener(v -> {
            startads(links,19);
        });

        nepsem1_BCOM.setOnClickListener(v -> {
            startads(links,20);
        });

        nepsem1_BCOM_H.setOnClickListener(v -> {
            startads(links,21);
        });

        nepsem2_BA.setOnClickListener(v -> {
            startads(links,22);

        });

        nepsem2_BCOM.setOnClickListener(v -> {
            startads(links,23);

        });

        nepsem2_BCOM_H.setOnClickListener(v -> {
            startads(links,24);

        });

        nepsem3_BA.setOnClickListener(v -> {
            startads(links,25);
        });

        nepsem3_BCOM.setOnClickListener(v -> {
            startads(links,26);
        });

        nepsem3_BCOM_H.setOnClickListener(v -> {
            startads(links,27);
        });

    }


    private void  load_banner_ads(){

        //add start
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


    }

    public void openIntend(String link){
        Intent intent = new Intent(this, Syllabus_Web_MainActivity.class);
        intent.putExtra("link", link);
        startActivity(intent);
        loadads();
    }

    public void loadads(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
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
                    }
                });
    }

    public void startads(String Links[],int linkNum){ //Provide array of links and Index number of link
        click1++; //count number of click
        if (mInterstitialAd != null && click1 % NumberOfClickToShowAsd==0) {
            mInterstitialAd.show(Syllabus_MainActivity_NotInUse.this); // Google ads will node if user click on question paper button every second time

            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d(TAG, "Ad was clicked.");
                }
                @Override
                public void onAdDismissedFullScreenContent() {
                    openIntend(Links[linkNum]);//This will call open intend function and after loading add it will send user to next activity
                    loadads();
                }
                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                    Log.e(TAG, "Ad failed to show fullscreen content.");
                    mInterstitialAd = null;
                    loadads();
                }
                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d(TAG, "Ad recorded an impression.");
                }
                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d(TAG, "Ad showed fullscreen content.");
                }
            });
        } else {
            openIntend(Links[linkNum]); //If add do not load it will send automatically user to next activity
            loadads();
        }

    }

}




