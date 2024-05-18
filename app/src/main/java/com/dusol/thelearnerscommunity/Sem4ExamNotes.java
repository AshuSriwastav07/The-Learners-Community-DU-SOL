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


public class Sem4ExamNotes extends Fragment {

    private InterstitialAd mInterstitialAd;
    int click1=2;
    int NumberOfClickToShowAsd=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sem4_exam_notes, container, false);

        //Start banner ads

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //ends banner ads

        ListView listView=view.findViewById(R.id.sem4ExamNotesList);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference sem5Links = database.getReference("StudyNotes/BASem4/ExamNotes");

        List<String> sem4ExamNotesName = new ArrayList<>();  //Store Course Name
        List<String> sem4ExamNotesLink = new ArrayList<>();  //store course pdf links

        sem5Links.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue(String.class);

                    sem4ExamNotesName.add(key);
                    sem4ExamNotesLink.add(value);

                }

                ArrayAdapter adapter=new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, sem4ExamNotesName);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((parent, view1, position, id) ->
                        start_Ads(sem4ExamNotesLink.get(position),click1));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    public void openIntend(String link){
        if(!Objects.equals(link, "N/A")) {
            Intent intent = new Intent(getActivity(), Notes_HomeWeb_MainActivity.class);
            intent.putExtra("link", link);
            startActivity(intent);
        }else if(link.contains("youtube")) {
            Uri youtubeUri = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

            // Set the package name of the YouTube app
            intent.setPackage("com.google.android.youtube");
            startActivity(intent);

        }else if(link.equals("N/A")){
            Toast.makeText(requireActivity(), "Not Available Now!", Toast.LENGTH_SHORT).show();
        }
    }


    public void loadads(){
        MobileAds.initialize(requireActivity(), initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(requireActivity(),"ca-app-pub-7092743628840352/2084823075", adRequest,
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