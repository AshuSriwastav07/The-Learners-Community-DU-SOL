package com.dusol.thelearnerscommunity.QuestionPapers.BAProg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.dusol.thelearnerscommunity.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class semester1QuestionPapers extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semester1_question_papers, container, false);
        ListView listView = view.findViewById(R.id.sem1QPListView);

        // Initialize Ads
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Fetch PDF links from Firebase based on course and semester
        PDFDataManage.NotesManage(getActivity(), getContext(), "QuestionPapers/Sem1/BA", listView);

        return view;
    }
}