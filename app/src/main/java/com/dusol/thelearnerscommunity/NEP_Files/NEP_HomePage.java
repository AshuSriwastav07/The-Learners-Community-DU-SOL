package com.dusol.thelearnerscommunity.NEP_Files;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStoreTabActivity;
import com.dusol.thelearnerscommunity.PDFDataCollerction.PDFDataManage;
import com.dusol.thelearnerscommunity.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class NEP_HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_nep_semester);

        //ads
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button sem1NotesOpen=findViewById(R.id.NEP_Sem1);
        Button sem2NotesOpen=findViewById(R.id.NEP_Sem2);
        Button sem3NotesOpen=findViewById(R.id.NEP_Sem3);
        Button sem4NotesOpen=findViewById(R.id.NEP_Sem4);
        Button sem5NotesOpen=findViewById(R.id.NEP_Sem5);
        Button sem6NotesOpen =findViewById(R.id.NEP_Sem6);

        List<String> Values= PDFDataManage.CheckNotesAvailable();

        sem1NotesOpen.setOnClickListener(view->{
            if(!Values.isEmpty() && Values.get(0).equals("ok")){
            Intent intent=new Intent(this, NEP_Sem1_MainActivity.class);
            startActivity(intent);
            }else {
                Toast.makeText(this, "Notes Will Be Available Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        sem2NotesOpen.setOnClickListener(view->{
            if(!Values.isEmpty() && Values.get(1).equals("ok")){
                Intent intent=new Intent(this, NEP_Sem2_MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Notes Will Be Available Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        sem3NotesOpen.setOnClickListener(view->{
            if(!Values.isEmpty() && Values.get(2).equals("ok")){
                Intent intent=new Intent(this, NEP_Sem3_MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Notes Will Be Available Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        sem4NotesOpen.setOnClickListener(view->{
            if(!Values.isEmpty() && Values.get(3).equals("ok")){
                Intent intent=new Intent(this, NEP_Sem4_MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Notes Will Be Available Soon!", Toast.LENGTH_SHORT).show();
            }

        });


        sem5NotesOpen.setOnClickListener(view->{
            if(!Values.isEmpty() && Values.get(4).equals("ok")){
                Intent intent=new Intent(this, NEP_Sem5_MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Notes Will Be Available Soon!", Toast.LENGTH_SHORT).show();
            }

        });


        sem6NotesOpen.setOnClickListener(view->{
            if(!Values.isEmpty() && Values.get(5).equals("ok")){
                Intent intent=new Intent(this, NEP_Sem6_MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Notes Will Be Available Soon!", Toast.LENGTH_SHORT).show();
            }

        });


    }

    public void openStore(View v) { // This activity will send you to the notes store page
        Intent intent = new Intent(this, NotesStoreTabActivity.class);
        startActivity(intent);
    }
}