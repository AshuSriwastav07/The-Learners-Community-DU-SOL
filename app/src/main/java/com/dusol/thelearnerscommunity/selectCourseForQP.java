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
        Button baHonsQPCourse=findViewById(R.id.baHonsQuestionPaperCourse);
        Button bcomQPCourse=findViewById(R.id.bcomQuestionPaperCourse);
        Button bcomHonsHonsQPCourse=findViewById(R.id.bcomHonsQuestionPaperCourse);

        baProgQPCourse.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), BA_QP_TabLayout_Activity.class);
            intent.putExtra("course", "BA");
            startActivity(intent);
        });

        baHonsQPCourse.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), BAH_QP_TabLayout_Acitivty.class);
            intent.putExtra("course", "BAH");
            startActivity(intent);
        });

        bcomQPCourse.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), Bcom_QP_TabLayout_Activity.class);
            intent.putExtra("course", "BCOM");
            startActivity(intent);
        });

        bcomHonsHonsQPCourse.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), BcomHons_QP_TabLayout_Activity.class);
            intent.putExtra("course", "BCOMH");
            startActivity(intent);
        });


    }

}