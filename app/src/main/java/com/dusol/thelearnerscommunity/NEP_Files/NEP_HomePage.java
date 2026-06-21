package com.dusol.thelearnerscommunity.NEP_Files;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStoreTabActivity;
import com.dusol.thelearnerscommunity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NEP_HomePage extends AppCompatActivity {

    // This will store the values from Firebase in order
    private final List<String> values = new ArrayList<>();
    private boolean isDataLoaded = false;   // To know when Firebase has finished loading

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_nep_semester);

        // Find views
        CardView sem1NotesOpen = findViewById(R.id.NEP_Sem1);
        CardView sem2NotesOpen = findViewById(R.id.NEP_Sem2);
        CardView sem3NotesOpen = findViewById(R.id.NEP_Sem3);
        CardView sem4NotesOpen = findViewById(R.id.NEP_Sem4);
        CardView sem5NotesOpen = findViewById(R.id.NEP_Sem5);
        CardView sem6NotesOpen = findViewById(R.id.NEP_Sem6);
        CardView sem7NotesOpen = findViewById(R.id.NEP_Sem7);
        CardView sem8NotesOpen = findViewById(R.id.NEP_Sem8);
        Button buyNotes = findViewById(R.id.Buy_Notes_PDF);

        // Load availability from Firebase once
        loadNotesAvailability();

        // Set click listeners for each semester
        sem1NotesOpen.setOnClickListener(v ->
                handleSemesterClick(0, NEP_Sem1_MainActivity.class));

        sem2NotesOpen.setOnClickListener(v ->
                handleSemesterClick(1, NEP_Sem2_MainActivity.class));

        sem3NotesOpen.setOnClickListener(v ->
                handleSemesterClick(2, NEP_Sem3_MainActivity.class));

        sem4NotesOpen.setOnClickListener(v ->
                handleSemesterClick(3, NEP_Sem4_MainActivity.class));

        sem5NotesOpen.setOnClickListener(v ->
                handleSemesterClick(4, NEP_Sem5_MainActivity.class));

        sem6NotesOpen.setOnClickListener(v ->
                handleSemesterClick(5, NEP_Sem6_MainActivity.class));

        sem7NotesOpen.setOnClickListener(v ->
                handleSemesterClick(6, NEP_Sem7_MainActivity.class));

        sem8NotesOpen.setOnClickListener(v ->
                handleSemesterClick(7, NEP_Sem8_MainActivity.class));

        // Buy Notes Button
        buyNotes.setOnClickListener(view -> {
            Intent intent = new Intent(this, NotesStoreTabActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Load all "NotesAvailable" statuses from Firebase once.
     * Path: NEP_Notes/NotesAvailable
     */
    private void loadNotesAvailability() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("NEP_Notes/NotesAvailable");

        // Read once (no need for realtime listener here)
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                values.clear();

                // IMPORTANT:
                // This will iterate children in the order they are stored in Firebase.
                // Make sure the order in Firebase matches your mapping (Sem1..Sem8).
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String value = childSnapshot.getValue(String.class);
                    if (value == null) value = "";

                    // Normalize string to avoid "OK", " Ok " issues
                    value = value.trim().toLowerCase();
                    values.add(value);
                }

                isDataLoaded = true;
                // For debugging:
                // Toast.makeText(NEP_HomePage.this, "Notes status loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isDataLoaded = false;
                Toast.makeText(NEP_HomePage.this,
                        "Failed to load notes info: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Handles click on each semester card.
     *
     * @param index          index in the values list (0..7)
     * @param targetActivity activity to open when status == "ok"
     */
    private void handleSemesterClick(int index, Class<?> targetActivity) {
        // 1. Check if data loaded from Firebase
        if (!isDataLoaded) {
            Toast.makeText(this,
                    "Please wait, loading notes information...",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // 2. Safety: check index within list size
        if (index >= values.size()) {
            Toast.makeText(this,
                    "Notes information not found. Please try again later.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String status = values.get(index);  // already lowercased & trimmed

        // 3. Check if this semester is available
        if ("ok".equals(status)) {
            Intent intent = new Intent(this, targetActivity);
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    "Notes Will Be Available Soon!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
