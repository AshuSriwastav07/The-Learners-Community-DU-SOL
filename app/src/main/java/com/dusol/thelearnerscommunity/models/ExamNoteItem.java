package com.dusol.thelearnerscommunity.models;

public class ExamNoteItem {
    private String imageUrl;
    private String pageLink;

    public ExamNoteItem(String imageUrl, String pageLink) {
        this.imageUrl = imageUrl;
        this.pageLink = pageLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPageLink() {
        return pageLink;
    }
}
