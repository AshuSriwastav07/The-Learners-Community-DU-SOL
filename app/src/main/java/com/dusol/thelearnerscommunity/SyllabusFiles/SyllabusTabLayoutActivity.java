package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.VP_Adapter;
import com.google.android.material.tabs.TabLayout;

public class SyllabusTabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_syllabus_tab_layout);

        // Initialize views
        TabLayout tabLayout = findViewById(R.id.Syllabus_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.Syllabus_ViewPager);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sem1SyllabusFragment(), "1st");
        vpAdapter.addFragment(new Sem2SyllabusFragment(), "2nd");
        vpAdapter.addFragment(new Sem3SyllabusFragment(), "3rd");
        vpAdapter.addFragment(new Sem4SyllabusFragment(), "4th");
        vpAdapter.addFragment(new Sem5SyllabusFragment(), "5th");
        vpAdapter.addFragment(new Sem6SyllabusFragment(), "6th");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

    }
}