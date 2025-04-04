package com.dusol.thelearnerscommunity;

import static com.dusol.thelearnerscommunity.R.drawable.notification_icon;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStore_HomePage;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        // Extract data from the notification
        String title = Objects.requireNonNull(message.getNotification()).getTitle();
        String body = message.getNotification().getBody();

        // Get custom data if needed
        String targetActivity = message.getData().get("targetActivity"); // Example: activity name passed in "targetActivity" key

        sendFirebaseNotification(title, body, targetActivity);
    }

    public void sendFirebaseNotification(String title, String message, String targetActivity) {
        // Create an Intent to open the specific activity
        Intent intent = null;

        if ("QP".equals(targetActivity)) {
            intent = new Intent(this, BA_QP_TabLayout_Activity.class);
            Log.d("FirebaseRegToken", "QPNotify");
        } else if ("paid_notes".equals(targetActivity)) {
            intent = new Intent(this, NotesStore_HomePage.class);
        } else if ("updates".equals(targetActivity)) {
            intent = new Intent(this, studentsBoard.class);
        }

        // Ensure that the activity opens correctly even if the app is closed
        if (intent != null) {
            // Use both flags to ensure the activity is opened correctly and clears any back stack
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Open in new task and clear stack
        }

        // Add data to the Intent if needed
        assert intent != null;
        intent.putExtra("notificationMessage", message);

        // Create the PendingIntent to launch the activity
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myNotificationChannel")
                .setSmallIcon(notification_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);  // Attach the PendingIntent

        // Display the notification
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        // Check for notification permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Handle missing permission if needed
            return;
        }

        // Send the notification
        manager.notify(101, builder.build());
    }


}
