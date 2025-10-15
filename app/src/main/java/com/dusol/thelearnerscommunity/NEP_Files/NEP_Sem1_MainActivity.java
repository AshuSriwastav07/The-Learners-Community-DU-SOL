package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_Paper_1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_Paper_2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_VAC;
import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.VP_Adapter;
import com.google.android.material.tabs.TabLayout;

public class NEP_Sem1_MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // Close the activity immediately without managing back stack
        super.onBackPressed();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nep_sem1_main);

        /* Subject Select Buttons

        Button paper1=findViewById(R.id.paper1DSC);
        Button paper2=findViewById(R.id.paper2DSC);
        Button aec=findViewById(R.id.aec);
        Button ge=findViewById(R.id.GenericElective);
        Button sec=findViewById(R.id.sec);
        Button vac=findViewById(R.id.vac);*/
        /* FragmentActivity context=this;

        paper1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_Paper_1_DSC(),R.id.NEPsem1Fragment);
            }
        });

        paper2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_Paper_2_DSC(),R.id.NEPsem1Fragment);
            }
        });


        aec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_AEC(),R.id.NEPsem1Fragment);
            }
        });

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_SEC(),R.id.NEPsem1Fragment);
            }
        });

        ge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_GENERIC_ELECTIVE(),R.id.NEPsem1Fragment);
            }
        });
        vac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_VAC(),R.id.NEPsem1Fragment);
            }
        });*/


// Initialize views
        TabLayout tabLayout = findViewById(R.id.nep_sem1_notes_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.nep_sem1_notes_PageViewer);  // Correct initialization
        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sem1_Paper_1_DSC(), "Paper 1");
        vpAdapter.addFragment(new Sem1_Paper_2_DSC(), "Paper 2");
        vpAdapter.addFragment(new Sem1_AEC(), "AEC");
        vpAdapter.addFragment(new Sem1_GENERIC_ELECTIVE(), "GE");
        vpAdapter.addFragment(new Sem1_SEC(), "SEC");
        vpAdapter.addFragment(new Sem1_VAC(), "VAC");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);


    }
}