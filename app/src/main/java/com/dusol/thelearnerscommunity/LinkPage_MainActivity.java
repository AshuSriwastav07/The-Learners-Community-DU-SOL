package com.dusol.thelearnerscommunity;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.dusol.thelearnerscommunity.FunctionManager.functionManager;
import com.dusol.thelearnerscommunity.NotesStoreManage.NotesStoreTabActivity;
import com.dusol.thelearnerscommunity.SyllabusFiles.SyllabusTabLayoutActivity;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class LinkPage_MainActivity extends AppCompatActivity {
    private long Timeback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_page_activity_main);

        // Set up modern back press handling
        setupBackPressHandler();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        // Check and request notification permission every time the app starts
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            checkAndRequestNotificationPermission();
        }


        // Initialize UI elements
        CardView du_and_sol_notes_page = findViewById(R.id.button1_du_sol_notes_page);
        CardView solUpdates = findViewById(R.id.button2_SOL_Updates);
        CardView QuestionPapers = findViewById(R.id.button3_QP);
        CardView SOL_Syllabus = findViewById(R.id.button4_SOL_Syllabus);
        CardView sol_portal = findViewById(R.id.button5_Portal3);
        CardView shop = findViewById(R.id.button6_notes_store);
        ImageButton watch_videos = findViewById(R.id.button7_Videos);
        ImageButton Connect_with_us = findViewById(R.id.button9_connect_us);
        CardView sol_materials = findViewById(R.id.button10_SOL_Study_Material);
        ImageButton askDoubt = findViewById(R.id.askHere);

        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);

        CardView YTVideo1CV=findViewById(R.id.YTVideosCV1);
        CardView YTVideo2CV=findViewById(R.id.YTVideosCV2);
        CardView YTVideo3CV=findViewById(R.id.YTVideosCV3);
        CardView YTVideo4CV=findViewById(R.id.YTVideosCV4);
        CardView YTVideo5CV=findViewById(R.id.YTVideosCV5);
        CardView YTVideo6CV=findViewById(R.id.YTVideosCV6);
        CardView YTVideo7CV=findViewById(R.id.YTVideosCV7);
        CardView YTVideo8CV=findViewById(R.id.YTVideosCV8);
        CardView YTVideo9CV=findViewById(R.id.YTVideosCV9);
        CardView YTVideo10CV=findViewById(R.id.YTVideosCV10);

        ImageView YTVideo1IV=findViewById(R.id.YTVideo1ImageView);
        ImageView YTVideo2IV=findViewById(R.id.YTVideo2ImageView);
        ImageView YTVideo3IV=findViewById(R.id.YTVideo3ImageView);
        ImageView YTVideo4IV=findViewById(R.id.YTVideo4ImageView);
        ImageView YTVideo5IV=findViewById(R.id.YTVideo5ImageView);
        ImageView YTVideo6IV=findViewById(R.id.YTVideo6ImageView);
        ImageView YTVideo7IV=findViewById(R.id.YTVideo7ImageView);
        ImageView YTVideo8IV=findViewById(R.id.YTVideo8ImageView);
        ImageView YTVideo9IV=findViewById(R.id.YTVideo9ImageView);
        ImageView YTVideo10IV=findViewById(R.id.YTVideo10ImageView);

        ImageView videoIcon=findViewById(R.id.VideoGifIconImageView);


        //Upcoming Exams
        CardView UpComingExamsMainCard=findViewById(R.id.UpcomingExamsNotesMainCard);

        CardView semester12=findViewById(R.id.semester12);
        CardView semester34=findViewById(R.id.semester34);
        CardView semester56=findViewById(R.id.semester56);
        CardView semester78=findViewById(R.id.semester78);

        TextView semester12TV=findViewById(R.id.semester12TV);
        TextView semester34TV=findViewById(R.id.semester34TV);
        TextView semester56TV=findViewById(R.id.semester56TV);
        TextView semester78TV=findViewById(R.id.semester78TV);

        ImageView notificationIcon= findViewById(R.id.updateIcon);
        ImageButton reviewAnimationButton= findViewById(R.id.reviewUSAnimation);
        ImageView saleAnimation = findViewById(R.id.saleanimation);

        //Notes Images
        ImageView HomeNotesIV1=findViewById(R.id.HomeNotes1);
        ImageView HomeNotesIV2=findViewById(R.id.HomeNotes2);
        ImageView HomeNotesIV3=findViewById(R.id.HomeNotes3);
        ImageView HomeNotesIV4=findViewById(R.id.HomeNotes4);
        ImageView HomeNotesIV5=findViewById(R.id.HomeNotes5);
        ImageView HomeNotesIV6=findViewById(R.id.HomeNotes6);
        ImageView HomeNotesIV7=findViewById(R.id.HomeNotes7);
        ImageView HomeNotesIV8=findViewById(R.id.HomeNotes8);
        ImageView HomeNotesIV9=findViewById(R.id.HomeNotes9);
        ImageView HomeNotesIV10=findViewById(R.id.HomeNotes10);


//        set Video Icon as GIf
        setGif(videoIcon, R.drawable.video);
        setGif(notificationIcon, R.drawable.anounc);
        setGif(reviewAnimationButton, R.drawable.reviewani);
        setGif(findViewById(R.id.InstaImageView),R.drawable.instaanimate);
        setGif(findViewById(R.id.TelegramImageView),R.drawable.animatetelegram);
        setGif(findViewById(R.id.YTImageView),R.drawable.animateyt);
        setGif(findViewById(R.id.ShareNow),R.drawable.animateshare);
        setGif(findViewById(R.id.saleanimation),R.drawable.sale);
        setGif(findViewById(R.id.button9_connect_us),R.drawable.connect);

        setGif(findViewById(R.id.HomeNotes1),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes2),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes3),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes4),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes5),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes6),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes7),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes8),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes9),R.drawable.loading);
        setGif(findViewById(R.id.HomeNotes10),R.drawable.loading);


//        show sale is on or Not
        functionManager.isSaleOn(saleAnimation);

        functionManager.managerNewSignLogo(this, this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }


        //Set Review ans connect us button name
        final String[] buttonName = new String[1];
        checkInAppUpdate();

        // Navigation Bar Button Listeners (unchanged)
        new Thread(()-> NavVideos.setOnClickListener(view -> openYouTubeChannel())).start();

        NavBooks.setOnClickListener(view -> {
            new Handler().postDelayed(() -> {
                Bundle bundle = new Bundle();
                bundle.putString("SOL_Notes_Open", "button_clicked");
                FirebaseAnalytics.getInstance(this).logEvent("SOL_Notes_Open", bundle);
            }, 500);
            startActivity(new Intent(getApplicationContext(), DU_SOL_NOTES__MainActivity.class));
        });
        NavStudents.setOnClickListener(view -> {
            new Handler().postDelayed(() -> {
                Bundle bundle = new Bundle();
                bundle.putString("SOL_Portal", "button_clicked");
                FirebaseAnalytics.getInstance(this).logEvent("SOL_Portal", bundle);
            }, 500);

            startActivity(new Intent(getApplicationContext(), studentsBoard.class));
        });


        //upcoming exams
        functionManager.upComingExams(this, UpComingExamsMainCard,semester12,semester34,semester56,semester78,semester12TV,semester34TV,semester56TV,semester78TV);

        //Feature Videos Function Call

        new Thread(() -> fetchFeatureVideosData(YTVideo1CV,YTVideo2CV,YTVideo3CV,YTVideo4CV,YTVideo5CV,YTVideo6CV,YTVideo7CV,YTVideo8CV,YTVideo9CV,YTVideo10CV,YTVideo1IV,YTVideo2IV,YTVideo3IV,YTVideo4IV,YTVideo5IV,YTVideo6IV,YTVideo7IV,YTVideo8IV,YTVideo9IV,YTVideo10IV)).start();


        //feature Notes Call

        new Thread(()-> FeaturedNotes(HomeNotesIV1,HomeNotesIV2,HomeNotesIV3,HomeNotesIV4,HomeNotesIV5,HomeNotesIV6,HomeNotesIV7,HomeNotesIV8,HomeNotesIV9,HomeNotesIV10)).start();


        // Text Marquee (unchanged)
        TextView textView = findViewById(R.id.linkPageMarquee);
        new Thread(()-> marqueeTextViewBanner(textView)).start();


        // Main Buttons Listeners (unchanged)
        shop.setOnClickListener(view -> {
            AnalyticsDataPushWithActivity("Notes_Store", "Notes_Store_Opens", "Notes_Store", NotesStoreTabActivity.class,this);
        });

        du_and_sol_notes_page.setOnClickListener(view -> {

            AnalyticsDataPushWithActivity("Notes_Open", "NotesActivityOpens", "Notes_Open", DU_SOL_NOTES__MainActivity.class,this);

        });

        QuestionPapers.setOnClickListener(view -> {
            AnalyticsDataPushWithActivity("Question_Paper_Open", "Question_Paper_Open", "Question_Paper_Open", selectCourseForQP.class,this);

        });

        solUpdates.setOnClickListener(v -> {

            AnalyticsDataPushWithLink(this);

        });

        SOL_Syllabus.setOnClickListener(view -> {

            AnalyticsDataPushWithActivity("SOL_Syllabus", "SOL_Syllabus_Opens", "SOL_Syllabus", SyllabusTabLayoutActivity.class,this);

        });

        new Thread(()-> watch_videos.setOnClickListener(view -> openYouTubeChannel())).start();


        sol_portal.setOnClickListener(v -> {

            AnalyticsDataPushWithActivity("StudentPortal", "SOL_Portal_Opens", "StudentPortal", studentsBoard.class,this);

        });

        sol_materials.setOnClickListener(v -> {

            AnalyticsDataPushWithActivity("SOL_Materials", "SOL_Materials", "SOL_Materials", study_materials.class,this);

        });

        Connect_with_us.setOnClickListener(view -> {
                // buttonName[0] is "N/A" or null-safe
                Bundle bundle = new Bundle();
                bundle.putString("Join_US", "JoinUS_Opens");
                FirebaseAnalytics.getInstance(this).logEvent("Join_US", bundle);
                startActivity(new Intent(getApplicationContext(), connect_with_us_MainActivity.class));
        });

        reviewAnimationButton.setOnClickListener(view -> {
            reviewUsPageOpen();
        });



        askDoubt.setOnClickListener(view -> functionManager.askDoubtHere(LinkPage_MainActivity.this));

        // Firebase FCM (unchanged)
        FirebaseMessaging.getInstance().subscribeToTopic("All_Notification")
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Subscription to topic failed");
                    }
                });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "myNotificationChannel",
                    "General Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Channel for general app notifications");
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    String token = task.getResult();
//                    Log.d("FirebaseRegToken", token);
                });

        new Thread(() -> fetchConnectUsButtonData(buttonName, Connect_with_us,reviewAnimationButton)).start(); //New Thread for better performance

        findViewById(R.id.YTImageView).setOnClickListener(v ->
                openExternalApp("https://www.youtube.com/@TheLearnersCommunityDUSOL/", "com.google.android.youtube"));

        findViewById(R.id.TelegramImageView).setOnClickListener(v ->
                openExternalApp("https://t.me/The_LCTyoutube", "org.telegram.messenger"));

        findViewById(R.id.InstaImageView).setOnClickListener(v ->
                openExternalApp("https://www.instagram.com/the_learners_community_dusol/", "com.instagram.android"));

        initShareButton();


        //Check Internet Is Working or Not
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))) {
                    Log.d("FirebaseCheck", "Connected to internet");
                } else {
                    Log.e("FirebaseCheck", "No internet connection");
                }
            } else {
                Log.e("FirebaseCheck", "No active network");
            }
        }

        Log.d("FirebaseCheck", "Attempting to fetch data...");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AcademicCalendarLink");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("FirebaseCheck", "Data fetched successfully.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseCheck", "Error: " + error.getMessage());
            }
        });

    }

    private void setupBackPressHandler() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (System.currentTimeMillis() - Timeback > 2000) {
                    Timeback = System.currentTimeMillis();
                    Toast.makeText(LinkPage_MainActivity.this, "Press again to exit", Toast.LENGTH_SHORT).show();
                } else {
                    finishAffinity();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    // Combined method to check and request notification permission
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Permission not granted, request it every time
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {
                        Log.d("Notification", "Permission granted for notifications.");
                        Toast.makeText(this, "Notifications are enabled!", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Notification", "Permission denied for notifications.");
                        Toast.makeText(this, "Notifications are disabled. You can enable them in settings.", Toast.LENGTH_SHORT).show();
                    }
                }).launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                Log.d("Notification", "Permission already granted.");
                Toast.makeText(this, "Notifications are already enabled!", Toast.LENGTH_SHORT).show();
            }
        } else { // Pre-Android 13
            if (!androidx.core.app.NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                Toast.makeText(this, "Please enable notifications in settings.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, getPackageName());
                startActivity(intent);
            }
        }
    }

    // Helper method to open YouTube channel (to avoid code repetition)
    private void openYouTubeChannel() {

        String youtubeChannelUrl = "https://www.youtube.com/@TheLearnersCommunityDUSOL";
        Uri youtubeUri = Uri.parse(youtubeChannelUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);
        intent.setPackage("com.google.android.youtube");

        new android.os.Handler().postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("YoutubeOpen", "YoutubeOpen");
            FirebaseAnalytics.getInstance(this).logEvent("YoutubeOpen", bundle);
            }, 500);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeChannelUrl)));
        }
    }

    private void reviewUsPageOpen() {
        String playStorePage = "https://play.google.com/store/apps/details?id=com.dusol.thelearnerscommunity";
        Uri playStoreUri = Uri.parse(playStorePage);

        // Create intent to open Play Store
        Intent intent = new Intent(Intent.ACTION_VIEW, playStoreUri);
        intent.setPackage("com.android.vending"); // Correct Play Store package name

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Push analytics event
            Bundle bundle = new Bundle();
            bundle.putString("Review_US", "Review_US_Opens");
            FirebaseAnalytics.getInstance(this).logEvent("Review_US", bundle);

            // Start Play Store or fallback to browser
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, playStoreUri));
            }
        }, 500);
    }

    private void fetchConnectUsButtonData(String[] buttonName, ImageButton Connect_with_us, ImageButton reviewAnimationButton) {

        DatabaseReference reviewData = FirebaseDatabase.getInstance().getReference("ReviewUSNow");
        reviewData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                buttonName[0] = snapshot.getValue(String.class);
                assert buttonName[0] != null;
                runOnUiThread(() -> {
                    if (!buttonName[0].equals("N/A")) {
                        Connect_with_us.setVisibility(View.GONE);
                        reviewAnimationButton.setVisibility(View.VISIBLE);

                    } else {
                        Connect_with_us.setVisibility(View.VISIBLE);
                        reviewAnimationButton.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("firebaseError",error.getMessage());

            }
        });
    }

    private void fetchFeatureVideosData(CardView cardView1, CardView cardView2, CardView cardView3, CardView cardView4, CardView cardView5,CardView cardView6, CardView cardView7, CardView cardView8, CardView cardView9, CardView cardView10,ImageView imageView1, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5,ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10) {

        DatabaseReference YTFeatureVideosData = FirebaseDatabase.getInstance().getReference("YTFeature");

        YTFeatureVideosData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int index = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    List<String> value = new ArrayList<>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        value.add(String.valueOf(child.getValue()));
                    }

                    if (value.size() >= 2) {
                        String imageUrl = value.get(0);  // Thumbnail URL
                        String videoUrl = value.get(1);  // YouTube video link

//                        Log.d("YTVideo", imageUrl + " : " + videoUrl);

                        switch (index) {
                            case 0:
                                loadFeature(imageView1, cardView1, imageUrl, videoUrl);
                                break;
                            case 1:
                                loadFeature(imageView2, cardView2, imageUrl, videoUrl);
                                break;
                            case 2:
                                loadFeature(imageView3, cardView3, imageUrl, videoUrl);
                                break;
                            case 3:
                                loadFeature(imageView4, cardView4, imageUrl, videoUrl);
                                break;
                            case 4:
                                loadFeature(imageView5, cardView5, imageUrl, videoUrl);
                                break;
                            case 5:
                                loadFeature(imageView6, cardView6, imageUrl, videoUrl);
                                break;
                            case 6:
                                loadFeature(imageView7, cardView7, imageUrl, videoUrl);
                                break;
                            case 7:
                                loadFeature(imageView8, cardView8, imageUrl, videoUrl);
                                break;
                            case 8:
                                loadFeature(imageView9, cardView9, imageUrl, videoUrl);
                                break;
                            case 9:
                                loadFeature(imageView10, cardView10, imageUrl, videoUrl);
                                break;
                        }

                        index++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("YTFeature", "Database error: " + error.getMessage());
            }
        });
    }

    private void loadFeature(ImageView imageView, CardView cardView, String imageUrl, String videoUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
            imageView.getContext().startActivity(intent);
        });
    }


    //Show Notes on Home Page

    public static void FeaturedNotes(ImageView NotesImage1, ImageView NotesImage2, ImageView NotesImage3, ImageView NotesImage4, ImageView NotesImage5, ImageView NotesImage6,ImageView NotesImage7,ImageView NotesImage8,ImageView NotesImage9,ImageView NotesImage10) {

        DatabaseReference FeatureNotesData = FirebaseDatabase.getInstance().getReference("PaidNewAndMostSellNotes");

        FeatureNotesData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int index = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    List<String> value = new ArrayList<>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        value.add(String.valueOf(child.getValue()));
                    }

                    if (value.size() >= 2) {
                        String NotesImageUrl = value.get(0);  // Notes Image Link
                        String NotesBuyLink = value.get(1);  // Notes link

//                        Log.d("NotesProductLink", NotesImageUrl + " : " + NotesBuyLink);

                        switch (index) {
                            case 0:
                                loadFeatureNotes(NotesImage1, NotesImageUrl, NotesBuyLink);
                                break;
                            case 1:
                                loadFeatureNotes(NotesImage2,  NotesImageUrl, NotesBuyLink);
                                break;
                            case 2:
                                loadFeatureNotes(NotesImage3,  NotesImageUrl, NotesBuyLink);
                                break;
                            case 3:
                                loadFeatureNotes(NotesImage4,  NotesImageUrl, NotesBuyLink);
                                break;
                            case 4:
                                loadFeatureNotes(NotesImage5,  NotesImageUrl, NotesBuyLink);
                                break;
                            case 5:
                                loadFeatureNotes(NotesImage6,  NotesImageUrl, NotesBuyLink);
                                break;
                            case 6:
                                loadFeatureNotes(NotesImage7,  NotesImageUrl, NotesBuyLink);
                                break;
                            case 7:
                                loadFeatureNotes(NotesImage8,  NotesImageUrl, NotesBuyLink);
                                break;
                            case 8:
                                loadFeatureNotes(NotesImage9,  NotesImageUrl, NotesBuyLink);
                                break;
                            case 9:
                                loadFeatureNotes(NotesImage10, NotesImageUrl, NotesBuyLink);
                                break;
                        }

                        index++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private static void loadFeatureNotes(ImageView imageView, String imageUrl, String buyUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).error(R.drawable.loading).into(imageView);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(buyUrl));
            imageView.getContext().startActivity(intent);
        });
    }


    private void checkInAppUpdate() {
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());

// Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // This example applies an immediate update. To apply a flexible update
                    // instead, pass in AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.

                appUpdateManager.startUpdateFlowForResult(
                        // Pass the intent that is returned by 'getAppUpdateInfo()'.
                        appUpdateInfo,
                        // an activity result launcher registered via registerForActivityResult
                        registerForActivityResult(
                                new ActivityResultContracts.StartIntentSenderForResult(),
                                result -> {
                                    // handle callback
                                    if (result.getResultCode() != RESULT_OK) {
                                        Log.d("LauncherCheck","Update flow failed! Result code: " + result.getResultCode());
                                        // If the update is canceled or fails,
                                        // you can request to start the update again.
                                    }
                                }),
                        // Or pass 'AppUpdateType.FLEXIBLE' to newBuilder() for
                        // flexible updates.
                        AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build());

            }

        });


    }

    //Marquee TextView CallFunction
    private void marqueeTextViewBanner(TextView textView) {
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        DatabaseReference MarqueeText = FirebaseDatabase.getInstance().getReference("MainPageBanner");
        MarqueeText.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String TextToShow = snapshot.getValue(String.class);
                assert TextToShow != null;
                if (TextToShow.equals("N/A")) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setText(TextToShow);
                    textView.setSelected(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    private void AnalyticsDataPushWithActivity(String key, String value, String name, Class<? extends AppCompatActivity> activityClass, AppCompatActivity activity) {
        new android.os.Handler().postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putString(key, value);
            FirebaseAnalytics.getInstance(activity).logEvent(name, bundle);

            activity.startActivity(new Intent(activity.getApplicationContext(), activityClass));
        }, 500);
    }

    private void AnalyticsDataPushWithLink(AppCompatActivity activity) {
        new android.os.Handler().postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("SOL_Updates", "SOL_Updates_Opens");
            FirebaseAnalytics.getInstance(activity).logEvent("SOL_Updates", bundle);

            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.sol.du.ac.in/home")));
        }, 500);
    }


    //Open Social Media Accounts
    private void openExternalApp(String url, String packageName) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage(packageName);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }


    //Share App
    private static final String PLAY_STORE_URL =
            "https://play.google.com/store/apps/details?id=com.dusol.thelearnerscommunity";

    private static final String SHARE_TEXT = String.join("\n",
            "⚡️ Download the Best Study App for DU/SOL/NCWEB & Ace Your Exams! ⚡️",
            "✅ CBCS/NEP Course Notes",
            "💯 Semester 1‑6 Question Papers 📝",
            "📚 Semester 1‑6 Study Material",
            "📚 All Semesters Updated Syllabus",
            "💯 All College Updates",
            "🎥 Video‑explain Notes 🎥",
            "✨ Student Portal for Every Need ✨",
            "",
            "Check it out: " + PLAY_STORE_URL
    );

    private void initShareButton() {
        findViewById(R.id.ShareNow).setOnClickListener(v -> shareApp());
    }

    private void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, "Check this out!")
                .putExtra(Intent.EXTRA_TEXT, SHARE_TEXT);

        startActivity(Intent.createChooser(intent, "Share via"));
    }

    //Animate Buttons
    private void setGif(ImageView imageView, int gifResId) {
        Glide.with(this)
                .asGif()
                .load(gifResId)
                .error(gifResId)
                .placeholder(gifResId)
                .into(imageView);
    }

}
