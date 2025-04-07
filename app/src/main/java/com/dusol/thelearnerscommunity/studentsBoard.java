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

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class studentsBoard extends AppCompatActivity {

    String link1;
    String link2;
    String link3;
    String link4;
    String link5;
    String link6;
    String link7;
    String link8;
    String TitleAnsYear;
    String sem1Title;
    String sem2Title;
    String sem3Title;

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
        Button ExtraInfo3 = findViewById(R.id.ExtraInfoBtn3);
        Button ExtraInfo4 = findViewById(R.id.ExtraInfoBtn4);
        Button ExtraInfo5 = findViewById(R.id.ExtraInfoBtn5);
        Button ExtraInfo6 = findViewById(R.id.ExtraInfoBtn6);
        Button ExtraInfo7 = findViewById(R.id.ExtraInfoBtn7);
        Button ExtraInfo8 = findViewById(R.id.ExtraInfoBtn8);
        Button ExtraInfo9 = findViewById(R.id.ExtraInfoBtn9);
        Button ExtraInfo10 = findViewById(R.id.ExtraInfoBtn10);
        Button GetStudyMaterial = findViewById(R.id.GetStudyMaterial);
        Button knowYourBarcode = findViewById(R.id.button9_knowYourBC);
        Button ProvisionalCertificate = findViewById(R.id.button10_ProvisionalCertificate);


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
            Intent intent = new Intent(getApplicationContext(), DU_SOL_NOTES__MainActivity.class);
            startActivity(intent);
        });

        feeStructure.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics("feeStructure_Opens");
            Intent intent = new Intent(getApplicationContext(), open_site_webpage.class);
            intent.putExtra("link", "https://sol.du.ac.in/admission_23_24/fee_structure.html");
            startActivity(intent);
        });

        StudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToFirebaseAnalytics("StudentLogin_Opens");
                openWebPage("https://web.sol.du.ac.in/student-login");
            }
        });


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

        StudentResult.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics("StudentResult_Opens");
            openWebPage(AllResultLink[0]);
        }); //


        // Button to open Academic Calendar
        AcademicCalendar.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics("AcademicCalendar_Opens");
            startActivity(new Intent(this, Academic_Calendar_MainActivity.class));
        });


        DatabaseReference NewResultLinks = database.getReference("Links").child("NewPublishedResultLinks");
        ArrayList<String> newResultLink = new ArrayList<>();
        ArrayList<String> newResultName = new ArrayList<>();

        NewResultLinks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot == null)) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        newResultName.add(data.getKey());
                        newResultLink.add(data.getValue(String.class));
                        Log.d("ResultLinks", data.getValue(String.class));
                    }
                }

                if (!newResultLink.get(0).equals("N/A")) {
                    SOL_New_result1.setText(newResultName.get(0));
                    SOL_New_result1.setVisibility(View.VISIBLE);
                } else {
                    SOL_New_result1.setVisibility(View.GONE);
                }

                if (!newResultLink.get(1).equals("N/A")) {
                    SOL_New_result2.setText(newResultName.get(1));
                    SOL_New_result2.setVisibility(View.VISIBLE);
                } else {
                    SOL_New_result2.setVisibility(View.GONE);
                }

                if (!newResultLink.get(2).equals("N/A")) {
                    SOL_New_result3.setText(newResultName.get(2));
                    SOL_New_result3.setVisibility(View.VISIBLE);
                } else {
                    SOL_New_result3.setVisibility(View.GONE);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DatabaseError", "The read failed: " + error.getCode());

            }
        });
        SOL_New_result1.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newResultName.get(0));
            openWebPage(newResultLink.get(0));
        });

        SOL_New_result2.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newResultName.get(1));
            openWebPage(newResultLink.get(1));
        });

        SOL_New_result3.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newResultName.get(2));
            openWebPage(newResultLink.get(2));
        });


        //Extra Info Button

        DatabaseReference Extra1 = database.getReference("Links").child("ExtraInfo");
        ArrayList<String> newInfoLink = new ArrayList<>();
        ArrayList<String> newInfoName = new ArrayList<>();

        Extra1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        newInfoName.add(data.getKey());
                        newInfoLink.add(data.getValue(String.class));

                        Log.d("CheckStatus", Objects.requireNonNull(data.getValue(String.class)));
                    }
                }

                // Create a list of buttons
                List<Button> extraInfoButtons = Arrays.asList(
                        ExtraInfo1, ExtraInfo2, ExtraInfo3, ExtraInfo4, ExtraInfo5,
                        ExtraInfo6, ExtraInfo7, ExtraInfo8, ExtraInfo9
                );

                // Ensure the loop doesn't exceed the number of buttons
                int maxButtons = Math.min(newInfoLink.size(), extraInfoButtons.size());

                // Iterate and update buttons
                for (int i = 0; i < maxButtons; i++) {
                    if (!newInfoLink.get(i).equals("N/A")) {
                        extraInfoButtons.get(i).setText(newInfoName.get(i)); // Set button text
                        extraInfoButtons.get(i).setVisibility(View.VISIBLE); // Make button visible
                    } else {
                        extraInfoButtons.get(i).setVisibility(View.GONE); // Hide button
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DatabaseError", "The read failed: " + error.getCode());

            }
        });


        ExtraInfo1.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(0));
            openWebPage(newInfoLink.get(0));
        });

        ExtraInfo2.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(1));
            openWebPage(newInfoLink.get(1));
        });

        ExtraInfo3.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(2));
            openWebPage(newInfoLink.get(2));
        });

        ExtraInfo4.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(3));
            openWebPage(newInfoLink.get(3));
        });

        ExtraInfo5.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(4));
            openWebPage(newInfoLink.get(4));
        });

        ExtraInfo6.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(5));
            openWebPage(newInfoLink.get(5));
        });

        ExtraInfo7.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(6));
            openWebPage(newInfoLink.get(6));
        });

        ExtraInfo8.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(7));
            openWebPage(newInfoLink.get(7));
        });

        ExtraInfo9.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(8));
            openWebPage(newInfoLink.get(8));
        });

        ExtraInfo10.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics(newInfoName.get(9));
            openWebPage(newInfoLink.get(9));
        });


        // Button New Admission Link
        DatabaseReference NewAdmissionLink = FirebaseDatabase.getInstance().getReference("Links");
        NewAdmissionLink.child("admissionlink").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                AdmissionLink.setOnClickListener(v -> {
                    sendDataToFirebaseAnalytics("NewAdmissionLink_Opens");
                    openWebPage(data);
                });
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
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.hasChildren()) {
                    for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                        String value = dateSnapshot.getValue(String.class);
                        datesheetList.add(value);
                        assert value != null;
//                        Log.d("DateSheetData", value);
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
                    DateSheet.setOnClickListener(v -> {
                        sendDataToFirebaseAnalytics("DateSheet_Opens");
                        showDateSheetDiallog(link1, link2, link3, link4, link5, link6, link7, link8, TitleAnsYear);
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DBError", "Error ");
            }
        });


        //feeStructure


        // Button Admit Card
        DatabaseReference AdmitCardLinks = FirebaseDatabase.getInstance().getReference("AdmitCard");
        List<String> admitcardLinks = new ArrayList<>();
        AdmitCardLinks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                    AdmitCard.setOnClickListener(v -> {
                        sendDataToFirebaseAnalytics("AdmitCard_Opens");
                        showAdmitCard(AC_Link1, AC_Link2, AC_Link3, AC_Semtitle1, AC_Semtitle2, AC_Semtitle3, AC_yeartitle);
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DBError", "Error ");
            }

        });

        // Button Next Semester Subject
        AllSemSub.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics("NextSemesterSubject_Opens");
            openWebPage("https://web.sol.du.ac.in/info/ug-course-structure-based-on-nep-semester-i");
        });

        // Button SOL Degree
        SOL_Degree.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics("SOL_Degree_Opens");
            openWebPage("https://durslt.du.ac.in/AC_INTERNET_INDEX/Online_Fee_Payment/Std_Rslt_Index.aspx");
        });

        knowYourBarcode.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics("KnowYourBarcode_Opens");
            openWebPage("https://web.sol.du.ac.in/misc/student_info/kyb_24.php");
        });

        ProvisionalCertificate.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics("ProvisionalCertificate_Opens");
            openWebPage("https://web.sol.du.ac.in/info/provisional");
        });

        GetStudyMaterial.setOnClickListener(v -> {
            sendDataToFirebaseAnalytics("GetStudyMaterial_Opens");
            openWebPage("https://web.sol.du.ac.in/info/nep-resources");
        });

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

    private void showDateSheetDiallog(String link1, String link2, String link3, String link4, String link5, String link6, String link7, String link8, String titleAndYear) {
        DialogFragment dialogFragment = Datesheet_DailogBox.newInstance(link1, link2, link3, link4, link5, link6, link7, link8, titleAndYear);
        dialogFragment.show(getSupportFragmentManager(), "DateSheetLinks");
    }

    private void showAdmitCard(String AC_Link1, String AC_Link2, String AC_Link3, String AC_SemTitle1, String AC_SemTitle2, String AC_SemTitle3, String AC_yearTitle) {
        DialogFragment dialogFragment = AdmitCardDailogBox.newInstance(AC_Link1, AC_Link2, AC_Link3, AC_SemTitle1, AC_SemTitle2, AC_SemTitle3, AC_yearTitle);
        dialogFragment.show(getSupportFragmentManager(), "AdmitCardLinks");
    }

    public void sendDataToFirebaseAnalytics(String operationName) {
        if (operationName == null || operationName.isEmpty()) {
            Log.e("AnalyticsCheckLog", "Operation name is null or empty. Skipping log.");
        } else {
            Log.d("AnalyticsCheckLog", "Event sent: " + operationName);
            FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle bundle = new Bundle();
            bundle.putString("operation_name", operationName);
            firebaseAnalytics.logEvent("StudentPortal", bundle); // Use consistent lowercase event name

        }
    }


}

