package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dusol.thelearnerscommunity.FunctionManager.functionManager;
import com.dusol.thelearnerscommunity.QuestionPapers.Bcom.sem1QuestionPaperBcom;
import com.dusol.thelearnerscommunity.QuestionPapers.Bcom.sem2QuestionPaperBcom;
import com.dusol.thelearnerscommunity.QuestionPapers.Bcom.sem3QuestionPaperBcom;
import com.dusol.thelearnerscommunity.QuestionPapers.Bcom.sem4QuestionPaperBcom;
import com.dusol.thelearnerscommunity.QuestionPapers.Bcom.sem5QuestionPaperBcom;
import com.dusol.thelearnerscommunity.QuestionPapers.Bcom.sem6QuestionPaperBcom;
import com.google.android.material.tabs.TabLayout;

public class Bcom_QP_TabLayout_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcom_qp_tab_layout);

        TextView textView=findViewById(R.id.textBannerMarquee);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true); //Run Marquee

        // Log the intent to see if it's correctly passed
        String notificationMessage = getIntent().getStringExtra("notificationMessage");
        Log.d("BA_QP_TabLayout", "Notification message: " + notificationMessage);

        // Initialize views
        TabLayout tabLayout = findViewById(R.id.Bcom_QP_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.QP_ViewPager);  // Correct initialization
        ImageButton Telegram = findViewById(R.id.shareOnTelegram);

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_QP_Adapter vpQPAdapter = new VP_QP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpQPAdapter.addFragment(new sem1QuestionPaperBcom(), "1st");
        vpQPAdapter.addFragment(new sem2QuestionPaperBcom(), "2nd");
        vpQPAdapter.addFragment(new sem3QuestionPaperBcom(), "3rd");
        vpQPAdapter.addFragment(new sem4QuestionPaperBcom(), "4th");
        vpQPAdapter.addFragment(new sem5QuestionPaperBcom(), "5th");
        vpQPAdapter.addFragment(new sem6QuestionPaperBcom(), "6th");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpQPAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

        Telegram.setOnClickListener(view -> {
            functionManager.openShareQP_Group(Bcom_QP_TabLayout_Activity.this);
        });
    }
}