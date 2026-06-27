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

            int oldIndex = getNavIndex(selectedItemId);
            int newIndex = getNavIndex(id);

            Context context = activity.getApplicationContext();

            Intent intent = null;
            if (id == R.id.nav_home) {
                intent = new Intent(context, LinkPage_MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            } else if (id == R.id.nav_videos) {
                intent = new Intent(context, YouTubeVideosActivity.class);
            } else if (id == R.id.nav_notes) {
                intent = new Intent(context, DU_SOL_NOTES__MainActivity.class);
            } else if (id == R.id.nav_student) {
                intent = new Intent(context, studentsBoard.class);
            } else {
                return false;
            }

            activity.startActivity(intent);

            if (newIndex > oldIndex) {
                // Moving right: new screen comes from right, old screen goes to left
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                // Moving left: new screen comes from left, old screen goes to right
                activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

            // Finish current activity so backstack doesn't get messed up if we're just switching tabs
            if (!(activity instanceof LinkPage_MainActivity)) {
                activity.finish();
            }

            return true;
        });
    }

    private static int getNavIndex(int id) {
        if (id == R.id.nav_home) return 0;
        if (id == R.id.nav_videos) return 1;
        if (id == R.id.nav_notes) return 2;
        if (id == R.id.nav_student) return 3;
        return -1;
    }
}
