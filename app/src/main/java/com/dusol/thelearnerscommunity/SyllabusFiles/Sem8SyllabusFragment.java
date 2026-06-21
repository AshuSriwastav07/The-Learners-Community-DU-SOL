package com.dusol.thelearnerscommunity.SyllabusFiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.dusol.thelearnerscommunity.R;

public class Sem8SyllabusFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sem8_syllabus, container, false);

        ListView listView = view.findViewById(R.id.sem8SyllabusList);

        syllabus_pdf_manage.SyllabusPDFManage(getActivity(), getContext(), "Syllabus/sem8", listView);

        return view;
    }
}
