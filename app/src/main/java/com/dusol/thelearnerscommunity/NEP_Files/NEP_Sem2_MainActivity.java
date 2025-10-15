package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem2.Sem2_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem2.Sem2_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem2.Sem2_Paper1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem2.Sem2_Paper2_DCS;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem2.Sem2_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem2.Sem2_VAC;
import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.VP_Adapter;
import com.google.android.material.tabs.TabLayout;

public class NEP_Sem2_MainActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        // Close the activity immediately without managing back stack
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nep_sem2_main);

        /*//Subject Select Buttons

        Button paper1=findViewById(R.id.paper1DSC);
        Button paper2=findViewById(R.id.paper2DSC);
        Button aec=findViewById(R.id.aec);
        Button ge=findViewById(R.id.GenericElective);
        Button sec=findViewById(R.id.sec);
        Button vac=findViewById(R.id.vac);



        FragmentActivity context=this;


        paper1.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem2_Paper1_DSC(),R.id.NEPsem2Fragment));

        paper2.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem2_Paper2_DCS(),R.id.NEPsem2Fragment));


        aec.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem2_AEC(),R.id.NEPsem2Fragment));

        sec.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem2_SEC(),R.id.NEPsem2Fragment));

        ge.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem2_GENERIC_ELECTIVE(),R.id.NEPsem2Fragment));

        vac.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem2_VAC(),R.id.NEPsem2Fragment));*/


        // Initialize views
        TabLayout tabLayout = findViewById(R.id.nep_sem2_notes_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.nep_sem2_notes_PageViewer);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sem2_Paper1_DSC(), "Paper 1");
        vpAdapter.addFragment(new Sem2_Paper2_DCS(), "Paper 2");
        vpAdapter.addFragment(new Sem2_AEC(), "AEC");
        vpAdapter.addFragment(new Sem2_GENERIC_ELECTIVE(), "GE");
        vpAdapter.addFragment(new Sem2_SEC(), "SEC");
        vpAdapter.addFragment(new Sem2_VAC(), "VAC");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);


    }
}
