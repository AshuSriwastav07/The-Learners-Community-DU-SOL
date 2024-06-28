package com.dusol.thelearnerscommunity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.dusol.thelearnerscommunity.SyllabusFiles.Syllabus_Web_MainActivity;

public class Datesheet_DailogBox extends DialogFragment {

    // Variables to store links and title
    private String link1;
    private String link2;
    private String link3;
    private String link4;
    private String link8;
    private String link5;
    private String link6;
    private String link7;
    private String title;

    // Method to create a new instance of the dialog box
    public static Datesheet_DailogBox newInstance(String link1, String link2, String link3, String link4,String link5,String link6,String link7,String link8, String title) {
        Datesheet_DailogBox fragment = new Datesheet_DailogBox();
        Bundle args = new Bundle();

        args.putString("link1", link1);
        args.putString("link2", link2);
        args.putString("link3", link3);
        args.putString("link4", link4);

        args.putString("link5", link5);
        args.putString("link6", link6);
        args.putString("link7", link7);
        args.putString("link8", link8);

        args.putString("title", title);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = inflater.inflate(R.layout.fragment_datesheet__dailog_box, null);

        // Get arguments from bundle
        assert getArguments() != null;
        link1 = getArguments().getString("link1");
        link2 = getArguments().getString("link2");
        link3 = getArguments().getString("link3");
        link4 = getArguments().getString("link4");
        link5 = getArguments().getString("link5");
        link6 = getArguments().getString("link6");
        link7 = getArguments().getString("link7");
        link8 = getArguments().getString("link8");
        title = getArguments().getString("title");

        // Initialize buttons and text view
        Button openNEP_BA = view.findViewById(R.id.NEPbutton1);
        Button openNEP_BAHons = view.findViewById(R.id.NEPbutton2);
        Button openNEP_Bcom = view.findViewById(R.id.NEPbutton3);
        Button openNEP_BcomHons = view.findViewById(R.id.NEPbutton4);
        Button openCBCS_BA = view.findViewById(R.id.CBCS_button1);
        Button openCBCS_BAHons = view.findViewById(R.id.CBCS_button2);
        Button openCBCS_Bcom = view.findViewById(R.id.CBCS_button3);
        Button openCBCS_BcomHons = view.findViewById(R.id.CBCS_button4);
        TextView mTextView = view.findViewById(R.id.DatesheetTitle);
        mTextView.setText(title);

        // Set click listeners for buttons
        openNEP_BA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if link is available
                if (link1.equals("N/A")) {
                    Toast.makeText(getContext(), " Not Available Now", Toast.LENGTH_SHORT).show();
                } else {
                    // Open web activity with link
                    Intent intent = new Intent(requireContext(), Syllabus_Web_MainActivity.class);
                    intent.putExtra("link", link1);
                    startActivity(intent);
                }
            }
        });

        openNEP_BAHons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if link is available
                if (link2.equals("N/A")) {
                    Toast.makeText(getContext(), " Not Available Now", Toast.LENGTH_SHORT).show();
                } else {
                    // Open web activity with link
                    Intent intent = new Intent(requireContext(), Syllabus_Web_MainActivity.class);
                    intent.putExtra("link", link2);
                    startActivity(intent);
                }
            }
        });

        openNEP_Bcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if link is available
                if (link3.equals("N/A")) {
                    Toast.makeText(getContext(), " Not Available Now", Toast.LENGTH_SHORT).show();
                } else {
                    // Open web activity with link
                    Intent intent = new Intent(requireContext(), Syllabus_Web_MainActivity.class);
                    intent.putExtra("link", link3);
                    startActivity(intent);
                }
            }
        });

        openNEP_BcomHons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if link is available
                if (link4.equals("N/A")) {
                    Toast.makeText(getContext(), " Not Available Now", Toast.LENGTH_SHORT).show();
                } else {
                    // Open web activity with link
                    Intent intent = new Intent(requireContext(), Syllabus_Web_MainActivity.class);
                    intent.putExtra("link", link4);
                    startActivity(intent);
                }
            }
        });
        openCBCS_BA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if link is available
                if (link5.equals("N/A")) {
                    Toast.makeText(getContext(), " Not Available Now", Toast.LENGTH_SHORT).show();
                } else {
                    // Open web activity with link
                    Intent intent = new Intent(requireContext(), Syllabus_Web_MainActivity.class);
                    intent.putExtra("link", link5);
                    startActivity(intent);
                }
            }
        });

        openCBCS_BAHons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if link is available
                if (link6.equals("N/A")) {
                    Toast.makeText(getContext(), " Not Available Now", Toast.LENGTH_SHORT).show();
                } else {
                    // Open web activity with link
                    Intent intent = new Intent(requireContext(), Syllabus_Web_MainActivity.class);
                    intent.putExtra("link", link6);
                    startActivity(intent);
                }
            }
        });

        openCBCS_Bcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if link is available
                if (link7.equals("N/A")) {
                    Toast.makeText(getContext(), " Not Available Now", Toast.LENGTH_SHORT).show();
                } else {
                    // Open web activity with link
                    Intent intent = new Intent(requireContext(), Syllabus_Web_MainActivity.class);
                    intent.putExtra("link", link7);
                    startActivity(intent);
                }
            }
        });

        openCBCS_BcomHons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if link is available
                if (link8.equals("N/A")) {
                    Toast.makeText(getContext(), " Not Available Now", Toast.LENGTH_SHORT).show();
                } else {
                    // Open web activity with link
                    Intent intent = new Intent(requireContext(), Syllabus_Web_MainActivity.class);
                    intent.putExtra("link", link8);
                    startActivity(intent);
                }
            }
        });

        // Set positive button to close dialog
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
