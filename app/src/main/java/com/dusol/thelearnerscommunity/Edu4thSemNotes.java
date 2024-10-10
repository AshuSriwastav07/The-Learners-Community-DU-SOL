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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Edu4thSemNotes extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edu4thsem, container, false);

        ListView listView = view.findViewById(R.id.sem4SECEducationyNotesList);

        Bundle bundle = new Bundle();
        bundle.putString("Sem4_Notes", "Sem4_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem4_Notes_Open", bundle);

        PDFDataManage.NotesManage(getActivity(), getContext(), "StudyNotes/BASem4/EducationSEC", listView);

/*
        Button unit1=view.findViewById(R.id.unit1);
        Button unit2=view.findViewById(R.id.unit2);
        Button PaidNotes=view.findViewById(R.id.Sem4Education_PaidNotes);



        unit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link="https://drive.google.com/file/d/19C2cpjtFsDZwm8wXOhyqENTcGIn0kc-c/view?usp=sharing";
                            openIntend(link);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }


                    });

                } else {

                    // Create an Intent to open the new activity
                    String link="https://drive.google.com/file/d/19C2cpjtFsDZwm8wXOhyqENTcGIn0kc-c/view?usp=sharing";
                    openIntend(link);

                }


            }
        });

        unit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link="https://drive.google.com/file/d/1by7QUkDbO4b8NnIx0yRKBYkRmbCcD69Z/view?usp=sharing";
                            openIntend(link);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }


                    });

                } else {

                    // Create an Intent to open the new activity
                    String link="https://drive.google.com/file/d/1by7QUkDbO4b8NnIx0yRKBYkRmbCcD69Z/view?usp=sharing";
                    openIntend(link);

                }

            }
        });

        //Paid NOtes Here
        PaidNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Replace "https://www.example.com" with the URL you want to open in Chrome
                            String url = "https://thelearnerscommunitynotes.myinstamojo.com/product/3830640/du-sol-4th-semester-sec-education-reflective?referred_from=category";
                            PaidNotesLinkOpen(url);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }


                    });

                } else {
                    // Replace "https://www.example.com" with the URL you want to open in Chrome
                    String url = "https://thelearnerscommunitynotes.myinstamojo.com/product/3830640/du-sol-4th-semester-sec-education-reflective?referred_from=category";

                    // Create an intent with ACTION_VIEW and the URL as the data
                    PaidNotesLinkOpen(url);

                }


            }
        });
        //Ends Here

        Button WatchVideos=view.findViewById(R.id.Watch_Videos);
        WatchVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define the YouTube channel URL
                String playlistUrl = "https://youtube.com/playlist?list=PL6Rby-wV4zjzWXlJZb8JmyIYILx5Z2BxU&si=8hAvKfpqnPt2fgpG";

                // Create an Intent with the ACTION_VIEW action and the YouTube channel URL
                Uri youtubeUri = Uri.parse(playlistUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");

                startActivity(intent);}
        });
*/

/*        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudyNotes/BASem4/EducationSEC");
        List<String> sem4NotesEducationName = new ArrayList<>();
        List<String> sem4NotesEducationLinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);

                    sem4NotesEducationName.add(key);
                    sem4NotesEducationLinks.add(value);
                }

                if(sem4NotesEducationName.isEmpty()){
                }else{

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem4NotesEducationName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {

            if (sem4NotesEducationLinks.get(position).contains("youtube")) {

                Uri youtubeUri = Uri.parse(sem4NotesEducationLinks.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);

            } else if (sem4NotesEducationLinks.get(position).contains("myinstamojo")) {

                PaidNotesLinkOpen(sem4NotesEducationLinks.get(position));
            } else {
                openIntend(sem4NotesEducationLinks.get(position));
            }
        });


    }

    public void openIntend(String link){
        Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
        intent.putExtra("link", link);
        startActivity(intent);
    }

    public void PaidNotesLinkOpen(String url){
        // Create an intent with ACTION_VIEW and the URL as the data
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Check if there is a web browser (e.g., Chrome) available to handle the intent
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            // Start the activity to open the URL in the browser
            startActivity(intent);
        } else {
            Toast.makeText(requireContext(), "No web browser found to open the URL.", Toast.LENGTH_SHORT).show();
        }
    }*/
        return view;
    }
}