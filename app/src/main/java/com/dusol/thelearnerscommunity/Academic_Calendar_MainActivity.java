package com.dusol.thelearnerscommunity;

// Required imports for app functionality

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector; // Used to detect pinch zoom
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;         // For receiving data from Firebase
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;    // Reference to a node in Firebase
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;                      // Used to load image from URL

// This class is the main screen that shows the academic calendar image
public class Academic_Calendar_MainActivity extends AppCompatActivity {

    private ScaleGestureDetector scaleGestureDetector; // Detects zoom gesture (pinch)
    private float mScaleFactor = 1.0f;                 // Keeps track of zoom level (1.0 = normal)
    private ImageView imageView;                       // To show the academic calendar image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar_main); // Set layout from XML

        imageView = findViewById(R.id.imageView); // Link Java imageView with the one in XML

        // Get Firebase database instance
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Pointing to a specific location in the database (AcademicCalendarLink)
        DatabaseReference ref = database.getReference("AcademicCalendarLink");

        // Add listener to get image URL from Firebase in real-time
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get the link of the image from the database
                String Link = snapshot.getValue(String.class);

                // Load image using Picasso library
                // Show loading image while it's loading
                // Show error image if loading fails
                Picasso.get()
                        .load(Link)
                        .error(R.drawable.nopictures)
                        .placeholder(R.drawable.loading)
                        .into(imageView); // Put image into imageView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Do nothing if loading fails (you can show a message if needed)
            }
        });

        // Initialize zoom gesture detector
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    // This method receives all touch screen actions (like swipe, pinch etc.)
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // Pass the touch to zoom detector
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true; // Return true to say event was handled
    }

    // This inner class handles the zooming logic
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            // Multiply current zoom level by new pinch gesture scale
            mScaleFactor *= scaleGestureDetector.getScaleFactor();

            // Restrict zoom between 0.1x and 10x
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

            // Apply zoom to the image
            imageView.setScaleX(mScaleFactor); // Zoom in/out horizontally
            imageView.setScaleY(mScaleFactor); // Zoom in/out vertically

            return true; // Gesture handled
        }
    }
}
