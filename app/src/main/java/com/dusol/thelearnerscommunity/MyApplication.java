package com.dusol.thelearnerscommunity;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        // FORCE LIGHT MODE — must be first line, before super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate();
        // Enable Firebase offline persistence for the entire app
        // This makes Firebase cache its last-fetched data locally.
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
