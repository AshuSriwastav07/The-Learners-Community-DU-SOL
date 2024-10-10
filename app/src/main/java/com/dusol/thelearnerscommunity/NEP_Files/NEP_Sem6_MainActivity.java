package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_Paper1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_Paper2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_VAC;
import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.VP_Adapter;
import com.google.android.material.tabs.TabLayout;

public class NEP_Sem6_MainActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        // Close the activity immediately without managing back stack
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nep_sem6_main);

        /*Button paper1=findViewById(R.id.paper1DSC);
        Button paper2=findViewById(R.id.paper2DSC);
        Button aec=findViewById(R.id.aec);
        Button ge=findViewById(R.id.GenericElective);
        Button sec=findViewById(R.id.sec);
        Button vac=findViewById(R.id.vac);


        FragmentActivity context=this;

        paper1.setOnClickListener(v ->ManageFragment.setButtonToLoadFragment(context,new Sem6_Paper1_DSC(),R.id.NEPsem6Fragment));
        
        paper2.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_Paper2_DSC(),R.id.NEPsem6Fragment));

        aec.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_AEC(),R.id.NEPsem6Fragment));

        sec.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_SEC(),R.id.NEPsem6Fragment));

        ge.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_GENERIC_ELECTIVE(),R.id.NEPsem6Fragment));

        vac.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_VAC(),R.id.NEPsem6Fragment));*/

// Initialize views
        TabLayout tabLayout = findViewById(R.id.nep_sem6_notes_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.nep_sem6_notes_PageViewer);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sem6_Paper1_DSC(), "Paper 1");
        vpAdapter.addFragment(new Sem6_Paper2_DSC(), "Paper 2");
        vpAdapter.addFragment(new Sem6_AEC(), "AEC");
        vpAdapter.addFragment(new Sem6_GENERIC_ELECTIVE(), "GE");
        vpAdapter.addFragment(new Sem6_SEC(), "SEC");
        vpAdapter.addFragment(new Sem6_VAC(), "VAC");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }
}