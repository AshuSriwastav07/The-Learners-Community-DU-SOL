package com.dusol.thelearnerscommunity;

/**
 * Model class representing a single YouTube video item.
 */
public class YouTubeVideoItem {
    private final String videoId;
    private final String title;
    private final String thumbnailUrl;
    private final String publishedAt;

    public YouTubeVideoItem(String videoId, String title, String thumbnailUrl, String publishedAt) {
        this.videoId = videoId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.publishedAt = publishedAt;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
