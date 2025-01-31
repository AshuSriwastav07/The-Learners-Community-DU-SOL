/**
 * App developed by:
 * Ashu Sriwastav
 * <p>
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;


public class Notes_Store extends AppCompatActivity {

    private final Handler handler = new Handler();

    String[] productName =notesdata.productName;
    String[] productDetails =notesdata.productDetails;


    Double[] Price =notesdata.Price;


    String[] BuyLink =notesdata.BuyLink;

    String[] imgLinks =notesdata.imgLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_store);
        TextView textView=findViewById(R.id.StoreTitle);

        //Navigation Bar Button start here
        ImageButton NavHome = findViewById(R.id.navbarHome);
        ImageButton NavBooks = findViewById(R.id.navbarBooks);
        ImageButton NavStudents = findViewById(R.id.navbarStudent);
        ImageButton NavVideos = findViewById(R.id.navbarVideos);


        textView.setVisibility(View.GONE);

        //Videos // Left to make
        NavVideos.setOnClickListener(view -> {
            // Define the YouTube channel URL
            String youtubeChannelUrl = "https://www.youtube.com/@TheLearnersCommunityDUSOL/videos";

            // Create an Intent with the ACTION_VIEW action and the YouTube channel URL
            Uri youtubeUri = Uri.parse(youtubeChannelUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);

            // Set the package name of the YouTube app
            intent.setPackage("com.google.android.youtube");

            // Check if the YouTube app is installed
            if (intent.resolveActivity(getPackageManager()) != null) {
                // The YouTube app is installed, so open it
                startActivity(intent);
            } else {
                // The YouTube app is not installed, you can handle this case as needed
                // For example, you can open the YouTube website in a web browser
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeChannelUrl)));
            }
        });

        NavHome.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),LinkPage_MainActivity.class);
            startActivity(intent);

        });

        //Notes Books
        NavBooks.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),DU_SOL_NOTES__MainActivity.class);
            startActivity(intent);

        });

        //Students Portal
        NavStudents.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),studentsBoard.class);
            startActivity(intent);

        });

        //Nav Ends Here



        ImageView imageView1=findViewById(R.id.imageView1);
        ImageView imageView2=findViewById(R.id.imageView2);
        ImageView imageView3=findViewById(R.id.imageView3);
        ImageView imageView4=findViewById(R.id.imageView4);
        ImageView imageView5=findViewById(R.id.imageView5);
        ImageView imageView6=findViewById(R.id.imageView6);
        ImageView imageView7=findViewById(R.id.imageView7);
        ImageView imageView8=findViewById(R.id.imageView8);
        ImageView imageView9=findViewById(R.id.imageView9);
        ImageView imageView10=findViewById(R.id.imageView10);
        ImageView imageView11=findViewById(R.id.imageView11);
        ImageView imageView12=findViewById(R.id.imageView12);
        ImageView imageView13=findViewById(R.id.imageView13);
        ImageView imageView14=findViewById(R.id.imageView14);
        ImageView imageView15=findViewById(R.id.imageView15);
        ImageView imageView16=findViewById(R.id.imageView16);
        ImageView imageView17=findViewById(R.id.imageView17);
        ImageView imageView18=findViewById(R.id.imageView18);


        ImageView[] imageViews = {
                imageView1, imageView2, imageView3, imageView4, imageView5,
                imageView6, imageView7, imageView8, imageView9, imageView10,
                imageView11, imageView12, imageView13,imageView14,imageView15,
                imageView16,imageView17,imageView18,
        };

        for (int i = 0; i < imgLinks.length && i < imageViews.length; i++) {
            Picasso.get().load(imgLinks[i]).into(imageViews[i]);
        }




        Button productButton1 = findViewById(R.id.notesbtn1);
        Button productButton2 = findViewById(R.id.notesbtn2);
        Button productButton3 = findViewById(R.id.notesbtn3);
        Button productButton4 = findViewById(R.id.notesbtn4);
        Button productButton5 = findViewById(R.id.notesbtn5);
        Button productButton6 = findViewById(R.id.notesbtn6);
        Button productButton7 = findViewById(R.id.notesbtn7);
        Button productButton8 = findViewById(R.id.notesbtn8);
        Button productButton9 = findViewById(R.id.notesbtn9);
        Button productButton10 = findViewById(R.id.notesbtn10);
        Button productButton11= findViewById(R.id.notesbtn11);
        Button productButton12= findViewById(R.id.notesbtn12);
        Button productButton13= findViewById(R.id.notesbtn13);
        Button productButton14= findViewById(R.id.notesbtn14);
        Button productButton15= findViewById(R.id.notesbtn15);
        Button productButton16= findViewById(R.id.notesbtn16);
        Button productButton17= findViewById(R.id.notesbtn17);
        Button productButton18= findViewById(R.id.notesbtn18);

        productButton1.setOnClickListener(v -> showProductDetailsDialog(productName[0], productDetails[0], Price[0], BuyLink[0]));

        productButton2.setOnClickListener(v -> showProductDetailsDialog(productName[1], productDetails[1], Price[1], BuyLink[1]));

        productButton3.setOnClickListener(v -> showProductDetailsDialog(productName[2], productDetails[2], Price[2], BuyLink[2]));

        productButton4.setOnClickListener(v -> showProductDetailsDialog(productName[3], productDetails[3], Price[3], BuyLink[3]));

        productButton5.setOnClickListener(v -> showProductDetailsDialog(productName[4], productDetails[4], Price[4], BuyLink[4]));

        productButton6.setOnClickListener(v -> showProductDetailsDialog(productName[5], productDetails[5], Price[5], BuyLink[5]));

        productButton7.setOnClickListener(v -> showProductDetailsDialog(productName[6], productDetails[6], Price[6], BuyLink[6]));

        productButton8.setOnClickListener(v -> showProductDetailsDialog(productName[7], productDetails[7], Price[7], BuyLink[7]));

        productButton9.setOnClickListener(v -> showProductDetailsDialog(productName[8], productDetails[8], Price[8], BuyLink[8]));

        productButton10.setOnClickListener(v -> showProductDetailsDialog(productName[9], productDetails[9], Price[9], BuyLink[9]));

        productButton11.setOnClickListener(v -> showProductDetailsDialog(productName[10], productDetails[10], Price[10], BuyLink[10]));

        productButton12.setOnClickListener(v -> showProductDetailsDialog(productName[11], productDetails[11], Price[11], BuyLink[11]));

        productButton13.setOnClickListener(v -> showProductDetailsDialog(productName[12], productDetails[12], Price[12], BuyLink[12]));

        productButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProductDetailsDialog(productName[13], productDetails[13], Price[13], BuyLink[13]);
            }
        });
    productButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProductDetailsDialog(productName[14], productDetails[14], Price[14], BuyLink[14]);
            }
        });
    productButton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProductDetailsDialog(productName[15], productDetails[15], Price[15], BuyLink[15]);
            }
        });
productButton17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProductDetailsDialog(productName[16], productDetails[16], Price[16], BuyLink[16]);
            }
        });
productButton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProductDetailsDialog(productName[17], productDetails[17], Price[17], BuyLink[17]);
                textView.setVisibility(View.VISIBLE);
            }
        });


        ProgressBar progressBar = findViewById(R.id.progress_bar);

        // Show the ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        // Set a delay of 5 seconds (5000 milliseconds)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Hide the ProgressBar after 5 seconds
                progressBar.setVisibility(View.GONE);
            }
        }, 5000);


        FirebaseMessaging.getInstance().subscribeToTopic("SELL_NOTES")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";

                        // Check if subscribing to the topic was successful
                        if (!task.isSuccessful()) {
                            // If not successful, you can handle any failure here
                            // For example, you can uncomment the line below to set the message to "Subscribe failed"
                            // msg = "Subscribe failed";
                        } else {
                            // If subscription is successful, you can perform any necessary actions here
                            // For example, display a toast message to indicate success
                            // Toast.makeText(MainActivity3.this, "Done", Toast.LENGTH_SHORT).show();

                            // Log a message to check whether the user successfully subscribed to the topic
                            Log.d("notes_topic", msg);
                        }
                    }
                });


    }


    private void showProductDetailsDialog(String productName, String productDescription, double productPrice, String productLink) {
        DialogFragment dialogFragment = ProductDetailsDialogFragment.newInstance( productName, productDescription, productPrice, productLink);

        dialogFragment.show(getSupportFragmentManager(), "ProductDetailsDialogFragment");
    }

}
