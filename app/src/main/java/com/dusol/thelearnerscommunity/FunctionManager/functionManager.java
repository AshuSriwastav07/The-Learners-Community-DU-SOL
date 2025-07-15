package com.dusol.thelearnerscommunity.FunctionManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
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
                Log.d("NewSignInfo", updateList.toString());

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

                    /*// Video Notes Update Indicator
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
    };

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

}
