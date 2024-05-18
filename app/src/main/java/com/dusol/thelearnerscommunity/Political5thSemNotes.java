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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Political5thSemNotes extends Fragment {

    private InterstitialAd mInterstitialAd;
    int click=0;
    int NumberOfClickToShowAsd=2;
    private AdView adView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_political5th_sem, container, false);

        ListView listView = view.findViewById(R.id.sem5PoliticalScienceDSENotesList);

        Bundle bundle = new Bundle();
        bundle.putString("Sem5_Notes", "Sem5_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem5_Notes_Open", bundle);


        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        //Ends Here

        PDFDataManage.NotesManage(getActivity(),getContext(),"StudyNotes/BASem5/PolSciDSE",listView);


        loadads();

        /*Button unit1=view.findViewById(R.id.unit1);
        Button unit2=view.findViewById(R.id.unit2);
        Button PaidNotes=view.findViewById(R.id.PaidNotesPDF);

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
                            PaidNotesLinkOpen(links[2]);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            loadads();
                        }
                    });

                } else {
                    PaidNotesLinkOpen(links[2]);
                }
            }
        });*/


        /*final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudyNotes/BASem5/PolSciDSE");
        List<String> sem5NotesPoliticalSciDSEName = new ArrayList<>();
        List<String> sem5NotesPoliticalSciDSELinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);

                    sem5NotesPoliticalSciDSEName.add(key);
                    sem5NotesPoliticalSciDSELinks.add(value);
                }

                if(sem5NotesPoliticalSciDSEName.isEmpty()){
                }else{

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem5NotesPoliticalSciDSEName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {

            if (sem5NotesPoliticalSciDSELinks.get(position).contains("youtube")) {

                Uri youtubeUri = Uri.parse(sem5NotesPoliticalSciDSELinks.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);

            } else if (sem5NotesPoliticalSciDSELinks.get(position).contains("myinstamojo")) {

                PaidNotesLinkOpen(sem5NotesPoliticalSciDSELinks.get(position));
            } else {

                if(!Objects.equals(sem5NotesPoliticalSciDSELinks.get(position), "N/A")){

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
                                openIntend(sem5NotesPoliticalSciDSELinks.get(position));
                            }
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.e("AdsLoad2", "Ad failed to show fullscreen content.");
                                openIntend(sem5NotesPoliticalSciDSELinks.get(position));
                            }


                        });

                    }else{
                        Log.e("AdsLoad4", "Last Else.");
                        openIntend(sem5NotesPoliticalSciDSELinks.get(position));
                    }
                }else{
                    Toast.makeText(requireContext(), "Notes Not Available Now!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        return view;

    }

    public void loadads(){
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
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

    /*public void openIntend(String link){
        Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
        intent.putExtra("link", link);
        startActivity(intent);
        loadads();
    }

    public void PaidNotesLinkOpen(String url){
        // Create an intent with ACTION_VIEW and the URL as the data
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Check if there is a web browser (e.g., Chrome) available to handle the intent
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            // Start the activity to open the URL in the browser
            startActivity(intent);
            loadads();
        } else {
            Toast.makeText(requireContext(), "No web browser found to open the URL.", Toast.LENGTH_SHORT).show();
        }
    }*/

}