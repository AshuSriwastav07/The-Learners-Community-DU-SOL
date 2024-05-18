/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */


/*

package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class semester3QuestionPapers extends Fragment {
    private InterstitialAd mInterstitialAd;
    int click1=1;
    int NumberOfClickToShowAsd=2;
    private final String[] links={
            "https://drive.google.com/file/d/14WbxIy0S9m0FVcUoiNOV3jL5vIVKporN/view?usp=sharing",
            "https://drive.google.com/file/d/160y1x_IhZmIDcarLiWNYcSIUGcIpUFZZ/view?usp=sharing",
            "https://drive.google.com/file/d/18XFPwOkDfRn9CiIXt32f-GWRhK3YyW8r/view?usp=sharing",
            "https://drive.google.com/file/d/198-xPjoSidBNyOlCVKOHRtvCNgMe3Qzi/view?usp=sharing",
            "https://drive.google.com/file/d/1VTerXC4728j837Ih7CH-Tu0b7ERVd-Vt/view?usp=sharing",

    }; //Add Links Here
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_semester3_question_papers, container, false);

        //Add start
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //add ends

        Button qp1 = view.findViewById(R.id.qp1); //Make Button Here
        Button qp2 = view.findViewById(R.id.qp2);
        Button qp3 = view.findViewById(R.id.qp3);
        Button qp4 = view.findViewById(R.id.qp4);
        Button qp5 = view.findViewById(R.id.qp5);

        //Define This function for every button and use button name
        qp1.setOnClickListener(v -> {
            // Create an Intent to open the new activity
            String link=links[0];
            openIntend(link);

        });
        //Define This function for every button and use button name
        qp2.setOnClickListener(v -> {
            // Create an Intent to open the new activity
            String link=links[1];
            openIntend(link);

        });
        //Define This function for every button and use button name
        qp3.setOnClickListener(v -> {
            // Create an Intent to open the new activity
            String link=links[2];
            openIntend(link);

        });
        //Define This function for every button and use button name
        qp4.setOnClickListener(v -> {
            // Create an Intent to open the new activity
            String link=links[3];
            openIntend(link);

        });
        //Define This function for every button and use button name
        qp5.setOnClickListener(v -> {
            // Create an Intent to open the new activity
            String link=links[4];
            openIntend(link);
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

    public void startads(String Links[],int linkNum){ //Provide array of links and Index number of link
        click1++; //count number of click
        if (mInterstitialAd != null && click1 % NumberOfClickToShowAsd==0) {
            mInterstitialAd.show(getActivity()); // Google ads will node if user click on question paper button every second time
            Log.d("ShowAds","Ad Load");
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d("ShowAds", "Ad was clicked.");
                }
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    openIntend(links[linkNum]);
                    Log.d("ShowAds","Ad Close and Activity Open");
                    loadads();

                }
                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                    Log.e("ShowAds", "Ad failed to show fullscreen content.");
                    loadads();
                }
                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d("ShowAds", "Ad recorded an impression.");
                    loadads();
                }
                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    super.onAdShowedFullScreenContent();
                    Log.d("ShowAds", "Ad showed fullscreen content.");
                    loadads();
                }
            });
        } else {
            openIntend(links[linkNum]);
            Log.d("ShowAds","Ad did not load open Activity");
            loadads();

        }
    }

    public void openIntend(String link){
        Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
        intent.putExtra("link", link);
        startActivity(intent);

    }
}*/


package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class semester3QuestionPapers extends Fragment {

    private InterstitialAd mInterstitialAd;
    int click1=2;
    int NumberOfClickToShowAsd=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semester3_question_papers, container, false);
        ListView listView = view.findViewById(R.id.sem3QPListView);

        //Add start
        com.google.android.gms.ads.MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//Ads Ends

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("QP_Links/sem3");
        List<String> sem3QpName = new ArrayList<>();
        List<String> sem3QpLinks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    Log.d("DataBaseLinks", value);
                    Log.d("DataBaseLinks", key);

                    sem3QpName.add(key);
                    sem3QpLinks.add(value);
                }

                if(sem3QpName.isEmpty()){
                }else{
                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, sem3QpName);
                    listView.setAdapter(itemsAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                start_Ads(sem3QpLinks.get(position),click1);
            }
        });

        return view;
    }


    public void openIntend(String link){
        if(!Objects.equals(link, "N/A")) {
            Intent intent = new Intent(getActivity(), QP_Web_Page_MainActivity.class);
            intent.putExtra("link", link);
            startActivity(intent);
        }else{
            Toast.makeText(requireActivity(), "Not Available Now!", Toast.LENGTH_SHORT).show();
        }
    }


    public void loadads(){
        MobileAds.initialize(requireActivity(), initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(requireActivity(),"ca-app-pub-7092743628840352/8393735655", adRequest,
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
                        loadads();
                    }
                });
    }


    public void start_Ads(String link,int click){
        click++;
        if (mInterstitialAd != null && click %NumberOfClickToShowAsd !=1) {
            mInterstitialAd.show(requireActivity());
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){

                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d("LinkPageAds", "Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
//                        startActivity(new Intent(getApplicationContext(),Syllabus_MainActivity.class));
                    loadads();
                    openIntend(link);

                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    // Called when ad fails to show.
                    Log.e("LinkPageAds", "Ad failed to show fullscreen content.");
//                mInterstitialAd = null;
                    loadads();
                }

                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d("LinkPageAds", "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d("LinkPageAds", "Ad showed fullscreen content.");
                }
            });
        } else {
            openIntend(link);
            loadads();

        }


    }

}
