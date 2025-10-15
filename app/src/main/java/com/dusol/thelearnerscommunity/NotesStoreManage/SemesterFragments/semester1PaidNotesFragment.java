package com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dusol.thelearnerscommunity.NotesStoreManage.NotesManagerAdapter;
import com.dusol.thelearnerscommunity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class semester1PaidNotesFragment extends Fragment {

    private static final String TAG = "Sem1PaidNotesFragment";
    private static final String FIREBASE_PATH = "PaidNotesNewData/Sem1";
    private static final int LOAD_DELAY_MS = 1000;
    private static final int MAX_RETRY_ATTEMPTS = 3;
    
    private RecyclerView recyclerView;
    private Handler mainHandler;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    private final AtomicBoolean isDataLoaded = new AtomicBoolean(false);
    private final AtomicBoolean isFragmentActive = new AtomicBoolean(false);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_semester1_paid_notes, container, false);
            
            recyclerView = view.findViewById(R.id.semester1PaidNotesRV);
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            }
            
            mainHandler = new Handler(Looper.getMainLooper());
            isFragmentActive.set(true);
            
            return view;
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreateView: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadNotesData();
    }

    private void loadNotesData() {
        if (!isFragmentActive.get()) {
            Log.w(TAG, "Fragment is not active, skipping data load");
            return;
        }

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference(FIREBASE_PATH);
            
            valueEventListener = new ValueEventListener() {
                final List<String> title = new ArrayList<>();
                final List<String> imageLink = new ArrayList<>();
                final List<String> pageLink = new ArrayList<>();
                final List<String> details = new ArrayList<>();

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!isFragmentActive.get()) {
                        Log.w(TAG, "Fragment is not active, ignoring data change");
                        return;
                    }

                    try {
                        title.clear();
                        imageLink.clear();
                        pageLink.clear();
                        details.clear();

                        if (snapshot.exists()) {
                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                try {
                                    GenericTypeIndicator<List<String>> typeIndicator = 
                                        new GenericTypeIndicator<List<String>>() {};
                                    List<String> noteData = childSnapshot.getValue(typeIndicator);

                                    if (noteData != null && noteData.size() >= 4) {
                                        // Validate data before adding
                                        if (isValidNoteData(noteData)) {
                                            title.add(noteData.get(0));
                                            imageLink.add(noteData.get(1));
                                            pageLink.add(noteData.get(2));
                                            details.add(noteData.get(3));
                                        } else {
                                            Log.w(TAG, "Invalid note data found: " + noteData);
                                        }
                                    } else {
                                        Log.w(TAG, "Note data is null or incomplete");
                                    }
                                } catch (Exception e) {
                                    Log.e(TAG, "Error processing note data: " + e.getMessage(), e);
                                }
                            }
                        } else {
                            Log.i(TAG, "No data found in Firebase for path: " + FIREBASE_PATH);
                        }

                        // Update UI on main thread
                        mainHandler.postDelayed(() -> {
                            if (isFragmentActive.get() && isAdded() && getActivity() != null) {
                                updateRecyclerView(title, imageLink, pageLink, details);
                            }
                        }, LOAD_DELAY_MS);

                    } catch (Exception e) {
                        Log.e(TAG, "Error in onDataChange: " + e.getMessage(), e);
                        showErrorToUser("Error loading data");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, "Database error: " + error.getMessage() + " Code: " + error.getCode());
                    if (isFragmentActive.get()) {
                        showErrorToUser("Network error: " + getReadableErrorMessage(error.getCode()));
                    }
                }
            };

            databaseReference.addValueEventListener(valueEventListener);

        } catch (Exception e) {
            Log.e(TAG, "Error initializing Firebase: " + e.getMessage(), e);
            showErrorToUser("Error connecting to server");
        }
    }

    private boolean isValidNoteData(List<String> noteData) {
        if (noteData == null || noteData.size() < 4) {
            return false;
        }
        
        // Check if required fields are not null or empty
        for (int i = 0; i < 4; i++) {
            if (noteData.get(i) == null || noteData.get(i).trim().isEmpty()) {
                return false;
            }
        }
        
        return true;
    }

    private void updateRecyclerView(List<String> title, List<String> imageLink, 
                                   List<String> pageLink, List<String> details) {
        try {
            if (recyclerView == null) {
                Log.e(TAG, "RecyclerView is null");
                return;
            }

            if (title.isEmpty()) {
                Log.i(TAG, "No notes data to display");
                // You could show a "No notes available" message here
                return;
            }

            NotesManagerAdapter adapter = new NotesManagerAdapter(
                    getActivity(), title, imageLink, pageLink, details,
                    getActivity().getSupportFragmentManager()
            );
            recyclerView.setAdapter(adapter);
            isDataLoaded.set(true);
            
            Log.i(TAG, "Successfully loaded " + title.size() + " notes");

        } catch (Exception e) {
            Log.e(TAG, "Error setting adapter: " + e.getMessage(), e);
            showErrorToUser("Error displaying notes");
        }
    }

    private void showErrorToUser(String message) {
        if (isFragmentActive.get() && getActivity() != null) {
            mainHandler.post(() -> {
                try {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(TAG, "Error showing toast: " + e.getMessage(), e);
                }
            });
        }
    }

    private String getReadableErrorMessage(int errorCode) {
        switch (errorCode) {
            case DatabaseError.PERMISSION_DENIED:
                return "Access denied";
            case DatabaseError.DISCONNECTED:
                return "Connection lost";
            case DatabaseError.NETWORK_ERROR:
                return "Network error";
            case DatabaseError.OPERATION_FAILED:
                return "Operation failed";
            default:
                return "Unknown error";
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isFragmentActive.set(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        isFragmentActive.set(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFragmentActive.set(false);
        
        // Clean up resources
        if (valueEventListener != null && databaseReference != null) {
            databaseReference.removeEventListener(valueEventListener);
        }
        
        if (mainHandler != null) {
            mainHandler.removeCallbacksAndMessages(null);
        }
        
        recyclerView = null;
        mainHandler = null;
        databaseReference = null;
        valueEventListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isFragmentActive.set(false);
    }
}