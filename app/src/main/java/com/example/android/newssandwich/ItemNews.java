package com.example.android.newssandwich;

import android.graphics.Bitmap;

public class ItemNews {
    private String mTitle;
    private String mSection;
    private String mUrl;

    private String mAuthor;
    private String mDate;
    private Bitmap mThumbnail;

    public ItemNews(String mTitle, String mSection, String mUrl) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mUrl = mUrl;
    }
    public ItemNews(String mTitle, String mSection, String mUrl,String author,String date,Bitmap thumbnail) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mUrl = mUrl;
        this.mDate = date;
        this.mAuthor = author;
        this.mThumbnail = thumbnail;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmDate() {
        return mDate;
    }

    public Bitmap getmThumbnail() {
        return mThumbnail;
    }
}
