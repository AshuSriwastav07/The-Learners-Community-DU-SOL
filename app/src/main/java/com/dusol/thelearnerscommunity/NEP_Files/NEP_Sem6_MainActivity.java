package com.dusol.thelearnerscommunity.NEP_Files;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.ManageFragment;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_AEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_GENERIC_ELECTIVE;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_Paper1_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_Paper2_DSC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_SEC;
import com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments.Sem6.Sem6_VAC;
import com.dusol.thelearnerscommunity.R;

public class NEP_Sem6_MainActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        // Close the activity immediately without managing back stack
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nep_sem6_main);

        Button paper1=findViewById(R.id.paper1DSC);
        Button paper2=findViewById(R.id.paper2DSC);
        Button aec=findViewById(R.id.aec);
        Button ge=findViewById(R.id.GenericElective);
        Button sec=findViewById(R.id.sec);
        Button vac=findViewById(R.id.vac);


        FragmentActivity context=this;

        paper1.setOnClickListener(v ->ManageFragment.setButtonToLoadFragment(context,new Sem6_Paper1_DSC(),R.id.NEPsem6Fragment));
        
        paper2.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_Paper2_DSC(),R.id.NEPsem6Fragment));

        aec.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_AEC(),R.id.NEPsem6Fragment));

        sec.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_SEC(),R.id.NEPsem6Fragment));

        ge.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_GENERIC_ELECTIVE(),R.id.NEPsem6Fragment));

        vac.setOnClickListener(v -> ManageFragment.setButtonToLoadFragment(context,new Sem6_VAC(),R.id.NEPsem6Fragment));


    }
}