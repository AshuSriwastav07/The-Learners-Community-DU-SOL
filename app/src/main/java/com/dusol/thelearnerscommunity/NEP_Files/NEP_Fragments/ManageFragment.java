package com.dusol.thelearnerscommunity.NEP_Files.NEP_Fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ManageFragment {
    public static void setButtonToLoadFragment(FragmentActivity activity, Fragment fragment, int fc) { //get fragment activity, fragment that show, and fc= container that show fragment in it.

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(fc, fragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // Name can be null
                .commit();
    }
}
