/**
 * App developed by:
 * Ashu Sriwastav
 *
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


public class SubjectSelect_5thSem_MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_subject_select5th_sem_main);


        /*Button sem5HistoryNotes=findViewById(R.id.sem5History);
        Button sem5HindiNotes=findViewById(R.id.sem5Hindi);
        Button sem5PoliticalNotes=findViewById(R.id.sem5Political);
        Button sem5EduSecNotes=findViewById(R.id.sem5SecEdu);
        Button sem5GEHisNotes=findViewById(R.id.sem5GEHistory);
        Button sem5DSE_EduNotes=findViewById(R.id.sem5DSE_Education);
        Button sem5ExamNotes=findViewById(R.id.sem5ExamNotes);
        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
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


        sem5HistoryNotes.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester5_Notes_Fragment_Container, History5thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem5HindiNotes.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester5_Notes_Fragment_Container, Hindi5thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem5PoliticalNotes.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester5_Notes_Fragment_Container, Political5thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem5EduSecNotes.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester5_Notes_Fragment_Container, EducationSem5SecWorkplaceNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

    sem5GEHisNotes.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester5_Notes_Fragment_Container, HistoryGE5thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        sem5DSE_EduNotes.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester5_Notes_Fragment_Container, EducationDSE5thSemNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

sem5ExamNotes.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.CBCS_Semester5_Notes_Fragment_Container, Sem5ExamAllNotes.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });*/

        // Initialize views
        TabLayout tabLayout = findViewById(R.id.cbcs_sem5_notes_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.cbcs_sem5_notes_PageViewer);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new History5thSemNotes(), "History");
        vpAdapter.addFragment(new Political5thSemNotes(), "Political");
        vpAdapter.addFragment(new Hindi5thSemNotes(), "Hindi GE");
        vpAdapter.addFragment(new EducationSem5SecWorkplaceNotes(), "Education");
        vpAdapter.addFragment(new EducationDSE5thSemNotes(), "Education DSE");
        vpAdapter.addFragment(new HistoryGE5thSemNotes(), "History GE");
        vpAdapter.addFragment(new Sem5ExamAllNotes(), "Exam Notes");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

    }


}