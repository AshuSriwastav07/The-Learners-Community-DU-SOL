/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
public class connect_with_us_MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_with_us_main);
        loadads();

        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);

        ImageButton openYouTubeChannelButton = findViewById(R.id.YTLink);

        openYouTubeChannelButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Define the YouTube channel URL
            String youtubeChannelUrl = "https://www.youtube.com/@TheLearnersCommunityDUSOL/";

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

        ImageButton openTelegramChannelButton = findViewById(R.id.TelegramLink);

        openTelegramChannelButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Define the Telegram channel URL
            String TelegramChannelUrl = "https://t.me/The_LCTyoutube";

            // Create an Intent with the ACTION_VIEW action and the Telegram channel URL
            Uri telegramUri = Uri.parse(TelegramChannelUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, telegramUri);

            // Set the package name of the Telegram app
            intent.setPackage("org.telegram.messenger");

            // Check if the Telegram app is installed
            if (intent.resolveActivity(getPackageManager()) != null) {
                // The Telegram app is installed, so open it
                startActivity(intent);
            } else {
                // The Telegram app is not installed, you can handle this case as needed
                // For example, you can open the Telegram website in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(TelegramChannelUrl)));
            }
        }
    });

        ImageButton openBlogSiteButton = findViewById(R.id.BloggerLink);

        openBlogSiteButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Define the Blogger channel URL
            String BlogSiteUrl = "https://du-sol-study-notes.blogspot.com/";

            // Create an Intent with the ACTION_VIEW action and the Blogger channel URL
            Uri BloggerUri = Uri.parse(BlogSiteUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, BloggerUri);

            // Set the package name of the Blogger app
            intent.setPackage("com.google.android.youtube");

            // Check if the Blogger app is installed
            if (intent.resolveActivity(getPackageManager()) != null) {
                // The Blogger app is installed, so open it
                startActivity(intent);
            } else {
                // The Blogger app is not installed, you can handle this case as needed
                // For example, you can open the Blogger website in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(BlogSiteUrl)));
            }
        }
    });

        ImageButton openInstagram = findViewById(R.id.instaLink);

        openInstagram.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Define the Blogger channel URL
            String instasite = "https://www.instagram.com/the_learners_community_dusol/";

            // Create an Intent with the ACTION_VIEW action and the Blogger channel URL
            Uri BloggerUri = Uri.parse(instasite);
            Intent intent = new Intent(Intent.ACTION_VIEW, BloggerUri);

            // Set the package name of the Blogger app
            intent.setPackage("com.instagram.android");

            // Check if the Blogger app is installed
            if (intent.resolveActivity(getPackageManager()) != null) {
                // The Blogger app is installed, so open it
                startActivity(intent);
            } else {
                // The Blogger app is not installed, you can handle this case as needed
                // For example, you can open the Blogger website in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instasite)));
            }
        }
    });


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

}

    public void  loadads(){

        //add start
        MobileAds.initialize(this, initializationStatus -> {
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //add ends

    }
}