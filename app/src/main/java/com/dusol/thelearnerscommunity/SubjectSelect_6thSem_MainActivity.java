/**
 * App developed by:
 * Ashu Sriwastav
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class SubjectSelect_6thSem_MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_subject_select6th_sem_main);


        /*Button sem6PolSci=findViewById(R.id.sem6Political);
        Button sem6History=findViewById(R.id.sem6History);
        Button sem6HistorySec=findViewById(R.id.sem6HistorySec);
        Button sem6EnglishGE=findViewById(R.id.sem6EnglishGE);
        Button sem6EducationSec=findViewById(R.id.sem6EducationSEC);
        Button sem6PoliticalSEC=findViewById(R.id.sem6PoliticalSEC);
        Button sem6ExamNotes=findViewById(R.id.sem6ExamNotes);
        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents =findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);



//Navigation Bar Button

        //Home
        NavHome.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),LinkPage_MainActivity.class);
            startActivity(intent);

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


        sem6PolSci.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester6_Notes_Fragment_Container, Political6thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem6History.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester6_Notes_Fragment_Container, History6thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });
        sem6EnglishGE.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester6_Notes_Fragment_Container, EnglishGE6thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });
        sem6HistorySec.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester6_Notes_Fragment_Container, HistorySec6thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });
        sem6EducationSec.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester6_Notes_Fragment_Container, EducationSec6thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem6PoliticalSEC.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester6_Notes_Fragment_Container, PoliticalSciSEC_6thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });
        sem6ExamNotes.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester6_Notes_Fragment_Container, Sem6ExamAllNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });*/

        // Initialize views
        TabLayout tabLayout = findViewById(R.id.cbcs_sem6_notes_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.cbcs_sem6_notes_PageViewer);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Political6thSemNotes(), "Political");
        vpAdapter.addFragment(new History6thSemNotes(), "History");
        vpAdapter.addFragment(new HistorySec6thSemNotes(),"History Sec");
        vpAdapter.addFragment(new EnglishGE6thSemNotes(), "English GE");
        vpAdapter.addFragment(new EducationSec6thSemNotes(), "Education Sec");
        vpAdapter.addFragment(new PoliticalSciSEC_6thSemNotes(), "Political SEC");
        vpAdapter.addFragment(new Sem6ExamAllNotes(), "Exam Notes");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

    }
}