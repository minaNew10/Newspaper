package com.example.android.newssandwich;

public class ItemNews {
    private String mTitle;
    private String mSection;
    private String mUrl;

    public ItemNews(String mTitle, String mSection, String mUrl) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mUrl = mUrl;
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
}
