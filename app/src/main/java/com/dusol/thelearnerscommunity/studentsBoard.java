/**
 * App developed by:
 * Ashu Sriwastav
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class studentsBoard extends AppCompatActivity {

    String link1; String link2; String link3; String link4;String link5;String link6;String link7;String link8; String TitleAnsYear;
    String sem1title; String sem2title; String sem3title;

    private String AC_Link1, AC_Link2, AC_Link3, AC_Semtitle1, AC_Semtitle2, AC_Semtitle3, AC_yeartitle;

    @Override
    public void onBackPressed() {  // send user to homepage and clear back stack
        // Call finish() to close the activity when the back button is pressed
        super.onBackPressed();
        Intent intent = new Intent(this, LinkPage_MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_board);

        // Initialize UI elements
        Button StudentLogin = findViewById(R.id.button1_Student_Login);
        Button StudentResult = findViewById(R.id.button2_Student_Result);
        Button AcademicCalendar = findViewById(R.id.button3_Academic_Calendar);
        Button AdmissionLink = findViewById(R.id.button4_Admission_Link);
        Button AllSemSub = findViewById(R.id.button5_All_semester_subject);
        Button DateSheet = findViewById(R.id.button6_Date_sheet);
        Button AdmitCard = findViewById(R.id.button7_Admit_Card);
        Button SOL_Degree = findViewById(R.id.button8_Sol_Degree);
        Button feeStructure = findViewById(R.id.feeStructure);
        Button SOL_New_result1 = findViewById(R.id.Student_new_Result1);
        Button SOL_New_result2 = findViewById(R.id.Student_new_Result2);
        Button SOL_New_result3 = findViewById(R.id.Student_new_Result3);
        Button ExtraInfo1 = findViewById(R.id.ExtraInfoBtn1);
        Button ExtraInfo2 = findViewById(R.id.ExtraInfoBtn2);

        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);

        // Firebase database instance
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Navigation Bar Button Actions

        NavHome.setOnClickListener(view -> { //Home
            Intent intent = new Intent(getApplicationContext(), LinkPage_MainActivity.class);
            startActivity(intent);
        });

        NavVideos.setOnClickListener(view -> { //YT channel
            String youtubeChannelUrl = "https://www.youtube.com/@TheLearnersCommunityDUSOL/videos";
            Uri youtubeUri = Uri.parse(youtubeChannelUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);
            intent.setPackage("com.google.android.youtube");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeChannelUrl)));
            }
        });

        NavBooks.setOnClickListener(view -> { // notes page
            Intent intent = new Intent(getApplicationContext(), Semester_Select_MainActivity.class);
            startActivity(intent);
        });


        feeStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),open_site_webpage.class);
                intent.putExtra("link","https://sol.du.ac.in/admission_23_24/fee_structure.html");
                startActivity(intent);
            }
        });

        // Button Student Login Page
        StudentLogin.setOnClickListener(v -> openWebPage("https://web.sol.du.ac.in/student-login"));

        // Button check All Result Link
        final String[] AllResultLink = {""};
        DatabaseReference RTDBAllResultLink = database.getReference("Links").child("AllResult");
        RTDBAllResultLink.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                AllResultLink[0] = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DatabaseError", "The read failed: " + databaseError.getCode());
            }
        });

        StudentResult.setOnClickListener(v -> openWebPage(AllResultLink[0])); //


        // Button to open Academic Calendar
        AcademicCalendar.setOnClickListener(v -> startActivity(new Intent(this, Academic_Calendar_MainActivity.class)));


        DatabaseReference NewResultLinks = database.getReference("Links").child("NewPublishedResultLinks");
        ArrayList<String> newResultLink = new ArrayList<>();
        ArrayList<String> newResultName = new ArrayList<>();

        NewResultLinks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot == null)){
                for(DataSnapshot data:snapshot.getChildren()){
                    newResultName.add(data.getKey());
                    newResultLink.add(data.getValue(String.class));
                    Log.d("ResultLinks",data.getValue(String.class));
                }}

                if (!newResultLink.get(0).equals("N/A")){
                    SOL_New_result1.setText(newResultName.get(0));
                    SOL_New_result1.setVisibility(View.VISIBLE);
                }else {
                    SOL_New_result1.setVisibility(View.GONE);
                }

                if (!newResultLink.get(1).equals("N/A")){
                    SOL_New_result2.setText(newResultName.get(1));
                    SOL_New_result2.setVisibility(View.VISIBLE);
                }else {
                    SOL_New_result2.setVisibility(View.GONE);
                }

                if (!newResultLink.get(2).equals("N/A")){
                    SOL_New_result3.setText(newResultName.get(2));
                    SOL_New_result3.setVisibility(View.VISIBLE);
                }else{
                    SOL_New_result3.setVisibility(View.GONE);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DatabaseError", "The read failed: " + error.getCode());

            }
        });
        SOL_New_result1.setOnClickListener(v -> openWebPage(newResultLink.get(0)));
        SOL_New_result2.setOnClickListener(v -> openWebPage(newResultLink.get(1)));
        SOL_New_result3.setOnClickListener(v -> openWebPage(newResultLink.get(2)));




        //Extra Info Button

        DatabaseReference Extra1 = database.getReference("Links").child("ExtraInfo");
        ArrayList<String> newInfoLink = new ArrayList<>();
        ArrayList<String> newInfoName = new ArrayList<>();

        Extra1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot == null)){
                    for(DataSnapshot data:snapshot.getChildren()){
                        newInfoName.add(data.getKey());
                        newInfoLink.add(data.getValue(String.class));
                    }}

                if (!newInfoLink.get(0).equals("N/A")){
                    ExtraInfo1.setText(newInfoName.get(0));
                    ExtraInfo1.setVisibility(View.VISIBLE);
                }else {
                    ExtraInfo1.setVisibility(View.GONE);
                }

                if (!newInfoLink.get(1).equals("N/A")){
                    ExtraInfo2.setText(newInfoName.get(1));
                    ExtraInfo2.setVisibility(View.VISIBLE);
                }else {
                    ExtraInfo2.setVisibility(View.GONE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DatabaseError", "The read failed: " + error.getCode());

            }
        });



        ExtraInfo1.setOnClickListener(v -> openWebPage(newInfoLink.get(0)));
        ExtraInfo2.setOnClickListener(v -> openWebPage(newInfoLink.get(1)));


        // Button New Admission Link
        DatabaseReference NewAdmissionLink = FirebaseDatabase.getInstance().getReference("Links");
        NewAdmissionLink.child("admissionlink").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                AdmissionLink.setOnClickListener(v -> openWebPage(data));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DBError", "Error ");
            }
        });



        // Button Date Sheet
        DatabaseReference DateSheetLinks = FirebaseDatabase.getInstance().getReference("ExamDateSheet");
        List<String> datesheetList = new ArrayList<>();
        DateSheetLinks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.hasChildren()) {
                    for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                        String value = dateSnapshot.getValue(String.class);
                        datesheetList.add(value);
                        Log.d("DatesheetData",value);
                    }


                    link1 = datesheetList.get(0);
                    link2 = datesheetList.get(1);
                    link3 = datesheetList.get(2);
                    link4 = datesheetList.get(3);

                    link5 = datesheetList.get(4);
                    link6 = datesheetList.get(5);
                    link7 = datesheetList.get(6);
                    link8 = datesheetList.get(7);

                    TitleAnsYear = datesheetList.get(8);
                    DateSheet.setOnClickListener(v -> showDateSheetDiallog(link1, link2, link3, link4,link5,link6,link7,link8, TitleAnsYear));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DBError", "Error ");
            }
        });


        //feeStructure



        // Button Admit Card
        DatabaseReference AdmitCardLinks = FirebaseDatabase.getInstance().getReference("AdmitCard");
        List<String> admitcardLinks = new ArrayList<>();
        AdmitCardLinks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.hasChildren()) {
                    for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                        String value = dateSnapshot.getValue(String.class);
                        admitcardLinks.add(value);
                    }
                    AC_Link1 = admitcardLinks.get(0);
                    AC_Link2 = admitcardLinks.get(1);
                    AC_Link3 = admitcardLinks.get(2);
                    AC_Semtitle1 = admitcardLinks.get(3);
                    AC_Semtitle2 = admitcardLinks.get(4);
                    AC_Semtitle3 = admitcardLinks.get(5);
                    AC_yeartitle = admitcardLinks.get(6);
                    AdmitCard.setOnClickListener(v -> showAdmitCard(AC_Link1, AC_Link2, AC_Link3, AC_Semtitle1, AC_Semtitle2, AC_Semtitle3, AC_yeartitle));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DBError", "Error ");
            }
        });

        // Button Next Semester Subject
        AllSemSub.setOnClickListener(v -> openWebPage("https://web.sol.du.ac.in/info/study_materials_new"));

        // Button SOL Degree
        SOL_Degree.setOnClickListener(v -> openWebPage("https://durslt.du.ac.in/AC_INTERNET_INDEX/Online_Fee_Payment/Std_Rslt_Index.aspx"));
    }

    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        if (!activities.isEmpty() && intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If no activity found, show a message to the user
            Toast.makeText(this, "No application found to open this link", Toast.LENGTH_LONG).show();
        }
    }

    private void showDateSheetDiallog(String link1, String link2, String link3, String link4,String link5,String link6,String link7,String link8, String titleAndYear) {
        DialogFragment dialogFragment = Datesheet_DailogBox.newInstance(link1, link2, link3, link4, link5, link6, link7, link8, titleAndYear);
        dialogFragment.show(getSupportFragmentManager(), "DateSheetLinks");
    }

    private void showAdmitCard(String AC_Link1, String AC_Link2, String AC_Link3, String AC_Semtitle1, String AC_Semtitle2, String AC_Semtitle3, String AC_yeartitle) {
        DialogFragment dialogFragment = AdmitCardDailogBox.newInstance(AC_Link1, AC_Link2, AC_Link3, AC_Semtitle1, AC_Semtitle2, AC_Semtitle3, AC_yeartitle);
        dialogFragment.show(getSupportFragmentManager(), "AdmitCardLinks");
    }
}

