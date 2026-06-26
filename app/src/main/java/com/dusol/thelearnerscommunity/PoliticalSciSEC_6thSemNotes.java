package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.google.firebase.analytics.FirebaseAnalytics;


public class PoliticalSciSEC_6thSemNotes extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_political_sci_s_e_c_6th_sem, container, false);


        ListView listView = view.findViewById(R.id.sem6PolSciSECNotesList);

        Bundle bundle = new Bundle();
        bundle.putString("Sem6_Notes", "Sem6_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem6_Notes_Open", bundle);

        PDFDataManage.NotesManage(getActivity(),getContext(),"StudyNotes/BASem6/PolSciSEC",listView);

        /*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudyNotes/BASem6/PolSciSEC");
        List<String> sem6NotesPolSciSecName = new ArrayList<>();
        List<String> sem6NotesPolSciSECLinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);

                    sem6NotesPolSciSecName.add(key);
                    sem6NotesPolSciSECLinks.add(value);
                }

                if(sem6NotesPolSciSecName.isEmpty()){
                }else{

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem6NotesPolSciSecName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String url = sem6NotesPolSciSECLinks.get(position);
            String title = sem6NotesPolSciSecName.get(position);
            com.dusol.thelearnerscommunity.SyllabusFiles.NoteLinkRouter.route(view1.getContext(), url, title);
        });


         */
        return view;
    }
}