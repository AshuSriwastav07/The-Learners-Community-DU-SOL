/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import static com.dusol.thelearnerscommunity.R.drawable.notification_icon;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        getFirebaseMessage(message.getNotification().getTitle(), message.getNotification().getBody());  //remote message don't work

    }
    public void getFirebaseMessage(String title, String message) {

        // Create a notification using NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myNotificationChannel")
                .setSmallIcon(notification_icon) // Set the small notification icon
                .setContentTitle(title) // Set the notification title
                .setContentText(message) // Set the notification message
                .setAutoCancel(false); // Set whether the notification should be automatically canceled when clicked

        // Get the NotificationManagerCompat instance
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        // Check if the app has the POST_NOTIFICATIONS permission
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // If permission is not granted, consider requesting it
            // TODO: Consider calling ActivityCompat#requestPermissions here to request the missing permissions,
            // and then override onRequestPermissionsResult to handle user permission response.
            // See the documentation for ActivityCompat#requestPermissions for more details.
            return; // Return if permission is not granted
        }

        // Notify with the built notification (notification ID: 101)
        manager.notify(101, builder.build());
    }

}


