package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;


public class Exam2ndSemNotes extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_exam2nd_sem_notes, container, false);


        ListView listView=view.findViewById(R.id.sem2ExamAllNotes);

        PDFDataManage.NotesManage(getActivity(),getContext(),"StudyNotes/BASem2",listView);

/*
        final FirebaseDatabase database= FirebaseDatabase.getInstance();

        DatabaseReference myDatabaseRef=database.getReference("StudyNotes/BASem2");
        List<String> sem2ExamNotesName=new ArrayList<>();
        List<String> sem2ExamNotesLinks = new ArrayList<>();

        myDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ChildSnapshot:snapshot.getChildren()){
                    String value=ChildSnapshot.getValue(String.class);
                    sem2ExamNotesName.add(ChildSnapshot.getKey());
                    sem2ExamNotesLinks.add(value);

                }

                if (!sem2ExamNotesName.isEmpty()){
                    ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1,sem2ExamNotesName);
                    listView.setAdapter(itemAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(),"No Notes Available",Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String url = sem2ExamNotesLinks.get(position);
            String title = sem2ExamNotesName.get(position);
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
