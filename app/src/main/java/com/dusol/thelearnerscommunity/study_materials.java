package com.dusol.thelearnerscommunity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class study_materials extends AppCompatActivity {

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
        setContentView(R.layout.activity_study_materials);

        /*Button ba_btn1=findViewById(R.id.BA_btn1);
        Button ba_polHons_btn2=findViewById(R.id.BA_PolHons_btn2);
        Button ba_engHons_btn3=findViewById(R.id.BA_EngHons_btm3);
        Button Bcom_btn4=findViewById(R.id.BCom_btn4);
        Button BcomHons_btn5=findViewById(R.id.BComHons_btn5);
        Button BAEco_btn6=findViewById(R.id.BAEconomics_btn6);
        Button BAPsybtn7=findViewById(R.id.BA_PSY_btn7);



        ba_btn1.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.Materials_fragmentContainerView, BA_ProgSM.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        });

        ba_polHons_btn2.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.Materials_fragmentContainerView, BA_PolHons_SM.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        ba_engHons_btn3.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.Materials_fragmentContainerView, BA_EngHonsSM.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        Bcom_btn4.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.Materials_fragmentContainerView, BCom_SM.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        BcomHons_btn5.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.Materials_fragmentContainerView, BCom_HonsSM.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        BAEco_btn6.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.Materials_fragmentContainerView, BA_EconomicsHonsSM.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });

        BAPsybtn7.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.Materials_fragmentContainerView, BA_PsyHonsSM.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        });*/

        // Initialize views
        TabLayout tabLayout = findViewById(R.id.studyMaterial_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.studyMaterial_PageViewer);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new BA_ProgSM(), "BA Program");
        vpAdapter.addFragment(new BA_PolHons_SM(), "BA Pol Hons");
        vpAdapter.addFragment(new BA_EngHonsSM(), "BA Eng Hons");
        vpAdapter.addFragment(new BA_PsyHonsSM(), "BA Psy Hons");
        vpAdapter.addFragment(new BA_EconomicsHonsSM(), "BA Eco Hons");
        vpAdapter.addFragment(new BCom_SM(), "B Com");
        vpAdapter.addFragment(new BCom_HonsSM(), "B Com Hons");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);


    }
}