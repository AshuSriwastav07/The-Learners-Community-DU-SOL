package com.dusol.thelearnerscommunity.NotesStoreManage.SemesterFragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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


public class semester6PaidNotesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_semester6_paid_notes, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.semester6PaidNotesRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference getDonationData = database.getReference("PaidNotesNewData/Sem6");

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

                    new Handler().postDelayed(() -> {
                        if (isAdded() && getActivity() != null) {
                            NotesManagerAdapter adapter = new NotesManagerAdapter(
                                    getActivity(), title, imageLink, pageLink, details,
                                    getActivity().getSupportFragmentManager()
                            );
                            recyclerView.setAdapter(adapter);
                        }
                    }, 3000); // 3 seconds delay


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error
                }
            });
        } catch (Exception e) {
            Log.d("PaidNotesLogging", e.toString());
        }


        return view;
    }
}