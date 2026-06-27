package com.dusol.thelearnerscommunity.PDFDataCollerction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dusol.thelearnerscommunity.Notes_HomeWeb_MainActivity;
import com.dusol.thelearnerscommunity.pdf_adapter_manage.NotesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PDFDataManage {

    public static void NotesManage(Activity activity,Context context, String path, ListView listView) {

        List<String> NotesNameArray = new ArrayList<>();
        List<String> NotesLinksArray = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(path);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // AUDIT FIX: Guard UI updates to prevent getActivity() crashes (1A)
                if (activity != null && (activity.isDestroyed() || activity.isFinishing())) return;

                NotesNameArray.clear();
                NotesLinksArray.clear();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();  //notes name
                    String value = childSnapshot.getValue(String.class); //notes link

                    assert value != null;
                    if(!value.equals("N/A") && !value.isEmpty()){
                        NotesNameArray.add(key+" ✔");
                    }else {
                        NotesNameArray.add(key);
                    }


                    NotesLinksArray.add(value);
                }

                if (NotesNameArray.isEmpty()) {
                } else {

                    NotesAdapter adapter = new NotesAdapter(context, NotesNameArray, NotesLinksArray);
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        };
        
        ref.addValueEventListener(listener);

        // AUDIT FIX: Auto-remove Firebase listener when Fragment/Activity is destroyed to prevent memory leaks (2A)
        if (context instanceof androidx.lifecycle.LifecycleOwner) {
            ((androidx.lifecycle.LifecycleOwner) context).getLifecycle().addObserver(
                new androidx.lifecycle.LifecycleEventObserver() {
                    @Override
                    public void onStateChanged(@NonNull androidx.lifecycle.LifecycleOwner source, @NonNull androidx.lifecycle.Lifecycle.Event event) {
                        if (event == androidx.lifecycle.Lifecycle.Event.ON_DESTROY) {
                            ref.removeEventListener(listener);
                        }
                    }
                });
        }


        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String link = NotesLinksArray.get(position);
            String title = NotesNameArray.get(position);
            com.dusol.thelearnerscommunity.SyllabusFiles.NoteLinkRouter.route(context, link, title);
        });
    }


    public static void openIntend(Context context,String link,String Name, String PDFType){
        if (link != null && link.contains("drive")) {
            Intent intent = new Intent(context, Notes_HomeWeb_MainActivity.class);
            intent.putExtra("link", link);
            intent.putExtra("PdfName", Name);
            intent.putExtra("Path", PDFType);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, com.dusol.thelearnerscommunity.SyllabusFiles.PdfViewerActivity.class);
            intent.putExtra(com.dusol.thelearnerscommunity.SyllabusFiles.PdfViewerActivity.EXTRA_PDF_URL, link);
            intent.putExtra(com.dusol.thelearnerscommunity.SyllabusFiles.PdfViewerActivity.EXTRA_PDF_TITLE, Name);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
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

    public static List<String> CheckNotesAvailable(){

        List<String> NotesNameArray = new ArrayList<>();
        List<String> NotesLinksArray = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("NEP_Notes/NotesAvailable");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();  //notes name
                    String value = childSnapshot.getValue(String.class); //notes link

                    assert value != null;
//                    Log.d("NotesNameArray",value);

                    NotesLinksArray.add(value);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }


        });
        return NotesLinksArray;
    }
}
