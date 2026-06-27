/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Hindi4thSemNotes extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hindi4thsem, container, false);

        ListView listView = view.findViewById(R.id.sem4HindiGENotesList);

        Bundle bundle = new Bundle();
        bundle.putString("Sem4_Notes", "Sem4_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem4_Notes_Open", bundle);

        PDFDataManage.NotesManage(getActivity(),getContext(),"StudyNotes/BASem4/Hindi",listView);

        /*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudyNotes/BASem4/Hindi");
        List<String> sem4NotesHindiGEName = new ArrayList<>();
        List<String> sem4NotesHindiGELinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    // AUDIT FIX (7A): Log.d("DataBaseLinks", value);
                    // AUDIT FIX (7A): Log.d("DataBaseLinks", key);

                    sem4NotesHindiGEName.add(key);
                    sem4NotesHindiGELinks.add(value);
                }

                if(sem4NotesHindiGEName.isEmpty()){
                }else{

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem4NotesHindiGEName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String url = sem4NotesHindiGELinks.get(position);
            String title = sem4NotesHindiGEName.get(position);
            com.dusol.thelearnerscommunity.SyllabusFiles.NoteLinkRouter.route(view1.getContext(), url, title);
        });


        //Ads Start Here
        MobileAds.initialize(requireActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

         */

        return view;

    }

}