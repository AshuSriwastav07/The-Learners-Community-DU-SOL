package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

public class BCom_HonsSM extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bcom__hons, container, false);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("DBLinks").child("BCom_Hons");

        String[] links = {"https://web.sol.du.ac.in/info/bcom-hons-semester-i",
                "https://web.sol.du.ac.in/info/bcom-hons-semester-ii",
                "https://web.sol.du.ac.in/info/bcom-hons-semester-iii",
                "https://web.sol.du.ac.in/info/bcom-hons-semester-iv",
                "https://web.sol.du.ac.in/info/bcom-hons-semester-v",
                "https://web.sol.du.ac.in/info/bcom-hons-semester-vi",
                "https://web.sol.du.ac.in/info/nep-bcom-hons-semester-i",
                "https://web.sol.du.ac.in/info/nep-bcom-hons-semester-ii",
                "https://web.sol.du.ac.in/info/nep-bcom-hons-semester-iii"};

        Button[] buttons = new Button[9];

        buttons[0] = view.findViewById(R.id.btn1); //cbcs
        buttons[1] = view.findViewById(R.id.btn2); //cbcs
        buttons[2] = view.findViewById(R.id.btn3); //cbcs
        buttons[3] = view.findViewById(R.id.btn4); //cbcs
        buttons[4] = view.findViewById(R.id.btn5); //cbcs
        buttons[5] = view.findViewById(R.id.btn6); //cbcs

        buttons[6] = view.findViewById(R.id.btn7); //NEP
        buttons[7] = view.findViewById(R.id.btn8); //NEP
        buttons[8] = view.findViewById(R.id.btn9); //NEP //offline Links store

        Button btn9 = view.findViewById(R.id.btn10);  //NEP //RTDB Links
        Button btn10 = view.findViewById(R.id.btn11); //NEP
        Button btn11 = view.findViewById(R.id.btn12); //NEP


        // Add buttons for Button 2 to Button 12

        // Set click listeners for each button
        for (int i = 0; i < buttons.length; i++) {
            final int buttonIndex = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendStudyMaterialData("Semester " + buttonIndex);
                    openNextActivity(links[buttonIndex]);
                }
            });
        }

        mDatabase.child("sem4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                btn9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data != null) {
                            sendStudyMaterialData("NEP_Semester4");
                            openNextActivity(data);
                        }
                    }
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
                btn10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data != null) {
                            sendStudyMaterialData("NEP_Semester5");
                            openNextActivity(data);
                        }
                    }
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

                btn11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data != null) {
                            sendStudyMaterialData("NEP_Semester6");
                            openNextActivity(data);

                        }
                    }
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

    private void sendStudyMaterialData(String SemesterOpened) {
        new android.os.Handler().postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("BComHons", SemesterOpened);
            FirebaseAnalytics.getInstance(requireContext()).logEvent("StudyMaterial", bundle);
        }, 500);

    }

}