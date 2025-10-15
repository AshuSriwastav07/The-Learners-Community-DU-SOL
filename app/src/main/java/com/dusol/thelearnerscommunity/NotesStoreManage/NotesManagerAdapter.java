package com.dusol.thelearnerscommunity.NotesStoreManage;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dusol.thelearnerscommunity.ProductDetailsDialogFragment;
import com.dusol.thelearnerscommunity.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotesManagerAdapter extends RecyclerView.Adapter<NotesManagerAdapter.MyViewHolder> {

    private static final String TAG = "NotesManagerAdapter";
    private static final String DEFAULT_TITLE = "Unknown Title";
    private static final String DEFAULT_PRODUCT = "Unknown Product";
    
    private final Context context;
    private final String[] notesTitles;
    private final String[] notesImageView;
    private final String[] openNotesPageLinks;
    private final String[] productAllDetails;
    private final FragmentManager fragmentManager;
    private final boolean isContextValid;

    // Production-ready constructor with comprehensive validation
    public NotesManagerAdapter(@Nullable Activity activity, 
                              @Nullable List<String> notesTitles, 
                              @Nullable List<String> notesImageView, 
                              @Nullable List<String> openNotesPageLinks, 
                              @Nullable List<String> productDetails, 
                              @Nullable FragmentManager fragmentManager) {
        
        this.context = activity;
        this.isContextValid = activity != null && !activity.isFinishing() && !activity.isDestroyed();
        
        // Safe array conversion with null checks
        this.notesTitles = convertListToArray(notesTitles);
        this.notesImageView = convertListToArray(notesImageView);
        this.openNotesPageLinks = convertListToArray(openNotesPageLinks);
        this.productAllDetails = convertListToArray(productDetails);
        this.fragmentManager = fragmentManager;
        
        Log.d(TAG, "Adapter created with " + this.notesTitles.length + " items");
    }

    private String[] convertListToArray(@Nullable List<String> list) {
        if (list == null) {
            return new String[0];
        }
        return list.toArray(new String[0]);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final Button openNotesLink;
        private final ImageView notesImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesSubjectName);
            openNotesLink = itemView.findViewById(R.id.openNotesLink);
            notesImage = itemView.findViewById(R.id.notesImageView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            if (context == null) {
                throw new IllegalStateException("Context is null");
            }
            
            View itemView = LayoutInflater.from(context).inflate(R.layout.notes_product_item, parent, false);
            return new MyViewHolder(itemView);
        } catch (Exception e) {
            Log.e(TAG, "Error creating view holder: " + e.getMessage(), e);
            throw new RuntimeException("Failed to create view holder", e);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            if (!isValidPosition(position)) {
                Log.w(TAG, "Invalid position: " + position);
                return;
            }

            // Set title with null safety
            String title = getSafeString(notesTitles, position, DEFAULT_TITLE);
            holder.title.setText(title);

            // Load image with error handling
            loadImageSafely(holder.notesImage, position);

            // Set click listener with comprehensive error handling
            holder.openNotesLink.setOnClickListener(v -> handleButtonClick(position));

        } catch (Exception e) {
            Log.e(TAG, "Error binding view holder at position " + position + ": " + e.getMessage(), e);
            showErrorToUser("Error displaying item");
        }
    }

    private boolean isValidPosition(int position) {
        return position >= 0 && position < getItemCount();
    }

    private String getSafeString(String[] array, int position, String defaultValue) {
        try {
            if (array != null && position >= 0 && position < array.length && array[position] != null) {
                return array[position].trim();
            }
        } catch (Exception e) {
            Log.w(TAG, "Error getting string at position " + position + ": " + e.getMessage());
        }
        return defaultValue;
    }

    private void loadImageSafely(@NonNull ImageView imageView, int position) {
        try {
            String imageUrl = getSafeString(notesImageView, position, "");
            
            if (imageUrl.isEmpty()) {
                imageView.setImageResource(R.drawable.nopictures);
                return;
            }

            Picasso.get()
                    .load(imageUrl)
                    .error(R.drawable.nopictures)
                    .placeholder(R.drawable.loading)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "Image loaded successfully for position " + position);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.w(TAG, "Failed to load image for position " + position + ": " + e.getMessage());
                            imageView.setImageResource(R.drawable.nopictures);
                        }
                    });

        } catch (Exception e) {
            Log.e(TAG, "Error loading image for position " + position + ": " + e.getMessage(), e);
            imageView.setImageResource(R.drawable.nopictures);
        }
    }

    private void handleButtonClick(int position) {
        try {
            if (!isValidPosition(position)) {
                Log.w(TAG, "Invalid position for button click: " + position);
                showErrorToUser("Invalid item selected");
                return;
            }

            if (!isContextValid) {
                Log.w(TAG, "Context is not valid for button click");
                showErrorToUser("Application context error");
                return;
            }

            String productName = getSafeString(notesTitles, position, DEFAULT_PRODUCT);
            String productDetails = getSafeString(productAllDetails, position, "");
            String productLink = getSafeString(openNotesPageLinks, position, "");

            if (productLink.isEmpty()) {
                showErrorToUser("Product link not available");
                return;
            }

            showProductDetailsDialog(productName, productDetails, productLink);

        } catch (Exception e) {
            Log.e(TAG, "Error handling button click at position " + position + ": " + e.getMessage(), e);
            showErrorToUser("Error opening product details");
        }
    }

    private void showProductDetailsDialog(String productName, String productDescription, String productLink) {
        try {
            if (fragmentManager == null) {
                Log.e(TAG, "FragmentManager is null");
                showErrorToUser("Error showing product details");
                return;
            }

            if (!isContextValid) {
                Log.w(TAG, "Context is not valid for showing dialog");
                showErrorToUser("Application context error");
                return;
            }

            DialogFragment dialogFragment = ProductDetailsDialogFragment.newInstance(
                    productName, productDescription, 0, productLink);
            
            dialogFragment.show(fragmentManager, "ProductDetailsDialogFragment");
            
            Log.d(TAG, "Product details dialog shown for: " + productName);

        } catch (Exception e) {
            Log.e(TAG, "Error showing product details dialog: " + e.getMessage(), e);
            showErrorToUser("Error showing product details");
        }
    }

    private void showErrorToUser(String message) {
        try {
            if (context != null && isContextValid) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error showing toast: " + e.getMessage(), e);
        }
    }

    @Override
    public int getItemCount() {
        return notesTitles != null ? notesTitles.length : 0;
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
        // Cancel any pending image loads for this view holder
        try {
            if (holder.notesImage != null) {
                Picasso.get().cancelRequest(holder.notesImage);
            }
        } catch (Exception e) {
            Log.w(TAG, "Error canceling image request: " + e.getMessage());
        }
    }
}
