/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

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

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class EnglishGE6thSemNotes extends Fragment {


    private InterstitialAd mInterstitialAd;
    int click=0;
    int NumberOfClickToShowAsd=2;
    private AdView adView;

    /*String links[]={
            "https://drive.google.com/file/d/1JAZvYsR6fEjZj2chg59vZEjxO_9X8DWM/view?usp=sharing",
            "https://drive.google.com/file/d/1XY3CqQ46wDo7HD2ommub4p9D74pTVMjn/view?usp=sharing",
            "https://drive.google.com/file/d/1NGMxXuWPtcLITeieNEpIIbcwPGxdRM1s/view?usp=sharing",
            "https://thelearnerscommunitynotes.myinstamojo.com/product/3804807/du-sol-6th-semester-ge-english-media-and-com?referred_from=category",

    };
*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_english_ge6th_sem, container, false);

        ListView listView = view.findViewById(R.id.sem6EnglishGENotesList);

        Bundle bundle = new Bundle();
        bundle.putString("Sem6_Notes", "Sem6_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem6_Notes_Open", bundle);


        loadads();
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }

        });

        AdView adView = view.findViewById(R.id.adView);
//        AdView mAdView1 = view.findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Ends Here
      /*  Button unit1=view.findViewById(R.id.unit1);
        Button unit2=view.findViewById(R.id.unit2);
        Button ImpQA=view.findViewById(R.id.impNotes);
        Button PaidNotes=view.findViewById(R.id.PaidNotesPDF);
*/

     /*   unit1.setOnClickListener(new View.OnClickListener() {
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

        ImpQA.setOnClickListener(new View.OnClickListener() {
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
        });

*/


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudyNotes/BASem6/EnglishGE");
        List<String> sem6EnglishGEName = new ArrayList<>();
        List<String> sem6EnglishGELinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);

                    sem6EnglishGEName.add(key);
                    sem6EnglishGELinks.add(value);
                }

                if(sem6EnglishGEName.isEmpty()){
                }else{

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem6EnglishGEName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {

            if (sem6EnglishGELinks.get(position).contains("youtube")) {

                Uri youtubeUri = Uri.parse(sem6EnglishGELinks.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);

            } else if (sem6EnglishGELinks.get(position).contains("myinstamojo")) {

                PaidNotesLinkOpen(sem6EnglishGELinks.get(position));
            } else {

                if(!Objects.equals(sem6EnglishGELinks.get(position), "N/A")){

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
                                openIntend(sem6EnglishGELinks.get(position));
                            }
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.e("AdsLoad2", "Ad failed to show fullscreen content.");
                                openIntend(sem6EnglishGELinks.get(position));
                            }


                        });

                    }else{
                        Log.e("AdsLoad4", "Last Else.");
                        openIntend(sem6EnglishGELinks.get(position));
                    }
                }else{
                    Toast.makeText(requireContext(), "Notes Not Available Now!", Toast.LENGTH_SHORT).show();
                }
            }
        });





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
    }
}