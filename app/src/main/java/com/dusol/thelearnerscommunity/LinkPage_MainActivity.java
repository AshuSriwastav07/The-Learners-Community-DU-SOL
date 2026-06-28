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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class LinkPage_MainActivity extends BaseActivity {
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

        // Setup bottom navigation bar
        com.google.android.material.bottomnavigation.BottomNavigationView bottomNav = findViewById(R.id.bottomNavBar);
        BottomNavHelper.setup(this, bottomNav, R.id.nav_home);


        // 1. First: update local semester buttons — instant, no network needed
        updateUpcomingExamsSection(
                UpComingExamsMainCard,
                semester12, semester34, semester56, semester78,
                semester12TV, semester34TV, semester56TV, semester78TV
        );

        // Fetch Latest 10 YouTube Videos Dynamically
        setupHomeYouTubeVideos();

        // 2. Second: start Firebase fetch for images — async, won't block UI
        loadExamNotesImages(
                HomeNotesIV1, HomeNotesIV2, HomeNotesIV3, HomeNotesIV4, HomeNotesIV5,
                HomeNotesIV6, HomeNotesIV7, HomeNotesIV8, HomeNotesIV9, HomeNotesIV10
        );


        TextView bannerTextView = findViewById(R.id.linkPageMarquee);
        marqueeTextViewBanner(bannerTextView);

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

        watch_videos.setOnClickListener(view -> openYouTubeChannel());

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

        fetchConnectUsButtonData(buttonName, Connect_with_us,reviewAnimationButton);

        findViewById(R.id.YTImageView).setOnClickListener(v ->
                openExternalApp("https://www.youtube.com/@TheLearnersCommunityDUSOL/", "com.google.android.youtube"));

        findViewById(R.id.TelegramImageView).setOnClickListener(v ->
                openExternalApp("https://t.me/The_LCTyoutube", "org.telegram.messenger"));

        findViewById(R.id.InstaImageView).setOnClickListener(v ->
                openExternalApp("https://www.instagram.com/collegestudybydhruv/", "com.instagram.android"));

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

    // Helper method to open YouTube videos page (to avoid code repetition)
    private void openYouTubeChannel() {

        new android.os.Handler().postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("YoutubeOpen", "YoutubeOpen");
            FirebaseAnalytics.getInstance(this).logEvent("YoutubeOpen", bundle);
            }, 500);

        startActivity(new Intent(getApplicationContext(), YouTubeVideosActivity.class));
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

    private void setupHomeYouTubeVideos() {
        RecyclerView homeYouTubeRecyclerView = findViewById(R.id.homeYouTubeRecyclerView);
        android.widget.ProgressBar homeYouTubeProgressBar = findViewById(R.id.homeYouTubeProgressBar);

        homeYouTubeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        YouTubeApiHelper.fetchVideos(LinkPage_MainActivity.this, 30, new YouTubeApiHelper.YouTubeVideoCallback() {
            @Override
            public void onSuccess(java.util.List<YouTubeVideoItem> videos) {
                if (isFinishing() || isDestroyed()) return;
                homeYouTubeProgressBar.setVisibility(android.view.View.GONE);
                
                YouTubeVideoAdapter adapter = new YouTubeVideoAdapter(LinkPage_MainActivity.this, videos, R.layout.item_youtube_video_horizontal);
                homeYouTubeRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                if (isFinishing() || isDestroyed()) return;
                homeYouTubeProgressBar.setVisibility(android.view.View.GONE);
                // Silently fail if videos cannot load (e.g. API quota exceeded), 
                // avoiding annoying toasts since cached videos might already be showing.
            }
        });
    }

    private void updateUpcomingExamsSection(
            CardView MainCard,
            CardView semester12, CardView semester34,
            CardView semester56, CardView semester78,
            TextView semester12TV, TextView semester34TV,
            TextView semester56TV, TextView semester78TV) {

        MainCard.setVisibility(View.VISIBLE);

        String activeSemKey = com.dusol.thelearnerscommunity.utils.ExamSeasonManager.getActiveSemesterKey();
        boolean isSem2468Active = activeSemKey.equals(com.dusol.thelearnerscommunity.utils.ExamSeasonManager.KEY_SEM_2468);

        TextView tvUpcomingExamsTitle = findViewById(R.id.textView18); // ID for "Upcoming Exams Notes" text based on values
        if (tvUpcomingExamsTitle != null) {
            tvUpcomingExamsTitle.setText("Semester " + com.dusol.thelearnerscommunity.utils.ExamSeasonManager.getActiveSemesterLabel() + " Notes");
        }

        semester12.setVisibility(View.VISIBLE);
        semester34.setVisibility(View.VISIBLE);
        semester56.setVisibility(View.VISIBLE);
        semester78.setVisibility(View.VISIBLE);

        if (isSem2468Active) {
            setupSemesterButton(semester12, semester12TV, 2, com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem2_MainActivity.class);
            setupSemesterButton(semester34, semester34TV, 4, com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem4_MainActivity.class);
            setupSemesterButton(semester56, semester56TV, 6, com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem6_MainActivity.class);
            setupSemesterButton(semester78, semester78TV, 8, com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem8_MainActivity.class);
        } else {
            setupSemesterButton(semester12, semester12TV, 1, com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem1_MainActivity.class);
            setupSemesterButton(semester34, semester34TV, 3, com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem3_MainActivity.class);
            setupSemesterButton(semester56, semester56TV, 5, com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem5_MainActivity.class);
            setupSemesterButton(semester78, semester78TV, 7, com.dusol.thelearnerscommunity.NEP_Files.NEP_Sem7_MainActivity.class);
        }
    }

    private void setupSemesterButton(CardView cardView, TextView textView, int semNum, Class<?> activityClass) {
        textView.setText("Semester " + semNum);
        cardView.setOnClickListener(v -> {
            Intent i = new Intent(this, activityClass);
            i.putExtra("semester", String.valueOf(semNum));
            startActivity(i);
        });
    }

    private void loadExamNotesImages(ImageView... noteImages) {
        String semKey = com.dusol.thelearnerscommunity.utils.ExamSeasonManager.getActiveSemesterKey();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("ExamsImages")
                .child(semKey);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int index = 0;
                for (DataSnapshot itemSnap : snapshot.getChildren()) {
                    if (index >= noteImages.length) break;

                    String imageUrl = itemSnap.child("0").getValue(String.class);
                    if (imageUrl == null) imageUrl = itemSnap.child("imageUrl").getValue(String.class);

                    String pageLink = itemSnap.child("1").getValue(String.class);
                    if (pageLink == null) pageLink = itemSnap.child("pageLink").getValue(String.class);

                    // Skip empty or invalid entries (like a null '0' index in a Firebase array)
                    if (imageUrl == null || imageUrl.trim().isEmpty()) {
                        continue;
                    }

                    noteImages[index].setVisibility(android.view.View.VISIBLE);
                    loadFeatureNotes(noteImages[index], imageUrl, pageLink == null ? "" : pageLink);
                    index++;
                }

                // Hide any remaining ImageViews
                for (int i = index; i < noteImages.length; i++) {
                    noteImages[i].setVisibility(android.view.View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ExamNotes", "Failed to load exam notes: " + error.getMessage());
                for (ImageView img : noteImages) {
                    img.setVisibility(View.GONE);
                }
            }
        });
    }

    private static void loadFeatureNotes(ImageView imageView, String imageUrl, String buyUrl) {
        if (imageView == null) return;

        // No data for this slot → clear image & disable click
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            imageView.setImageDrawable(null);
            imageView.setOnClickListener(null);
            imageView.setClickable(false);
            // Optional if you want to hide empty slots completely:
            // imageView.setVisibility(View.GONE);
            return;
        }

        // Load image safely using the new CloudinaryImageLoader (optimized sizing and caching)
        CloudinaryImageLoader.load(imageView.getContext(), imageUrl, imageView,
                CloudinaryImageLoader.SIZE_MEDIUM, R.drawable.loading);

        // Handle click only if buyUrl is valid
        if (buyUrl == null || buyUrl.trim().isEmpty()) {
            imageView.setOnClickListener(null);
            imageView.setClickable(false);
        } else {
            final String safeBuyUrl = buyUrl.trim();
            imageView.setClickable(true);
            imageView.setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(safeBuyUrl));
                    v.getContext().startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Unable to open link", Toast.LENGTH_SHORT).show();
                }
            });
        }
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

    // Marquee TextView CallFunction (SAFE VERSION)
    private void marqueeTextViewBanner(TextView textView) {
        DatabaseReference marqueeTextRef =
                FirebaseDatabase.getInstance().getReference("MainPageBanner");

        // All initial UI setup MUST be on the main thread
        runOnUiThread(() -> {
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setMarqueeRepeatLimit(-1);
        });

        marqueeTextRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String textToShow = snapshot.getValue(String.class);

                runOnUiThread(() -> {
                    if (textToShow == null || textToShow.trim().equalsIgnoreCase("N/A")) {
                        textView.setVisibility(View.GONE);
                    } else {
                        String cleaned = textToShow.trim();
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(cleaned);
                        textView.setSelected(true); // needed for marquee
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Optional: log error
                // Log.e("Marquee", "Failed to load banner: " + error.getMessage());
            }
        });
    }




    private void AnalyticsDataPushWithActivity(String key, String value, String name, Class<? extends BaseActivity> activityClass, AppCompatActivity activity) {
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
