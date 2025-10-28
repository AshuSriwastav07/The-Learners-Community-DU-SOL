package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem8.Sem8_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem8.Sem8_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem8.Sem8_Paper1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem8.Sem8_Paper2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem8.Sem8_VAC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem8.Sem_SEC;
import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.VP_Adapter;
import com.google.android.material.tabs.TabLayout;

public class NEP_Sem8_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nep_sem8_main);

        TabLayout tabLayout = findViewById(R.id.nep_sem8_notes_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.nep_sem8_notes_PageViewer);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sem8_Paper1_DSC(), "Paper 1");
        vpAdapter.addFragment(new Sem8_Paper2_DSC(), "Paper 2");
        vpAdapter.addFragment(new Sem8_AEC(), "AEC");
        vpAdapter.addFragment(new Sem8_GENERIC_ELECTIVE(), "GE");
        vpAdapter.addFragment(new Sem_SEC(), "SEC");
        vpAdapter.addFragment(new Sem8_VAC(), "VAC");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }
}