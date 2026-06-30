package com.dusol.thelearnerscommunity;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import android.graphics.drawable.Drawable;

public class CloudinaryImageLoader {

    public static final int SIZE_THUMBNAIL = 200;  // list items, small previews
    public static final int SIZE_MEDIUM    = 400;  // medium cards
    public static final int SIZE_FULL      = 800;  // full-screen / detail screens

    /**
     * Builds an optimized Cloudinary URL using transformation parameters.
     * f_auto  → serves WebP on Android
     * q_auto  → Cloudinary auto-selects the best quality level
     * w_{n}   → resizes to only the width the UI needs
     * c_limit → never upscales a small image
     */
    public static String buildOptimizedUrl(String originalCloudinaryUrl, int targetWidthPx) {
        if (originalCloudinaryUrl == null || originalCloudinaryUrl.isEmpty()) return null;

        // Only transform Cloudinary URLs — return other URLs unchanged
        if (!originalCloudinaryUrl.contains("res.cloudinary.com")) {
            return originalCloudinaryUrl;
        }

        // Insert transformation string after /upload/ in the Cloudinary URL
        String transformation = "f_auto,q_auto,w_" + targetWidthPx + ",c_limit/";
        return originalCloudinaryUrl.replace("/upload/", "/upload/" + transformation);
    }

    /**
     * Loads an image into an ImageView using the optimized URL + Glide caching.
     */
    public static void load(Context context, String rawUrl, ImageView imageView,
                            int sizePx, @DrawableRes int placeholder) {

        if (context == null) return;
        if (context instanceof android.app.Activity) {
            android.app.Activity activity = (android.app.Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return; // Prevent "You cannot start a load for a destroyed activity" crash
            }
        }

        String optimizedUrl = buildOptimizedUrl(rawUrl, sizePx);

        RequestBuilder<Drawable> request = Glide.with(context)
            .load(optimizedUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)  // Cache both original and transformed
            .skipMemoryCache(false)                    // Use memory cache for fast scrolling
            .transition(DrawableTransitionOptions.withCrossFade(200)); // Smooth fade-in

        if (placeholder != 0) {
            request = request.placeholder(placeholder).error(placeholder);
        }

        request.into(imageView);
    }

    /**
     * Convenience overload for thumbnail-size images.
     */
    public static void loadThumbnail(Context context, String rawUrl, ImageView imageView) {
        load(context, rawUrl, imageView, SIZE_THUMBNAIL, R.drawable.loading);
    }

    /**
     * Preloads an image into the disk cache WITHOUT displaying it.
     */
    public static void prefetch(Context context, String rawUrl, int sizePx) {
        String optimizedUrl = buildOptimizedUrl(rawUrl, sizePx);
        if (optimizedUrl == null) return;
        Glide.with(context.getApplicationContext())
            .downloadOnly()
            .load(optimizedUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .preload();
    }

    /**
     * Call only on explicit user-triggered refresh
     */
    public static void clearDiskCacheAsync(Context context) {
        new Thread(() -> Glide.get(context.getApplicationContext()).clearDiskCache()).start();
    }
}
