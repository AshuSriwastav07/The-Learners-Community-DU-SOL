package com.dusol.thelearnerscommunity;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Firebase offline persistence for the entire app
        // This makes Firebase cache its last-fetched data locally.
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
