package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Sem3SyllabusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sem3_syllabus, container, false);

        ListView listView = view.findViewById(R.id.sem3SyllabusList);

        //ads
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        syllabus_pdf_manage.SyllabusPDFManage(getActivity(),getContext(),"Syllabus/sem3",listView);

        /*final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference sem1Links = database.getReference("Syllabus/sem3");

        List<String> sem3CourseName = new ArrayList<>();  // Store Course Name
        List<String> sem3CourseLinks = new ArrayList<>();  // Store course PDF links

        sem1Links.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue(String.class);

                    if(key.contains("NEP")) {
                        sem3CourseName.add(key + "\uD83C\uDD95");
                    }else{
                        sem3CourseName.add(key);

                    }
                    sem3CourseLinks.add(value);
                }

                Log.d("SyllabusData", sem3CourseLinks.toString());

                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, sem3CourseName);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((parent, view1, position, id) ->
                        openIntend(sem3CourseLinks.get(position)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });


         */
        return view;
    }
/*
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

 */
}
