package com.example.android.newssandwich;

import android.graphics.Bitmap;

public class ItemNews {
    private String mTitle;
    private String mSection;
    private String mUrl;

    private String mAuthor;
    private String mDate;


    public ItemNews(String mTitle, String mSection, String mUrl) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mUrl = mUrl;
    }
    public ItemNews(String mTitle, String mSection, String mUrl,String author,String date) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mUrl = mUrl;
        this.mDate = date;
        this.mAuthor = author;

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


}
