package com.dusol.thelearnerscommunity.NotesStoreManage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments.semester1PaidNotesFragment;
import com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments.semester2PaidNotesFragment;
import com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments.semester3PaidNotesFragment;
import com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments.semester4PaidNotesFragment;
import com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments.semester5PaidNotesFragment;
import com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments.semester6PaidNotesFragment;
import com.dusol.thelearnerscommunity.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class NotesStoreTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_store_tab);

        TabLayout tabLayout=findViewById(R.id.questionPaperTabLayout);
        ViewPager2 viewPager=findViewById(R.id.questionPaperTabLayoutViewPager);

        FragmentActivity fragmentActivity=this;

        VP2_PaidNotes_Adapter vpAdapter = new VP2_PaidNotes_Adapter(fragmentActivity);
        vpAdapter.addFragment(new semester1PaidNotesFragment(), "1st");
        vpAdapter.addFragment(new semester2PaidNotesFragment(), "2nd");
        vpAdapter.addFragment(new semester3PaidNotesFragment(), "3rd");
        vpAdapter.addFragment(new semester4PaidNotesFragment(), "4th");
        vpAdapter.addFragment(new semester5PaidNotesFragment(), "5th");
        vpAdapter.addFragment(new semester6PaidNotesFragment(), "6th");

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(vpAdapter);

        // Link the TabLayout with the ViewPager
// Link TabLayout with ViewPager2 using TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(vpAdapter.getPageTitle(position))
        ).attach();
    }
}