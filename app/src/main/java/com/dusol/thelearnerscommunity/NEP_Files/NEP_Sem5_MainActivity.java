package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.ManageFragment;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem5.Sem5_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem5.Sem5_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem5.Sem5_Paper1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem5.Sem5_Paper2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem5.Sem5_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem5.Sem5_VAC;
import com.dusol.thelearnerscommunity.R;

public class NEP_Sem5_MainActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        // Close the activity immediately without managing back stack
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nep_sem5_main);

        //Subject Select Buttons

        Button paper1=findViewById(R.id.paper1DSC);
        Button paper2=findViewById(R.id.paper2DSC);
        Button aec=findViewById(R.id.aec);
        Button ge=findViewById(R.id.GenericElective);
        Button sec=findViewById(R.id.sec);
        Button vac=findViewById(R.id.vac);


        FragmentActivity context=this;

        paper1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem5_Paper1_DSC(),R.id.NEPsem5Fragment);
            }
        });

        paper2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem5_Paper2_DSC(),R.id.NEPsem5Fragment);
            }
        });


        aec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem5_AEC(),R.id.NEPsem5Fragment);
            }
        });

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem5_SEC(),R.id.NEPsem5Fragment);
            }
        });

        ge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem5_GENERIC_ELECTIVE(),R.id.NEPsem5Fragment);
            }
        });
        vac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem5_VAC(),R.id.NEPsem5Fragment);
            }
        });
    }
}