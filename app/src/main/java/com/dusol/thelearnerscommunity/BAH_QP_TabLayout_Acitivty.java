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
import com.dusol.thelearnerscommunity.QuestionPapers.BAHons.sem1QuestionPaperBAHons;
import com.dusol.thelearnerscommunity.QuestionPapers.BAHons.sem2QuestionPaperBAHons;
import com.dusol.thelearnerscommunity.QuestionPapers.BAHons.sem3QuestionPaperBAHons;
import com.dusol.thelearnerscommunity.QuestionPapers.BAHons.sem4QuestionPaperBAHons;
import com.dusol.thelearnerscommunity.QuestionPapers.BAHons.sem5QuestionPaperBAHons;
import com.dusol.thelearnerscommunity.QuestionPapers.BAHons.sem6QuestionPaperBAHons;
import com.google.android.material.tabs.TabLayout;

public class BAH_QP_TabLayout_Acitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bah_qp_tab_layout_acitivty);

        TextView textView=findViewById(R.id.textBannerMarquee);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true); //Run Marquee

        // Log the intent to see if it's correctly passed
        String notificationMessage = getIntent().getStringExtra("notificationMessage");
        Log.d("BA_QP_TabLayout", "Notification message: " + notificationMessage);

        // Initialize views
        TabLayout tabLayout = findViewById(R.id.BAH_QP_TabLayout);  // Correct initialization
        ViewPager viewPager = findViewById(R.id.BAH_QP_ViewPager);  // Correct initialization
        ImageButton Telegram = findViewById(R.id.shareOnTelegram);

        // Create the adapter that will return a fragment for each of the primary sections of the activity
        VP_Adapter vpAdapter = new VP_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new sem1QuestionPaperBAHons(), "1st");
        vpAdapter.addFragment(new sem2QuestionPaperBAHons(), "2nd");
        vpAdapter.addFragment(new sem3QuestionPaperBAHons(), "3rd");
        vpAdapter.addFragment(new sem4QuestionPaperBAHons(), "4th");
        vpAdapter.addFragment(new sem5QuestionPaperBAHons(), "5th");
        vpAdapter.addFragment(new sem6QuestionPaperBAHons(), "6th");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

        Telegram.setOnClickListener(view -> functionManager.openShareQP_Group(BAH_QP_TabLayout_Acitivty.this));
    }
}
