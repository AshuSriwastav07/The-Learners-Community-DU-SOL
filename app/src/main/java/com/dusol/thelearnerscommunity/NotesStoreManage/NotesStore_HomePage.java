package com.dusol.thelearnerscommunity.NotesStoreManage;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dusol.thelearnerscommunity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotesStore_HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_store_home_page);

        RecyclerView storeRecyclerView = findViewById(R.id.notesStoreRecyclerView);
        storeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference getDonationData = database.getReference("NotesStore");

        try {
            getDonationData.addValueEventListener(new ValueEventListener() {
                final List<String> title = new ArrayList<>();
                final List<String> imageLink = new ArrayList<>();
                final List<String> pageLink = new ArrayList<>();
                final List<String> details = new ArrayList<>();

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    title.clear();
                    imageLink.clear();
                    pageLink.clear();
                    details.clear();


                    if (snapshot.exists()) {
                        for (DataSnapshot i : snapshot.getChildren()) {
                            GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                            List<String> ngoData = i.getValue(t);

                            if (ngoData != null) {
//                                Log.d("PaidNotesData", ngoData.get(0));
//                                Log.d("PaidNotesData", ngoData.get(1));
//                                Log.d("PaidNotesData", ngoData.get(2));
//                                Log.d("PaidNotesData", ngoData.get(3));

                                // Uncomment if you want to add the data to the lists
                                title.add(ngoData.get(0));
                                imageLink.add(ngoData.get(1));
                                pageLink.add(ngoData.get(2));
                                details.add(ngoData.get(3));

                            }
                        }
                    }
                    NotesManagerAdapter adapter = new NotesManagerAdapter(NotesStore_HomePage.this, title, imageLink, pageLink,details,getSupportFragmentManager());
                    storeRecyclerView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error
                }
            });
        } catch (Exception e) {
            Log.d("NotesStoreHomePageLogging", e.toString());
        }
    }
}
