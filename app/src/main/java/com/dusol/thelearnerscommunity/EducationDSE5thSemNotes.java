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


public class EducationDSE5thSemNotes extends Fragment {
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
        View view=inflater.inflate(R.layout.fragment_education_dse_5th_sem, container, false);

        ListView listView = view.findViewById(R.id.sem5EducationDSENotesList);

        Bundle bundle = new Bundle();
        bundle.putString("Sem5_Notes", "Sem5_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem5_Notes_Open", bundle);


        loadads();
        //Buttons
        /*Button unit1=view.findViewById(R.id.unit1);
        Button unit2=view.findViewById(R.id.unit2);
        Button unit3=view.findViewById(R.id.unit3);
        Button unit4=view.findViewById(R.id.unit4);
        Button PaidNotesPDF=view.findViewById(R.id.PaidNotesPDF);*/


        PDFDataManage.NotesManage(getActivity(),getContext(),"StudyNotes/BASem5/DSEEducation",listView);


        //Add start
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }

            public void onAdLoaded() {
                // Ad has been successfully loaded, perform other work here
                // For example, you can start an activity or update UI elements
            }
            public void onAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load, handle the error if needed
            }

        });
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Ads Ends


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



    public void loadads(){
        MobileAds.initialize(requireContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(getActivity(),"ca-app-pub-7092743628840352/2084823075", adRequest,
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