package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.Notes_HomeWeb_MainActivity;
import com.dusol.thelearnerscommunity.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sem1SyllabusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sem1_syllabus, container, false);


        //ads
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);      

        ListView listView = view.findViewById(R.id.sem1SyllabusList);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference sem1Links = database.getReference("Syllabus/sem1");

        List<String> sem1CourseName = new ArrayList<>();  // Store Course Name
        List<String> sem1CourseLinks = new ArrayList<>();  // Store course PDF links

        sem1Links.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue(String.class);

                    if(key.contains("NEP")) {
                        sem1CourseName.add(key + "\uD83C\uDD95");
                    }else{
                        sem1CourseName.add(key);

                    }
                    sem1CourseLinks.add(value);
                }

                Log.d("SyllabusData", sem1CourseLinks.toString());

                ArrayAdapter adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, sem1CourseName);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((parent, view1, position, id) ->
                        openIntend(sem1CourseLinks.get(position)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });

        return view;
    }

    public void openIntend(String link) {
        if (!Objects.equals(link, "N/A")) {
            if(link.contains("drive")){
                Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                intent.putExtra("link", link);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
            else if(link.contains("pdfhost")){
            Intent intent = new Intent(getActivity(), Syllabus_Web_MainActivity.class);
            intent.putExtra("link", link);
            startActivity(intent);}

        } else if (link.contains("youtube")) {
            Uri youtubeUri = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);
            intent.setPackage("com.google.android.youtube");
            startActivity(intent);
        } else {
            Toast.makeText(requireActivity(), "Syllabus Not Available Now!", Toast.LENGTH_SHORT).show();
        }
    }
}
