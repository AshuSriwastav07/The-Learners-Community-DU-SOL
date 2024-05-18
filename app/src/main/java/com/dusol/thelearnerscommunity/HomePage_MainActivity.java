/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;


public class HomePage_MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 123; // Replace with your desired request code
    private ProgressBar progressBar;


    @Override
    public void onBackPressed() {
        // Call finish() to close the activity when the back button is pressed
        super.onBackPressed();
        finish();
    } //important function that clears the stack and sends you back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Prevent App from SS
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
        );

        setContentView(R.layout.home_page_activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent=new Intent(this, LinkPage_MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
            } },1000);

    }


}
