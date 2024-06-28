package com.dusol.thelearnerscommunity.NEP_Files;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dusol.thelearnerscommunity.R;

public class NEP_HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nep_home_page);

        Button sem1NotesOpen=findViewById(R.id.NEP_Sem1);


        sem1NotesOpen.setOnClickListener(view->{
            Intent intent=new Intent(this, NEP_Sem1_MainActivity.class);
            startActivity(intent);
        });









    }
}