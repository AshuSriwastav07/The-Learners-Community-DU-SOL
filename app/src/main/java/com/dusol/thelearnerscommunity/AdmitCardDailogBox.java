package com.dusol.thelearnerscommunity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AdmitCardDailogBox extends DialogFragment  {

    private String link1;private String link2;private String link3;private String link4;private String title;private String sem1;private String sem2;private String sem3;

    public static AdmitCardDailogBox newInstance(String link1, String link2, String link3, String sem1, String sem2, String sem3, String title) {
        AdmitCardDailogBox fragment = new AdmitCardDailogBox();
        Bundle args = new Bundle();
        args.putString("link1", link1);
        args.putString("link2", link2);
        args.putString("link3", link3);
        args.putString("sem1", sem1);
        args.putString("sem2", sem2);
        args.putString("sem3", sem3);
        args.putString("title", title);

        fragment.setArguments(args);

        Log.d("AdmitCard","Working");
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admit_card_dailog_box, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        link1= getArguments().getString("link1");
        link2= getArguments().getString("link2");
        link3= getArguments().getString("link3");
        title= getArguments().getString("title");
        sem1= getArguments().getString("sem1");
        sem2= getArguments().getString("sem2");
        sem3= getArguments().getString("sem3");


        Button Open_sem1 = view.findViewById(R.id.button1);
        Button Open_sem2 = view.findViewById(R.id.button2);
        Button Open_sem3 = view.findViewById(R.id.button3);

        TextView sem1Title = view.findViewById(R.id.sem1);
        TextView sem2Title = view.findViewById(R.id.sem2);
        TextView sem3Title = view.findViewById(R.id.sem3);
        TextView mTextView = view.findViewById(R.id.AdmitCardTitle);

        //Set Title according to data receive

        sem1Title.setText(sem1);
        sem2Title.setText(sem2);
        sem3Title.setText(sem3);
        mTextView.setText(title);



        Open_sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("N/A".equals(link1)) {
                    Toast.makeText(getContext(), sem1+" Not Available Now", Toast.LENGTH_SHORT).show();
                } else{
                    Log.d("link",link1);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link1));
                startActivity(intent);}
            }
        });


        Open_sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("N/A".equals(link2)) {
                    Toast.makeText(getContext(), sem2+" Not Available Now", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link2));
                    startActivity(intent);}
            }
        });



        Open_sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("N/A".equals(link3)) {
                    Toast.makeText(getContext(), sem3+" Not Available Now", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link3));
                    startActivity(intent);}

            }
        });



        builder.setView(view)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Close the dialog
                        dialog.dismiss();
                    }
                });


        return view;
    }
}