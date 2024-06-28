package com.dusol.thelearnerscommunity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.google.firebase.analytics.FirebaseAnalytics;


public class EducationDSE5thSemNotes extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_education_dse_5th_sem, container, false);

        ListView listView = view.findViewById(R.id.sem5EducationDSENotesList);

        Bundle bundle = new Bundle();
        bundle.putString("Sem5_Notes", "Sem5_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem5_Notes_Open", bundle);

        PDFDataManage.NotesManage(getActivity(),getContext(),"StudyNotes/BASem5/DSEEducation",listView);

        /*//        Unit1
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
                            String link="https://drive.google.com/file/d/1cifdYxuUctmj1cOxLz2x7ZlEwzWGY2yy/view?usp=sharing";
                            openIntend(link);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });

                } else {

                    String link="https://drive.google.com/file/d/1cifdYxuUctmj1cOxLz2x7ZlEwzWGY2yy/view?usp=sharing";
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
                            String link="https://drive.google.com/file/d/1OaecnxRtVHmXY78n-wGoBj-Qc-XrTfBc/view?usp=sharing";
                            openIntend(link);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });

                } else {

                    String link="https://drive.google.com/file/d/1OaecnxRtVHmXY78n-wGoBj-Qc-XrTfBc/view?usp=sharing";
                    openIntend(link);
                }
            }
        });



        unit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link="https://drive.google.com/file/d/1CmHXv0y65x0gmFFWr63NRwMhVHwwyMnh/view?usp=sharing";
                            openIntend(link);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });

                } else {

                    String link="https://drive.google.com/file/d/1CmHXv0y65x0gmFFWr63NRwMhVHwwyMnh/view?usp=sharing";
                    openIntend(link);
                }

            }
        });

        unit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link="https://drive.google.com/file/d/1lBLArpjsTFomtcombngLn6aP7BBBL3_E/view?usp=sharing";
                            openIntend(link);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });

                } else {

                    String link="https://drive.google.com/file/d/1lBLArpjsTFomtcombngLn6aP7BBBL3_E/view?usp=sharing";
                    openIntend(link);
                }

            }
        });


        PaidNotesPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL="https://thelearnerscommunitynotes.myinstamojo.com/product/4526543/du-sol-ncweb-5th-semester-dse-education-educ?referred_from=category";
                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            PaidNotesLinkOpen(URL);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }
                    });

                } else {
                    PaidNotesLinkOpen(URL);
                }
            }
        });


        Button WatchVideos=view.findViewById(R.id.Watch_Videos);
        WatchVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define the YouTube channel URL
                String playlistUrl = "https://youtube.com/playlist?list=PL6Rby-wV4zjxcaWrY1LU9jUsExzV7Jks-&si=ycU4TYCaxSuxtn31";

                // Create an Intent with the ACTION_VIEW action and the YouTube channel URL
                Uri youtubeUri = Uri.parse(playlistUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");

                startActivity(intent);}
        });*/
        /*final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudyNotes/BASem5/DSEEducation");
        List<String> sem5NotesDSEEducationName = new ArrayList<>();
        List<String> sem5NotesDSEEducationLinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);

                    sem5NotesDSEEducationName.add(key);
                    sem5NotesDSEEducationLinks.add(value);
                }

                if(sem5NotesDSEEducationName.isEmpty()){
                }else{

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem5NotesDSEEducationName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {

            if (sem5NotesDSEEducationLinks.get(position).contains("youtube")) {

                Uri youtubeUri = Uri.parse(sem5NotesDSEEducationLinks.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);

            } else if (sem5NotesDSEEducationLinks.get(position).contains("myinstamojo")) {

                PaidNotesLinkOpen(sem5NotesDSEEducationLinks.get(position));
            } else {

                if(!Objects.equals(sem5NotesDSEEducationLinks.get(position), "N/A")){

                    click++;
                    Log.d("AdsLoad", String.valueOf(click % NumberOfClickToShowAsd == 0));
                    Log.d("AdsLoad", String.valueOf(mInterstitialAd != null));


                    if (mInterstitialAd != null && click % NumberOfClickToShowAsd == 0) {
                        mInterstitialAd.show(requireActivity());

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d("AdsLoad3", "Ad showed fullscreen content.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.e("AdsLoad1", "Ad failed to show fullscreen content.");
                                openIntend(sem5NotesDSEEducationLinks.get(position));
                            }
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.e("AdsLoad2", "Ad failed to show fullscreen content.");
                                openIntend(sem5NotesDSEEducationLinks.get(position));
                            }


                        });

                    }else{
                        Log.e("AdsLoad4", "Last Else.");
                        openIntend(sem5NotesDSEEducationLinks.get(position));
                    }
                }else{
                    Toast.makeText(requireContext(), "Notes Not Available Now!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        return view;

    }

}