package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;


public class Sem6ExamAllNotes extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sem6_exam_all_notes, container, false);



        ListView listView=view.findViewById(R.id.sem6ExamNotesList);

        PDFDataManage.NotesManage(getActivity(),getContext(),"StudyNotes/BASem6/ExamNotes",listView);


        /*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference sem5Links = database.getReference("StudyNotes/BASem6/ExamNotes");

        List<String> sem6ExamNotesName = new ArrayList<>();  //Store Course Name
        List<String> sem6ExamNotesLink = new ArrayList<>();  //store course pdf links

        sem5Links.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue(String.class);

                    sem6ExamNotesName.add(key);
                    sem6ExamNotesLink.add(value);

                }

                Log.d("SyllabusData",sem6ExamNotesName.toString());

                ArrayAdapter adapter=new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, sem6ExamNotesName);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((parent, view1, position, id) ->
                        start_Ads(sem6ExamNotesLink.get(position),click1));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


         */
        return view;
    }
}