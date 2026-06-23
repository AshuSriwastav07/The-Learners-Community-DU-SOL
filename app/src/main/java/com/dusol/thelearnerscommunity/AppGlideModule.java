package com.dusol.thelearnerscommunity;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;

@GlideModule
public class AppGlideModule extends com.bumptech.glide.module.AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        // Disk cache: 150MB — stores downloaded images so they survive app restarts
        int diskCacheSizeBytes = 150 * 1024 * 1024; // 150 MB

        builder.setDiskCache(
            new InternalCacheDiskCacheFactory(context, "image_cache", diskCacheSizeBytes)
        );

        // Memory cache: 20MB for in-session fast loads (scrolling RecyclerViews)
        builder.setMemoryCache(new LruResourceCache(20 * 1024 * 1024));

        // Log only warnings in release builds
        builder.setLogLevel(Log.WARN);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false; // Disable legacy manifest-based module loading
    }
}
