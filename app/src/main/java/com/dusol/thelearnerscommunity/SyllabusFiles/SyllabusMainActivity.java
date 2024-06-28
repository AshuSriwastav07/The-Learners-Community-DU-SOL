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

//        get all Buttons

        Button sem1Syllabus=findViewById(R.id.sem1Syllabus);
        Button sem2Syllabus=findViewById(R.id.sem2Syllabus);
        Button sem3Syllabus=findViewById(R.id.sem3Syllabus);
        Button sem4Syllabus=findViewById(R.id.sem4Syllabus);
        Button sem5Syllabus=findViewById(R.id.sem5Syllabus);
        Button sem6Syllabus=findViewById(R.id.sem6Syllabus);


       sem1Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, Sem1SyllabusFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem1Syllabus") // Name can be null
                    .commit();
        });

       sem2Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, Sem2SyllabusFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem2Syllabus") // Name can be null
                    .commit();
        });

       sem3Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, Sem3SyllabusFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem3Syllabus") // Name can be null
                    .commit();
        });

       sem4Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, Sem4SyllabusFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem4Syllabus") // Name can be null
                    .commit();
        });

       sem5Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, Sem5SyllabusFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem5Syllabus") // Name can be null
                    .commit();
        });

       sem6Syllabus.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.SyllabusfragmentContainerView, Sem6SyllabusFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Sem6Syllabus") // Name can be null
                    .commit();
        });


    }

}