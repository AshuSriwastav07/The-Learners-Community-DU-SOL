package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.QuestionPapers.semester1QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.semester2QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.semester3QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.semester4QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.semester5QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.semester6QuestionPapers;
import com.google.android.material.tabs.TabLayout;

public class QP_TabLayout_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qp_tab_layout);

        TextView textView=findViewById(R.id.textBannerMarquee);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true); //Run Marquee


        // Initialize views
        TabLayout tabLayout = findViewById(R.id.QP_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.QP_ViewPager);  // Correct initialization

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new semester1QuestionPapers(), "1st");
        vpAdapter.addFragment(new semester2QuestionPapers(), "2nd");
        vpAdapter.addFragment(new semester3QuestionPapers(), "3rd");
        vpAdapter.addFragment(new semester4QuestionPapers(), "4th");
        vpAdapter.addFragment(new semester5QuestionPapers(), "5th");
        vpAdapter.addFragment(new semester6QuestionPapers(), "6th");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

    }
}
