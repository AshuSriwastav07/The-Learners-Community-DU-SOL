package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BA_EconomicsHonsSM extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ba__economics_hons, container, false);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("DBLinks").child("BAEcoHons");

        Button btn1 = view.findViewById(R.id.btn1); //NEP
        Button btn2 = view.findViewById(R.id.btn2); //NEP
        Button btn3 = view.findViewById(R.id.btn3); //NEP
        Button btn4 = view.findViewById(R.id.btn4); //NEP
        Button btn5 = view.findViewById(R.id.btn5); //NEP
        Button btn6 = view.findViewById(R.id.btn6); //NEP

        mDatabase.child("sem1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                assert data != null;
                Log.d("FirebaseLinks", data);
                btn1.setOnClickListener(v -> {
                    sendStudyMaterialData("NEP_Semester1");
                    openNextActivity(data);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("sem2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                assert data != null;
                Log.d("FirebaseLinks", data);
                btn2.setOnClickListener(v -> {
                    sendStudyMaterialData("NEP_Semester2");
                    openNextActivity(data);
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("sem3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                assert data != null;
                Log.d("FirebaseLinks", data);
                btn3.setOnClickListener(v -> {
                    sendStudyMaterialData("NEP_Semester3");
                    openNextActivity(data);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("sem4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                assert data != null;
                Log.d("FirebaseLinks", data);
                btn4.setOnClickListener(v -> {
                    sendStudyMaterialData("NEP_Semester4");
                    openNextActivity(data);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("sem5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                assert data != null;
                Log.d("FirebaseLinks", data);
                btn5.setOnClickListener(v -> {
                    sendStudyMaterialData("NEP_Semester5");
                    openNextActivity(data);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("sem6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                assert data != null;
                Log.d("FirebaseLinks", data);
                btn6.setOnClickListener(v -> {
                    sendStudyMaterialData("NEP_Semester6");
                    openNextActivity(data);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }


    private void openNextActivity(String link) {
        if (link.equals("N/A")) {
            Toast.makeText(getActivity(), "Not Available", Toast.LENGTH_SHORT).show();
        } else {
            Uri BookLink = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_VIEW, BookLink);
            startActivity(intent);
        }

    }

    private void sendStudyMaterialData(String SemesterOpened){
        new android.os.Handler().postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("BA_EcoHons", SemesterOpened);
            FirebaseAnalytics.getInstance(requireContext()).logEvent("StudyMaterial", bundle);
        },500);

    }
}