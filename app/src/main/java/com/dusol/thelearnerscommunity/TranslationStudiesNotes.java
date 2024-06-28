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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

public class TranslationStudiesNotes extends Fragment {

    private InterstitialAd mInterstitialAd=null;
    int click=0;
    int NumberOfClickToShowAsd=2;
    private AdView adView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translationstudies, container, false);

        Bundle bundle = new Bundle();
        bundle.putString("Sem4_Notes", "Sem4_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem4_Notes_Open", bundle);


        /*Button trans1 = view.findViewById(R.id.unit1);
        Button trans2 = view.findViewById(R.id.unit2);

        Button trans3 = view.findViewById(R.id.unit3);
        Button trans4 = view.findViewById(R.id.unit4);
        Button trans5 = view.findViewById(R.id.unit5);

        Button trans6 = view.findViewById(R.id.buttonImp);
        Button trans7 = view.findViewById(R.id.button_matrial);
        Button trans8=view.findViewById(R.id.sem4Translation_PaidNotes);



        // Set click listener for the button
        trans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link="https://drive.google.com/file/d/179jeI76a_-i2PTRa2LTxMng7UyGLrS4q/view?usp=sharing.pdf";
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

                    String link="https://drive.google.com/file/d/179jeI76a_-i2PTRa2LTxMng7UyGLrS4q/view?usp=sharing.pdf";
                    openIntend(link);
                }
                // Create an Intent to open the new activity

            }
        });

        trans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link="https://drive.google.com/file/d/172DwfRfEMHpDXWo1NCPpNCU5miVMvZmq/view?usp=sharing.pdf";
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
                    String link="https://drive.google.com/file/d/172DwfRfEMHpDXWo1NCPpNCU5miVMvZmq/view?usp=sharing.pdf";
                    openIntend(link);
                }

            }
        });*/

        /*
        trans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the new activity
                String link="https://drive.google.com/file/d/1746tvJvWWDJBEbBA3onxVXQ-5ehmFDso/view?usp=sharing.pdf";
                Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });

        trans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the new activity
                String link="https://drive.google.com/file/d/16tQ4e9HeFPfn8PhrzRRYMhYx-7ZuNKqD/view?usp=sharing.pdf";
                Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });

        trans5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the new activity
                String link="https://drive.google.com/file/d/16tTFIQj1sCDLU9KSeOLYBPR2L2lN98S9/view?usp=sharing.pdf";
                Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });*/

        /*trans6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            String link="https://drive.google.com/file/d/17BRmrUWdtrXVxtyLPmEXJ0qDFyBEGJ7o/view?usp=sharing.pdf";
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
                    String link="https://drive.google.com/file/d/17BRmrUWdtrXVxtyLPmEXJ0qDFyBEGJ7o/view?usp=sharing.pdf";
                    openIntend(link);
                }


            }
        });
        trans7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Create an Intent to open the new activity
                            String link="https://drive.google.com/file/d/1uqYPXSX4ScH4V1bdEHsU-AizKBblcyhk/view?usp=sharing.pdf";
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
                    String link="https://drive.google.com/file/d/1uqYPXSX4ScH4V1bdEHsU-AizKBblcyhk/view?usp=sharing.pdf";
                    openIntend(link);
                }

            }
        });


        //Online Paid Notes
        trans8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if (mInterstitialAd != null && click % NumberOfClickToShowAsd==0) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            // Replace "https://www.example.com" with the URL you want to open in Chrome
                            String url = "https://thelearnerscommunitynotes.myinstamojo.com/product/3826316/du-sol-ncweb-3rd-4th-semester-sec-translatio?referred_from=category";

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
                    String url = "https://thelearnerscommunitynotes.myinstamojo.com/product/3826316/du-sol-ncweb-3rd-4th-semester-sec-translatio?referred_from=category";
                    PaidNotesLinkOpen(url);
                }

            }
        });*/


        ListView listView = view.findViewById(R.id.sem4TranslationStudiesNotesList);
        PDFDataManage.NotesManage(getActivity(),getContext(),"StudyNotes/BASem4/TranslationStudies",listView);


/*
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudyNotes/BASem4/TranslationStudies");
        List<String> sem4NotesTranslationStudiesName = new ArrayList<>();
        List<String> sem4NotesTranslationStudiesLinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);

                    sem4NotesTranslationStudiesName.add(key);
                    sem4NotesTranslationStudiesLinks.add(value);
                }

                if(sem4NotesTranslationStudiesName.isEmpty()){
                }else{

                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem4NotesTranslationStudiesName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {

            if (sem4NotesTranslationStudiesLinks.get(position).contains("youtube")) {

                Uri youtubeUri = Uri.parse(sem4NotesTranslationStudiesLinks.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);

            } else if (sem4NotesTranslationStudiesLinks.get(position).contains("myinstamojo")) {

                PaidNotesLinkOpen(sem4NotesTranslationStudiesLinks.get(position));
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
                            openIntend(sem4NotesTranslationStudiesLinks.get(position));
                        }
                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when ad fails to show.
                            Log.e("AdsLoad2", "Ad failed to show fullscreen content.");
                            openIntend(sem4NotesTranslationStudiesLinks.get(position));
                        }


                    });

                }else{
                    Log.e("AdsLoad4", "Last Else.");
                    openIntend(sem4NotesTranslationStudiesLinks.get(position));
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


       /* Button WatchVideos=view.findViewById(R.id.Watch_Videos);
        WatchVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define the YouTube channel URL
                String playlistUrl = "https://youtube.com/playlist?list=PL6Rby-wV4zjyaalCF4-Cd4nk0xo1c5k8S&si=9YjJvee1u0xseyZ0";

                // Create an Intent with the ACTION_VIEW action and the YouTube channel URL
                Uri youtubeUri = Uri.parse(playlistUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

                // Set the package name of the YouTube app
                intent.setPackage("com.google.android.youtube");

                startActivity(intent);}
        });
*/

        return view;


    }

/*
    public void loadads() {
        // Initialize the Mobile Ads SDK. You need to call this method once in your app.
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                // Initialization is complete; you can proceed with loading ads.
            }
        });

        // Create an AdRequest to specify ad targeting options.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Load an InterstitialAd (full-screen ad) using the provided Ad Unit ID.
        InterstitialAd.load(getActivity(), "ca-app-pub-7092743628840352/6848521284", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The interstitialAd is now ready to be shown.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error if the ad fails to load.
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

 */

}
