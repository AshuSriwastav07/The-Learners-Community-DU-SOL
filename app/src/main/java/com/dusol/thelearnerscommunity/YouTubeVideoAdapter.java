package com.dusol.thelearnerscommunity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * RecyclerView adapter for displaying YouTube video items.
 */
public class YouTubeVideoAdapter extends RecyclerView.Adapter<YouTubeVideoAdapter.VideoViewHolder> {

    private final List<YouTubeVideoItem> videoList;
    private final Context context;
    private final int layoutId;

    public YouTubeVideoAdapter(Context context, List<YouTubeVideoItem> videoList) {
        this(context, videoList, R.layout.item_youtube_video);
    }

    public YouTubeVideoAdapter(Context context, List<YouTubeVideoItem> videoList, int layoutId) {
        this.context = context;
        this.videoList = videoList;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        YouTubeVideoItem video = videoList.get(position);

        holder.titleTextView.setText(video.getTitle());
        holder.dateTextView.setText(formatDate(video.getPublishedAt()));

        // Load thumbnail using CloudinaryImageLoader (non-Cloudinary URLs like YouTube pass through automatically)
        CloudinaryImageLoader.loadThumbnail(context, video.getThumbnailUrl(), holder.thumbnailImageView);

        // Open video in YouTube app or browser on click
        holder.itemView.setOnClickListener(v -> {
            try {
                String videoUrl = "https://www.youtube.com/watch?v=" + video.getVideoId();
                Uri uri = Uri.parse(videoUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.youtube");

                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                } else {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            } catch (Exception e) {
                Toast.makeText(context, "Unable to open video", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    /**
     * Format ISO 8601 date string to a human-readable format.
     */
    private String formatDate(String isoDate) {
        if (isoDate == null || isoDate.isEmpty()) return "";
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = isoFormat.parse(isoDate);
            if (date != null) {
                SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMM yyyy", Locale.US);
                return displayFormat.format(date);
            }
        } catch (ParseException e) {
            // fallback: return raw string trimmed
            if (isoDate.length() >= 10) {
                return isoDate.substring(0, 10);
            }
        }
        return isoDate;
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImageView;
        TextView titleTextView;
        TextView dateTextView;

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.videoThumbnail);
            titleTextView = itemView.findViewById(R.id.videoTitle);
            dateTextView = itemView.findViewById(R.id.videoDate);
        }
    }
}
