package com.dusol.thelearnerscommunity;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class ActivityUtils {
    public static int getNumberOfActivitiesInStack(Context context) {
        int count = 0;

        // Get the activity manager
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        // Get a list of running tasks (activities)
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(Integer.MAX_VALUE);

        // Iterate through the list and count activities
        for (ActivityManager.RunningTaskInfo info : taskInfo) {
            // Filter out the activities in your app by checking the package name
            assert info.baseActivity != null;
            if (context.getPackageName().equals(info.baseActivity.getPackageName())) {
                count++;
            }
        }

        return count;
    }
}
