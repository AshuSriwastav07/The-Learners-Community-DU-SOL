package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.ManageFragment;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_Paper_1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_Paper_2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_VAC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem3.Sem3_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem3.Sem3_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem3.Sem3_Paper1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem3.Sem3_Paper2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem3.Sem3_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem3.Sem3_VAC;
import com.dusol.thelearnerscommunity.R;
import com.dusol.thelearnerscommunity.VP_Adapter;
import com.google.android.material.tabs.TabLayout;

public class NEP_Sem3_MainActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        // Close the activity immediately without managing back stack
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nep_sem3_main);


        /*Button paper1=findViewById(R.id.paper1DSC);
        Button paper2=findViewById(R.id.paper2DSC);
        Button aec=findViewById(R.id.aec);
        Button ge=findViewById(R.id.GenericElective);
        Button sec=findViewById(R.id.sec);
        Button vac=findViewById(R.id.vac);


        FragmentActivity context=this;


        paper1.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem3_Paper1_DSC(),R.id.NEPsem3Fragment));

        paper2.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem3_Paper2_DSC(),R.id.NEPsem3Fragment));


        aec.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem3_AEC(),R.id.NEPsem3Fragment));

        sec.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem3_SEC(),R.id.NEPsem3Fragment));

        ge.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem3_GENERIC_ELECTIVE(),R.id.NEPsem3Fragment));

        vac.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem3_VAC(),R.id.NEPsem3Fragment));*/

        // Initialize views
        TabLayout tabLayout = findViewById(R.id.nep_sem3_notes_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.nep_sem3_notes_PageViewer);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sem3_Paper1_DSC(), "Paper 1");
        vpAdapter.addFragment(new Sem3_Paper2_DSC(), "Paper 2");
        vpAdapter.addFragment(new Sem3_AEC(), "AEC");
        vpAdapter.addFragment(new Sem3_GENERIC_ELECTIVE(), "GE");
        vpAdapter.addFragment(new Sem3_SEC(), "SEC");
        vpAdapter.addFragment(new Sem3_VAC(), "VAC");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

    }
}