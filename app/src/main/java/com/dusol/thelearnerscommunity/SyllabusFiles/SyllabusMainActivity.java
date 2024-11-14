package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.dusol.thelearnerscommunity.R;

public class SyllabusMainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_syllabus_main2);

        // Get all Buttons
        Button sem1Syllabus = findViewById(R.id.sem1Syllabus);
        Button sem2Syllabus = findViewById(R.id.sem2Syllabus);
        Button sem3Syllabus = findViewById(R.id.sem3Syllabus);
        Button sem4Syllabus = findViewById(R.id.sem4Syllabus);
        Button sem5Syllabus = findViewById(R.id.sem5Syllabus);
        Button sem6Syllabus = findViewById(R.id.sem6Syllabus);

        // Button 1 - Sem 1 Syllabus
        sem1Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Sem1SyllabusFragment sem1Fragment = new Sem1SyllabusFragment(); // Create new instance of Sem1SyllabusFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, sem1Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem1Syllabus") // Name for the back stack entry
                    .commit();
        });

        // Button 2 - Sem 2 Syllabus
        sem2Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Sem2SyllabusFragment sem2Fragment = new Sem2SyllabusFragment(); // Create new instance of Sem2SyllabusFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, sem2Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem2Syllabus") // Name for the back stack entry
                    .commit();
        });

        // Button 3 - Sem 3 Syllabus
        sem3Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Sem3SyllabusFragment sem3Fragment = new Sem3SyllabusFragment(); // Create new instance of Sem3SyllabusFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, sem3Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem3Syllabus") // Name for the back stack entry
                    .commit();
        });

        // Button 4 - Sem 4 Syllabus
        sem4Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Sem4SyllabusFragment sem4Fragment = new Sem4SyllabusFragment(); // Create new instance of Sem4SyllabusFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, sem4Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem4Syllabus") // Name for the back stack entry
                    .commit();
        });

        // Button 5 - Sem 5 Syllabus
        sem5Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Sem5SyllabusFragment sem5Fragment = new Sem5SyllabusFragment(); // Create new instance of Sem5SyllabusFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, sem5Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem5Syllabus") // Name for the back stack entry
                    .commit();
        });

        // Button 6 - Sem 6 Syllabus
        sem6Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Sem6SyllabusFragment sem6Fragment = new Sem6SyllabusFragment(); // Create new instance of Sem6SyllabusFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, sem6Fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem6Syllabus") // Name for the back stack entry
                    .commit();
        });

    }
}
