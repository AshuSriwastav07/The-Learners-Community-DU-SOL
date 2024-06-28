package com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.dusol.thelearnerscommunity.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Sem1_Paper_1_DSC extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.sem1_nep_paper1_dsc_fragment, container, false);

        Bundle bundle = new Bundle();
        bundle.putString("Sem1_NEP_Notes", "Sem1_NEP_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem1_NEPNotes_Open", bundle);

        ListView listView = view.findViewById(R.id.sem1Paper1ListView);
        PDFDataManage.NotesManage(getActivity(),getContext(),"NEP_Notes/sem1/paper1",listView);
        return view;


       /* List<String> sem1NotesAncientHisName = new ArrayList<>();
        List<String> sem1NotesAncientHisLinks = new ArrayList<>();
*/



       /* ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                   *//* Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);*//*

                    sem1NotesAncientHisName.add(key);
                    sem1NotesAncientHisLinks.add(value);
                }

                if(sem1NotesAncientHisName.isEmpty()){
                }else{
                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem1NotesAncientHisName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(sem1NotesAncientHisLinks.get(position).contains("youtube")){

                Uri youtubeUri = Uri.parse(sem1NotesAncientHisLinks.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");

                startActivity(intent);

                } else if (sem1NotesAncientHisLinks.get(position).contains("myinstamojo")) {
                    Uri paidNotesLink = Uri.parse(sem1NotesAncientHisLinks.get(position));
                    Intent intent = new Intent(Intent.ACTION_VIEW, paidNotesLink);
                    startActivity(intent);

                }else if(sem1NotesAncientHisLinks.get(position).contains("N/A")){
                    Toast.makeText(requireContext(), "Notes Will Available Soon!", Toast.LENGTH_SHORT).show();

                }
                else {
                    openIntend(sem1NotesAncientHisLinks.get(position));
                }
            }
        });*/


        //Ends Here
        /*Button unit1=view.findViewById(R.id.sem1_History_Unit1);
        Button unit1p2=view.findViewById(R.id.sem1_History_Unit1P2);
        Button unit2=view.findViewById(R.id.sem1_History_Unit2);
        Button PaidNotes=view.findViewById(R.id.sem1His_PaidNotes);


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
                            openIntend(links[0]);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });
                } else {
                    openIntend(links[0]);
                }

            }
        });
    unit1p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            openIntend(links[1]);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });
                } else {
                    openIntend(links[1]);
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
                            openIntend(links[2]);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });
                } else {
                    openIntend(links[2]);
                }

            }
        });

    PaidNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            PaidNotesLinkOpen(links[3]);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });
                } else {
                    PaidNotesLinkOpen(links[3]);
                }

            }
        });*/

        /*Button WatchVideos=view.findViewById(R.id.Watch_Videos);
        WatchVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define the YouTube channel URL
                String playlistUrl = "https://youtube.com/playlist?list=PL6Rby-wV4zjwEjDP-Y7E0j61Cwgi-vFH_&si=e4GmusEa3IW1bSHo";

                // Create an Intent with the ACTION_VIEW action and the YouTube channel URL
        });*/


    }


    /*public void loadads(){
        MobileAds.initialize(getActivity(), initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(getActivity(),"ca-app-pub-7092743628840352/8393735655", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
//                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
//                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }

    public void openIntend(String link){
        loadads();
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
}