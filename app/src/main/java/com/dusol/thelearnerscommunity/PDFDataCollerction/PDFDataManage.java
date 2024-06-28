package com.dusol.thelearnerscommunity.PDFDataCollerction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dusol.thelearnerscommunity.Notes_HomeWeb_MainActivity;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PDFDataManage {

    public static InterstitialAd mInterstitialAd;
    public static int click=0;
    public static int NumberOfClickToShowAsd=2;
    public static void NotesManage(Activity activity,Context context, String path, ListView listView) {

        List<String> NotesNameArray = new ArrayList<>();
        List<String> NotesLinksArray = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(path);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    assert value != null;
                    Log.d("DataBaseLinks", value);
                    assert key != null;
                    Log.d("DataBaseLinks", key);


                    if(!value.equals("N/A")){
                        NotesNameArray.add(key+" ✔");
                    }else {
                        NotesNameArray.add(key);

                    }


                    NotesLinksArray.add(value);
                }

                if (NotesNameArray.isEmpty()) {
                } else {

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, NotesNameArray);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        listView.setOnItemClickListener((parent, view1, position, id) -> {

            if (NotesLinksArray.get(position).contains("youtube")) {

                Uri youtubeUri = Uri.parse(NotesLinksArray.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");
                context.startActivity(intent);

            } else if (NotesLinksArray.get(position).contains("myinstamojo")) {

                PaidNotesLinkOpen(context, NotesLinksArray.get(position));
            } else if (NotesLinksArray.get(position).contains("N/A")) {
                Toast.makeText(activity, "Notes Will Available Soon!", Toast.LENGTH_SHORT).show();
            } else {
                openIntend(context, NotesLinksArray.get(position));
            }


        });
    }


    public static void openIntend(Context context,String link){
        Intent intent = new Intent(context, Notes_HomeWeb_MainActivity.class);
        intent.putExtra("link", link);
        context.startActivity(intent);
    }

    public static void PaidNotesLinkOpen(Context context,String url){
        // Create an intent with ACTION_VIEW and the URL as the data
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Check if there is a web browser (e.g., Chrome) available to handle the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            // Start the activity to open the URL in the browser
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "No web browser found to open the URL.", Toast.LENGTH_SHORT).show();
        }
    }
}
