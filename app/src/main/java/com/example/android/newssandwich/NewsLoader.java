package com.example.android.newssandwich;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<ItemNews>> {

    private static final String TAG = "NewsLoader";

    String mUrl;

    public NewsLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<ItemNews> loadInBackground() {
        if(mUrl == null)
            return null;

        List<ItemNews> news = QueryUtils.fetchNewsList(mUrl);
        return news;
    }
}
