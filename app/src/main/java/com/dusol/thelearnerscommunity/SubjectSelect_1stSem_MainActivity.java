package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class SubjectSelect_1stSem_MainActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            // If there are fragments in the back stack, clear it
            getFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            // If no fragments, close the activity
            finish();
        }
    } //important function that clears the stack and sends you back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_select1st_sem_main);

        ImageButton NavHome = (ImageButton)findViewById(R.id.navbarHome);
        ImageButton NavBooks = (ImageButton)findViewById(R.id.navbarBooks);
        ImageButton NavStudents = (ImageButton)findViewById(R.id.navbarStudent);
        ImageButton NavVideos = (ImageButton)findViewById(R.id.navbarVideos);


        //Navigation Bar Button
        //Home
        NavHome.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),LinkPage_MainActivity.class);
            startActivity(intent);

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




}