package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.os.Bundle;
import com.dusol.thelearnerscommunity.BaseActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.VP_Adapter;
import com.google.android.material.tabs.TabLayout;

public class SyllabusTabLayoutActivity extends BaseActivity {

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
        vpAdapter.addFragment(new Sem7SyllabusFragment(), "7th");
        vpAdapter.addFragment(new Sem8SyllabusFragment(), "8th");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);
        
        // Add smooth depth swiping animation
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

    }
    
    private static class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(android.view.View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) {
                view.setAlpha(0f);
            } else if (position <= 0) {
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);
            } else if (position <= 1) {
                view.setAlpha(1 - position);
                view.setTranslationX(pageWidth * -position);
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else {
                view.setAlpha(0f);
            }
        }
    }
}