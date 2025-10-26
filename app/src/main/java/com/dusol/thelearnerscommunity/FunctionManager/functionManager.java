package com.dusol.thelearnerscommunity.FunctionManager;

import android.annotation.SuppressLint;
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
import com.dusol.thelearnerscommunity.DU_SOL_NOTES__MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem1_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem2_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem3_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem4_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem5_MainActivity;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem6_MainActivity;
import com.dusol.thelearnerscommunity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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

    //Get data show if there is any exam or not
    public static void upComingExams(Context context,CardView MainCard,
                                     CardView semester12, CardView semester34,
                                     CardView semester56, CardView semester78,
                                     TextView semester12TV, TextView semester34TV,
                                     TextView semester56TV, TextView semester78TV) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("UpcomingExams");
        DatabaseReference isThereAnyExam=database.getReference("IsThereAnyExam");

        isThereAnyExam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value= Objects.requireNonNull(snapshot.getValue()).toString();

                if(value.equals("YES") || value.equals("Yes") || value.equals("yes")){
                    MainCard.setVisibility(View.VISIBLE);
                }else{
                    MainCard.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Firebase real-time listener
        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear previous text
                semester12TV.setText("");
                semester34TV.setText("");
                semester56TV.setText("");
                semester78TV.setText("");

                // Loop through each child node
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String value = snapshot.getValue(String.class);

                    if (value == null) continue;

                    switch (value) {
                        case "1":
                            semester12TV.setText("Semester 1");
                            semester12.setOnClickListener(view -> {
                                Intent intent = new Intent(context, NEP_Sem1_MainActivity.class);
                                intent.putExtra("semester", "1");
                                context.startActivity(intent);
                            });
                            break;
                        case "2":
                            semester12TV.setText("Semester 2");
                            semester12.setOnClickListener(view -> {
                                Intent intent = new Intent(context, NEP_Sem2_MainActivity.class);
                                intent.putExtra("semester", "2");
                                context.startActivity(intent);
                            });
                            break;


                        case "3":
                            semester34TV.setText("Semester 3");
                            semester34.setOnClickListener(view -> {
                                Intent intent = new Intent(context, NEP_Sem3_MainActivity.class);
                                intent.putExtra("semester", "3");
                                context.startActivity(intent);
                            });
                            break;

                        case "4":
                            semester34TV.setText("Semester 4");
                            semester34.setOnClickListener(view -> {
                                Intent intent = new Intent(context, NEP_Sem4_MainActivity.class);
                                intent.putExtra("semester", "4");
                                context.startActivity(intent);
                            });
                            break;

                        case "5":
                            semester56TV.setText("Semester 5");
                            semester56.setOnClickListener(view -> {
                                Intent intent = new Intent(context, NEP_Sem5_MainActivity.class);
                                intent.putExtra("semester", "5");
                                context.startActivity(intent);
                            });
                            break;

                        case "6":
                            semester56TV.setText("Semester 6");
                            semester56.setOnClickListener(view -> {
                                Intent intent = new Intent(context, NEP_Sem6_MainActivity.class);
                                intent.putExtra("semester", "6");
                                context.startActivity(intent);
                            });
                            break;


                        case "7":
                            semester78TV.setText("Semester 7");
                            semester78.setOnClickListener(view -> {
                                Intent intent = new Intent(context, DU_SOL_NOTES__MainActivity.class);
                                intent.putExtra("semester", "7");
                                context.startActivity(intent);
                            });
                            break;

                        case "8":
                            semester78TV.setText("Semester 8");
                            semester78.setOnClickListener(view -> {
                                Intent intent = new Intent(context, DU_SOL_NOTES__MainActivity.class);
                                intent.putExtra("semester", "8");
                                context.startActivity(intent);
                            });
                            break;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Failed to load exams: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
