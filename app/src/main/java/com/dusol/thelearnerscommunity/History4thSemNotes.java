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

public class History4thSemNotes extends Fragment {

    private  InterstitialAd mInterstitialAd;
    int click=0;
    int NumberOfClickToShowAsd=2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history4thsem, container, false);

        ListView listView = view.findViewById(R.id.sem4IndianHistoryNotesList);

        Bundle bundle = new Bundle();
        bundle.putString("Sem4_Notes", "Sem4_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem4_Notes_Open", bundle);

        loadads();
        /*Button Sem4His1 = view.findViewById(R.id.Sem4_History_Unit1);
        Button Sem4His2 = view.findViewById(R.id.Sem4_History_Unit2);
        Button Sem4His3 = view.findViewById(R.id.Sem4_History_Unit3);
        Button Sem4His4 = view.findViewById(R.id.Sem4_History_Unit4);
        Button Sem4His5 = view.findViewById(R.id.Sem4_History_Unit5);
        Button PaidNotes = view.findViewById(R.id.Sem4His_PaidNotes);
//        Button Sem4His6 = view.findViewById(R.id.Sem4_History_Unit6);
//        Button Sem4His7 = view.findViewById(R.id.Sem4_History_Unit7);
//        Button Sem4His8 = view.findViewById(R.id.Sem4_History_Unit8);*/



        /*
        Sem4His1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link=links[0];
                            openIntend(link);

                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }

                    });

                } else {

                    // Create an Intent to open the new activity
                    String link=links[0]; //Unit1
                    openIntend(link);
                }




            }
        });

        //Function Start
        Sem4His2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link=links[1];
                            openIntend(link);

                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }
                    });

                } else {

                    // Create an Intent to open the new activity
                    String link=links[1]; //Unit1
                    openIntend(link);

                }




            }
        }); //Function Ends

        //Function Start
        Sem4His3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link=links[2];
                            openIntend(link);

                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }
                    });

                } else {

                    // Create an Intent to open the new activity
                    String link=links[2]; //Unit1
                    openIntend(link);

                }
            }
        }); //Function Ends

        //Function Start
        Sem4His4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link=links[3];
                            openIntend(link);

                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }
                    });

                } else {

                    // Create an Intent to open the new activity
                    String link=links[3]; //Unit1
                    openIntend(link);

                }
            }
        }); //Function Ends

        //Function Start
        Sem4His5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link=links[4];
                            openIntend(link);

                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }
                    });

                } else {

                    // Create an Intent to open the new activity
                    String link=links[4]; //Unit1
                    openIntend(link);

                }
            }
        }); //Function Ends

        //Paid Notes
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
                            String url = "https://thelearnerscommunitynotes.myinstamojo.com/product/3806923/1700-1950-4th-semester-history-dsc-unit-1-to-1a993?referred_from=category";
                            PaidNotesLinkOpen(url);



                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            mInterstitialAd = null;
                            loadads();
                        }
                    });

                } else {

                    // Replace "https://www.example.com" with the URL you want to open in Chrome
                    String url = "https://thelearnerscommunitynotes.myinstamojo.com/product/3806923/1700-1950-4th-semester-history-dsc-unit-1-to-1a993?referred_from=category";
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
                String playlistUrl = "https://youtube.com/playlist?list=PL6Rby-wV4zjwYzSxbgcJ5PvytiHIRH65U&si=yyWnSTKys8k-PC0v";

                // Create an Intent with the ACTION_VIEW action and the YouTube channel URL
                Uri youtubeUri = Uri.parse(playlistUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");

                startActivity(intent);}
        });
*/

        /*
        //Function Start
        Sem4His6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link=links[5];
                            Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                            intent.putExtra("link", link);
                            startActivity(intent);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            mInterstitialAd = null;
                        }
                    });

                } else {

                    // Create an Intent to open the new activity
                    String link=links[5]; //Unit1
                    Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                    intent.putExtra("link", link);
                    startActivity(intent);
                }
            }
        }); //Function Ends
*/

        /*
        //Function Start
        Sem4His7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link=links[6];
                            Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                            intent.putExtra("link", link);
                            startActivity(intent);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            mInterstitialAd = null;
                        }
                    });

                } else {

                    // Create an Intent to open the new activity
                    String link=links[6]; //Unit1
                    Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                    intent.putExtra("link", link);
                    startActivity(intent);
                }
            }
        }); //Function Ends
*/

        /*
        //Function Start
        Sem4His8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link=links[7];
                            Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                            intent.putExtra("link", link);
                            startActivity(intent);
                            loadads();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            mInterstitialAd = null;
                        }
                    });

                } else {

                    // Create an Intent to open the new activity
                    String link=links[7]; //Unit1
                    Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                    intent.putExtra("link", link);
                    startActivity(intent);
                }
            }
        }); //Function Ends
*/


//Ads Start Here
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudyNotes/BASem4/IndanHistory");
        List<String> sem4NotesHistoryName = new ArrayList<>();
        List<String> sem4NotesHistoryLinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);

                    sem4NotesHistoryName.add(key);
                    sem4NotesHistoryLinks.add(value);
                }

                if(sem4NotesHistoryName.isEmpty()){
                }else{

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem4NotesHistoryName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {

            if (sem4NotesHistoryLinks.get(position).contains("youtube")) {

                Uri youtubeUri = Uri.parse(sem4NotesHistoryLinks.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);

            } else if (sem4NotesHistoryLinks.get(position).contains("myinstamojo")) {

                PaidNotesLinkOpen(sem4NotesHistoryLinks.get(position));
            } else {

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
                            openIntend(sem4NotesHistoryLinks.get(position));
                        }
                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when ad fails to show.
                            Log.e("AdsLoad2", "Ad failed to show fullscreen content.");
                            openIntend(sem4NotesHistoryLinks.get(position));
                        }


                    });

                }else{
                    Log.e("AdsLoad4", "Last Else.");
                    openIntend(sem4NotesHistoryLinks.get(position));
                }
            }
        });


        //Ads Start Here
        MobileAds.initialize(requireActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        adView.loadAd(adRequest);





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
        loadads();
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
        loadads();
    }
}