/**
 * App developed by:
 * Ashu Sriwastav
  * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
public class connect_with_us_MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_with_us_main);

        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);

        ImageButton openYouTubeChannelButton = findViewById(R.id.YTLink);

        openYouTubeChannelButton.setOnClickListener(view -> {
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
        });

        ImageButton openTelegramChannelButton1 = findViewById(R.id.TelegramLink);

        openTelegramChannelButton1.setOnClickListener(view -> {
            // Define the Telegram channel URL
            String TelegramChannelUrl = "https://t.me/The_learnersCommunity";

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
        });

        ImageButton openTelegramChannelButton2 = findViewById(R.id.TelegramLink2);

        openTelegramChannelButton2.setOnClickListener(view -> {
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
        });

        ImageButton openBlogSiteButton = findViewById(R.id.BloggerLink);

        openBlogSiteButton.setOnClickListener(view -> {
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
        });

        ImageButton openInstagram = findViewById(R.id.instaLink);

        openInstagram.setOnClickListener(view -> {
            // Define the Blogger channel URL
            String instagramUri = "https://www.instagram.com/the_learners_community_dusol/";

            // Create an Intent with the ACTION_VIEW action and the Blogger channel URL
            Uri BloggerUri = Uri.parse(instagramUri);
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
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUri)));
            }
        });

        ImageButton shareButton = findViewById(R.id.ShareApp);
        shareButton.setOnClickListener(v -> {
            //The link you want to share
            String link = "https://play.google.com/store/apps/details?id=com.dusol.thelearnerscommunity";

            // Custom message
            String message = "⚡️ Download the Best Study App for DU/SOL/NCWEB & Ace Your Exams!⚡️\n" +
                    "✅ CBCS/NEP Course Notes\n" +
                    "💯 Semester 1-6 Question Papers📝\n" +
                    "📚 Semester 1-6 Study Material📚\n" +
                    "📚 All Semesters Updated Syllabus\n"+
                    "💯 All Collage Updates\n"+
                    "🎥 Video Explain Notes🎥\n" +
                    "✨ Student Portal for Every Need✨\n\n" +
                    "Check it out: " + link;

            // Create an Intent with ACTION_SEND
            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            // Set the type of content to text
            shareIntent.setType("text/plain");

            // Add the message to the Intent
            shareIntent.putExtra(Intent.EXTRA_TEXT, message);

            // Add a subject (optional)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check this out!");

            // Start the share activity
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        ImageButton openNotesWebsite = findViewById(R.id.NotesStorePage);

        openNotesWebsite.setOnClickListener(view -> {
            // Define the Blogger channel URL
            String SiteUrl = "https://thelearnerscommunitynotes.myinstamojo.com/";

            // Create an Intent with the ACTION_VIEW action and the Blogger channel URL
            Uri BloggerUri = Uri.parse(SiteUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, BloggerUri);

            // Check if the Blogger app is installed
            if (intent.resolveActivity(getPackageManager()) != null) {
                // The Blogger app is installed, so open it
                startActivity(intent);
            } else {
                // The Blogger app is not installed, you can handle this case as needed
                // For example, you can open the Blogger website in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(SiteUrl)));
            }
        });

        ImageButton reviewUS=findViewById(R.id.reviewUS);

        reviewUS.setOnClickListener(view ->{
            String playStorePage = "https://play.google.com/store/apps/details?id=com.dusol.thelearnerscommunity";
            Uri youtubeUri = Uri.parse(playStorePage);
            Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);
            intent.setPackage("com.google.android.playstore");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(playStorePage)));
            }


        });



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

        //Notes Books
        NavBooks.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),DU_SOL_NOTES__MainActivity.class);
            startActivity(intent);

        });

        //Students Portal
        NavStudents.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),studentsBoard.class);
            startActivity(intent);

        });

}


}