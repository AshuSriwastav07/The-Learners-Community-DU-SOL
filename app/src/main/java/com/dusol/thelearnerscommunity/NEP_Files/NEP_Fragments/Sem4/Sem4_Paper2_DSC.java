package com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.dusol.thelearnerscommunity.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Sem4_Paper2_DSC extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.sem4_nep_paper2_fragment, container, false);

        Bundle bundle = new Bundle();
        bundle.putString("Sem4_NEP_Notes", "Sem4_NEP_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem4_NEP_Notes_Open", bundle);

        ListView listView = view.findViewById(R.id.sem4Paper2ListView);
        PDFDataManage.NotesManage(getActivity(),getContext(),"NEP_Notes/sem4/paper2",listView);

        return view;

    }
}