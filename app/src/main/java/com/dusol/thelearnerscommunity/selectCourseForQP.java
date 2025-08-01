package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class selectCourseForQP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course_for_qp);

        CardView baProgQPCourse=findViewById(R.id.baQuestionPaperCourse);
        CardView baHonsQPCourse=findViewById(R.id.baHonsQuestionPaperCourse);
        CardView bcomQPCourse=findViewById(R.id.bcomQuestionPaperCourse);
        CardView bcomHonsHonsQPCourse=findViewById(R.id.bcomHonsQuestionPaperCourse);

        TextView TotalBAQP=findViewById(R.id.TotalBAQP);
        TextView TotalBAHQP=findViewById(R.id.TotalBAHQP);
        TextView TotalBCOMQP=findViewById(R.id.TotalBCOMQP);
        TextView TotalBCOMHQP=findViewById(R.id.TotalBCOMHQP);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("QuestionPapers");

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalBA = 0, totalBAH = 0, totalBCOM = 0, totalBCOMH = 0;

                for (DataSnapshot semSnapshot : snapshot.getChildren()) {
                    // Loop through Sem1, Sem2, ..., Sem6

                    DataSnapshot baSnap = semSnapshot.child("BA");
                    DataSnapshot bahSnap = semSnapshot.child("BAH");
                    DataSnapshot bcomSnap = semSnapshot.child("BCOM");
                    DataSnapshot bomHSnap = semSnapshot.child("BCOMH");

                    totalBA += (int) baSnap.getChildrenCount();
                    totalBAH += (int) bahSnap.getChildrenCount();
                    totalBCOM += (int) bcomSnap.getChildrenCount();
                    totalBCOMH += (int) bomHSnap.getChildrenCount();
                }

                TotalBAQP.setText(String.valueOf(totalBA));
                TotalBAHQP.setText(String.valueOf(totalBAH));
                TotalBCOMQP.setText(String.valueOf(totalBCOM));
                TotalBCOMHQP.setText(String.valueOf(totalBCOMH));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DB_ERROR", error.getMessage());
            }
        });

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