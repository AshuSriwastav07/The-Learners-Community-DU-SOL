package com.dusol.thelearnerscommunity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Utility class to setup the bottom navigation bar consistently across all activities.
 * Call BottomNavHelper.setup(this, bottomNav, R.id.nav_home) from any Activity's onCreate.
 */
public class BottomNavHelper {

    /**
     * Setup the BottomNavigationView with navigation logic.
     *
     * @param activity       The current Activity
     * @param bottomNav      The BottomNavigationView reference
     * @param selectedItemId The menu item ID that corresponds to the current page (to highlight it)
     */
    public static void setup(Activity activity, BottomNavigationView bottomNav, int selectedItemId) {
        if (bottomNav == null) return;

        // Disable default tint so original custom icons show properly
        bottomNav.setItemIconTintList(null);

        // Highlight the current page's tab
        bottomNav.setSelectedItemId(selectedItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            // Don't re-open the current page
            if (id == selectedItemId) return true;

            Context context = activity.getApplicationContext();

            if (id == R.id.nav_home) {
                activity.startActivity(new Intent(context, LinkPage_MainActivity.class));
            } else if (id == R.id.nav_videos) {
                activity.startActivity(new Intent(context, YouTubeVideosActivity.class));
            } else if (id == R.id.nav_notes) {
                activity.startActivity(new Intent(context, DU_SOL_NOTES__MainActivity.class));
            } else if (id == R.id.nav_student) {
                activity.startActivity(new Intent(context, studentsBoard.class));
            } else {
                return false;
            }

            return true;
        });
    }
}
