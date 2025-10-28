package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem7.Sem7_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem7.Sem7_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem7.Sem7_Paper1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem7.Sem7_Paper2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem7.Sem7_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem7.Sem7_VAC;
import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.VP_Adapter;
import com.google.android.material.tabs.TabLayout;

public class NEP_Sem7_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nep_sem7_main);

        TabLayout tabLayout = findViewById(R.id.nep_sem7_notes_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.nep_sem7_notes_PageViewer);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sem7_Paper1_DSC(), "Paper 1");
        vpAdapter.addFragment(new Sem7_Paper2_DSC(), "Paper 2");
        vpAdapter.addFragment(new Sem7_AEC(), "AEC");
        vpAdapter.addFragment(new Sem7_GENERIC_ELECTIVE(), "GE");
        vpAdapter.addFragment(new Sem7_SEC(), "SEC");
        vpAdapter.addFragment(new Sem7_VAC(), "VAC");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

    }
}