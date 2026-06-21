/**
 * App developed by:
 * Ashu Sriwastav
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */

package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.dusol.thelearnerscommunity.QuestionPapers.BAProg.semester1QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.BAProg.semester2QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.BAProg.semester3QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.BAProg.semester4QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.BAProg.semester5QuestionPapers;
import com.dusol.thelearnerscommunity.QuestionPapers.BAProg.semester6QuestionPapers;

public class QuestionPapers_MainActivity extends AppCompatActivity {

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
    } // Important function that clears the stack and sends you back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_papers_main);

        // Get all Buttons
        Button sem1 = findViewById(R.id.sem1QP);
        Button sem2 = findViewById(R.id.sem2QP);
        Button sem3 = findViewById(R.id.sem3QP);
        Button sem4 = findViewById(R.id.sem4QP);
        Button sem5 = findViewById(R.id.sem5QP);
        Button sem6 = findViewById(R.id.sem6QP);

        // Setup bottom navigation bar

        // Marquee text setup
        TextView textView = findViewById(R.id.textBannerMarquee);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true); // Run Marquee

        // Semester 1 - Question Papers
        sem1.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            semester1QuestionPapers sem1Fragment = new semester1QuestionPapers(); // Create new instance of the fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, sem1Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem1") // Name for the back stack entry
                    .commit();
        });

        // Semester 2 - Question Papers
        sem2.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            semester2QuestionPapers sem2Fragment = new semester2QuestionPapers(); // Create new instance of the fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, sem2Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem2") // Name for the back stack entry
                    .commit();
        });

        // Semester 3 - Question Papers
        sem3.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            semester3QuestionPapers sem3Fragment = new semester3QuestionPapers(); // Create new instance of the fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, sem3Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem3") // Name for the back stack entry
                    .commit();
        });

        // Semester 4 - Question Papers
        sem4.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            semester4QuestionPapers sem4Fragment = new semester4QuestionPapers(); // Create new instance of the fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, sem4Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem4") // Name for the back stack entry
                    .commit();
        });

        // Semester 5 - Question Papers
        sem5.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            semester5QuestionPapers sem5Fragment = new semester5QuestionPapers(); // Create new instance of the fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, sem5Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem5") // Name for the back stack entry
                    .commit();
        });

        // Semester 6 - Question Papers
        sem6.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            semester6QuestionPapers sem6Fragment = new semester6QuestionPapers(); // Create new instance of the fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, sem6Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem6") // Name for the back stack entry
                    .commit();
        });
    }
}
