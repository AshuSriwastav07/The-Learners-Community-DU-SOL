package com.dusol.thelearnerscommunity.FunctionManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem1_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem2_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem3_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem4_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem5_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem6_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem7_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem8_MainActivity;
import com.dusol.thelearnerscommunity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class functionManager {

    // Method to manage "New" sign icons based on Firebase data
    public static void managerNewSignLogo(Activity activity, Context context) {
        // Get Firebase instance and reference the "newUpdatesInfo" node
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("newUpdatesInfo");

        // List to store the update statuses retrieved from Firebase
        List<String> updateList = new ArrayList<>();

        // Firebase real-time event listener
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                updateList.clear(); // Clear list before adding new data to avoid duplicates

                // Loop through each child node and add the value to the list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String value = snapshot.getValue(String.class);
                    updateList.add(value);
                }

                // Log the retrieved values for debugging
//                Log.d("NewSignInfo", updateList.toString());

                try {
                    // Free Notes Update Indicator
                    ImageView newFreeNotes = activity.findViewById(R.id.newNotesUpload);
                    if (!updateList.isEmpty() && Objects.equals(updateList.get(0), "YES")) {
                        setImageViewGif(activity, newFreeNotes, true);
                    } else {
                        setImageViewGif(activity, newFreeNotes, false);
                    }

                    // Paid Notes Update Indicator
                    ImageView newPaidNotes = activity.findViewById(R.id.newPaidNotesUpload);
                    if (updateList.size() > 1 && Objects.equals(updateList.get(1), "YES")) {
                        setImageViewGif(activity, newPaidNotes, true);
                    } else {
                        setImageViewGif(activity, newPaidNotes, false);
                    }

                    // Portal Info Update Indicator
                    ImageView newPortalInfo = activity.findViewById(R.id.newInfoUpload);
                    if (updateList.size() > 2 && Objects.equals(updateList.get(2), "YES")) {
                        setImageViewGif(activity, newPortalInfo, true);
                    } else {
                        setImageViewGif(activity, newPortalInfo, false);
                    }

                    // Study Material Update Indicator
                    ImageView newSM = activity.findViewById(R.id.newStudyMaterialUpload);
                    if (updateList.size() > 3 && Objects.equals(updateList.get(3), "YES")) {
                        setImageViewGif(activity, newSM, true);
                    } else {
                        setImageViewGif(activity, newSM, false);
                    }

                    // Syllabus Update Indicator
                    ImageView newSyllabus = activity.findViewById(R.id.newSyllabusUpload);
                    if (updateList.size() > 4 && Objects.equals(updateList.get(4), "YES")) {
                        setImageViewGif(activity, newSyllabus, true);
                    } else {
                        setImageViewGif(activity, newSyllabus, false);
                    }

                    /* Video Notes Update Indicator
                    ImageView newVideoUpload = activity.findViewById(R.id.newVideoNotesUpload);
                    if (updateList.size() > 5 && Objects.equals(updateList.get(5), "YES")) {
                        setImageViewGif(activity, newVideoUpload, true);
                    } else {
                        setImageViewGif(activity, newVideoUpload, false);
                    }*/

                    // Question Paper Update Indicator
                    ImageView newQP = activity.findViewById(R.id.newQPUpload);
                    if (updateList.size() > 6 && Objects.equals(updateList.get(6), "YES")) {
                        setImageViewGif(activity, newQP, true);
                    } else {
                        setImageViewGif(activity, newQP, false);
                    }

                    // College Update Indicator
                    ImageView newCollageUpdate = activity.findViewById(R.id.newCollageUpdate);
                    if (updateList.size() > 7 && Objects.equals(updateList.get(7), "YES")) {
                        setImageViewGif(activity, newCollageUpdate, true);
                    } else {
                        setImageViewGif(activity, newCollageUpdate, false);
                    }

                } catch (Exception e) {
                    Log.d("NewSignInfo", "Error: " + e.getMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("SignDataTag", "Failed to read value.", error.toException());
            }
        });
    }

    // Helper method to show/hide the GIF icon
    private static void setImageViewGif(Activity activity, ImageView imageView, boolean isVisible) {
        if (isVisible) {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(activity)
                    .asGif()
                    .load(R.drawable.inew)
                    .error(R.drawable.inew)
                    .placeholder(R.drawable.inew)
                    .into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    public static void openShareQP_Group(Activity activity){
        // Define the Telegram channel URL
        String TelegramChannelUrl = "https://t.me/+rj4RGxLU-1QyZTI1";

        // Create an Intent with the ACTION_VIEW action and the Telegram channel URL
        Uri telegramUri = Uri.parse(TelegramChannelUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, telegramUri);

        // Set the package name of the Telegram app
        intent.setPackage("org.telegram.messenger");

        // Check if the Telegram app is installed
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            // The Telegram app is installed, so open it
            activity.startActivity(intent);
        } else {
            // The Telegram app is not installed, you can handle this case as needed
            // For example, you can open the Telegram website in a web browser
           activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(TelegramChannelUrl)));
        }
    }

    public static void askDoubtHere(Activity activity){
        // Define the Telegram channel URL
        String TelegramChannelUrl = "https://t.me/The_LCTyoutube";

        // Create an Intent with the ACTION_VIEW action and the Telegram channel URL
        Uri telegramUri = Uri.parse(TelegramChannelUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, telegramUri);

        // Set the package name of the Telegram app
        intent.setPackage("org.telegram.messenger");

        // Check if the Telegram app is installed
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            // The Telegram app is installed, so open it
            activity.startActivity(intent);
        } else {
            // The Telegram app is not installed, you can handle this case as needed
            // For example, you can open the Telegram website in a web browser
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(TelegramChannelUrl)));
        }
    }

    public static void upComingExams(Context context,
                                     CardView MainCard,
                                     CardView semester12, CardView semester34,
                                     CardView semester56, CardView semester78,
                                     TextView semester12TV, TextView semester34TV,
                                     TextView semester56TV, TextView semester78TV) {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference examsRef = db.getReference("UpcomingExams");
        DatabaseReference notesRef = db.getReference("NEP_Notes/NotesAvailable");
        DatabaseReference examFlagRef = db.getReference("IsThereAnyExam");

        // Hide all by default
        semester12.setVisibility(View.GONE);
        semester34.setVisibility(View.GONE);
        semester56.setVisibility(View.GONE);
        semester78.setVisibility(View.GONE);

        final String[] notesData = new String[8];

        // Step 1: Get Notes Availability
        notesRef.get().addOnSuccessListener(notesSnap -> {
            if (notesSnap.exists()) {
                for (int i = 1; i <= 8; i++) {
                    notesData[i - 1] = notesSnap.child("sem" + i).getValue(String.class);
                }
                Log.d("NotesData", Arrays.toString(notesData));

                // Step 2: Check if any exam is going on
                examFlagRef.get().addOnSuccessListener(flagSnap -> {
                    String flag = String.valueOf(flagSnap.getValue());
                    if (flag.equalsIgnoreCase("yes")) {
                        MainCard.setVisibility(View.VISIBLE);

                        // Step 3: Get Upcoming Exams
                        examsRef.get().addOnSuccessListener(examSnap -> {
                            if (!examSnap.exists()) return;

                            for (DataSnapshot semSnap : examSnap.getChildren()) {
                                String sem = semSnap.getValue(String.class);
                                if (sem == null) continue;

                                int semNum = Integer.parseInt(sem);
                                String noteStatus = notesData[semNum - 1];

                                // Only show if notes are available
                                if (!"N/A".equalsIgnoreCase(noteStatus)) {
                                    switch (semNum) {
                                        case 1: case 2:
                                            semester12.setVisibility(View.VISIBLE);
                                            semester12TV.setText("Semester " + semNum);
                                            semester12.setOnClickListener(v -> {
                                                Intent i = new Intent(context, semNum == 1 ? NEP_Sem1_MainActivity.class : NEP_Sem2_MainActivity.class);
                                                i.putExtra("semester", String.valueOf(semNum));
                                                context.startActivity(i);
                                            });
                                            break;

                                        case 3: case 4:
                                            semester34.setVisibility(View.VISIBLE);
                                            semester34TV.setText("Semester " + semNum);
                                            semester34.setOnClickListener(v -> {
                                                Intent i = new Intent(context, semNum == 3 ? NEP_Sem3_MainActivity.class : NEP_Sem4_MainActivity.class);
                                                i.putExtra("semester", String.valueOf(semNum));
                                                context.startActivity(i);
                                            });
                                            break;

                                        case 5: case 6:
                                            semester56.setVisibility(View.VISIBLE);
                                            semester56TV.setText("Semester " + semNum);
                                            semester56.setOnClickListener(v -> {
                                                Intent i = new Intent(context, semNum == 5 ? NEP_Sem5_MainActivity.class : NEP_Sem6_MainActivity.class);
                                                i.putExtra("semester", String.valueOf(semNum));
                                                context.startActivity(i);
                                            });
                                            break;

                                        case 7: case 8:
                                            semester78.setVisibility(View.VISIBLE);
                                            semester78TV.setText("Semester " + semNum);
                                            semester78.setOnClickListener(v -> {
                                                Intent i = new Intent(context, semNum == 7 ? NEP_Sem7_MainActivity.class : NEP_Sem8_MainActivity.class);
                                                i.putExtra("semester", String.valueOf(semNum));
                                                context.startActivity(i);
                                            });
                                            break;
                                    }
                                }
                            }
                        });

                    } else {
                        MainCard.setVisibility(View.GONE);
                    }
                });
            }
        }).addOnFailureListener(e ->
                Toast.makeText(context, "Error loading notes: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }


    public static void isSaleOn(ImageView imageView){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference isThereAnyExam=database.getReference("isSaleOn");

        isThereAnyExam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value= Objects.requireNonNull(snapshot.getValue()).toString();

                if(value.equals("YES") || value.equals("Yes") || value.equals("yes")){
                    imageView.setVisibility(View.VISIBLE);
                }else{
                    imageView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}
