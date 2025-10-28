package com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.dusol.thelearnerscommunity.R;
import com.google.firebase.analytics.FirebaseAnalytics;


public class Sem8_VAC extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.sem8_nep_vac_fragment, container, false);

        Bundle bundle = new Bundle();
        bundle.putString("Sem8_NEP_Notes", "Sem8_NEP_Notes_Open");
        FirebaseAnalytics.getInstance(requireContext()).logEvent("Sem8_NEP_Notes_Open", bundle);

        ListView listView = view.findViewById(R.id.sem8VacListView);
        PDFDataManage.NotesManage(getActivity(),getContext(),"NEP_Notes/sem8/vac",listView);

        return view;

    }
}