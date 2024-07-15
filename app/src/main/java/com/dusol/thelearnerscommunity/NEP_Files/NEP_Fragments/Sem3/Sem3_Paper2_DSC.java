package com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.dusol.thelearnerscommunity.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Sem3_Paper2_DSC extends Fragment {


    public Sem3_Paper2_DSC() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.sem3_nep_paper2_dsc_fragment, container, false);

        Bundle bundle = new Bundle();
        bundle.putString("Sem3_NEP_Notes", "Sem3_NEP_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem3_NEP_Notes_Open", bundle);

        ListView listView = view.findViewById(R.id.sem3Paper2ListView);
        PDFDataManage.NotesManage(getActivity(),getContext(),"NEP_Notes/sem3/paper2",listView);
        return view;

    }
}