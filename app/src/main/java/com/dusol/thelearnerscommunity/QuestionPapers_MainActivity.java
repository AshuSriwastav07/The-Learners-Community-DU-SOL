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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class QuestionPapers_MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_question_papers_main);

        Button sem1=findViewById(R.id.sem1QP);
        Button sem2=findViewById(R.id.sem2QP);
        Button sem3=findViewById(R.id.sem3QP);
        Button sem4=findViewById(R.id.sem4QP);
        Button sem5=findViewById(R.id.sem5QP);
        Button sem6=findViewById(R.id.sem6QP);


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


        TextView textView=findViewById(R.id.textBannerMarquee);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true); //Run Marquee



        sem1.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,semester1QuestionPapers.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem2.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,semester2QuestionPapers.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem3.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,semester3QuestionPapers.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });
        sem4.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,semester4QuestionPapers.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem5.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,semester5QuestionPapers.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem6.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,semester6QuestionPapers.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

    }
}