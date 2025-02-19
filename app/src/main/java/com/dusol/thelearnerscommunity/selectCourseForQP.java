package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class selectCourseForQP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course_for_qp);

        Button baProgQPCourse=findViewById(R.id.baQuestionPaperCourse);

        baProgQPCourse.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), BA_QP_TabLayout_Activity.class);
            startActivity(intent);
        });

    }

}