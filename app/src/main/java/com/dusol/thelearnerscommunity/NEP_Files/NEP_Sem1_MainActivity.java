package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.ManageFragment;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_Paper_1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_Paper_2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem1.Sem1_VAC;
import com.dusol.thelearnerscommunity.R;

public class NEP_Sem1_MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // Close the activity immediately without managing back stack
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nep_sem1_main);

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
                ManageFragment.setButtonToLoadFragment(context,new Sem1_Paper_1_DSC(),R.id.NEPsem1Fragment);
            }
        });

        paper2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_Paper_2_DSC(),R.id.NEPsem1Fragment);
            }
        });


        aec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_AEC(),R.id.NEPsem1Fragment);
            }
        });

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_SEC(),R.id.NEPsem1Fragment);
            }
        });

        ge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_GENERIC_ELECTIVE(),R.id.NEPsem1Fragment);
            }
        });
        vac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageFragment.setButtonToLoadFragment(context,new Sem1_VAC(),R.id.NEPsem1Fragment);
            }
        });


    }
}