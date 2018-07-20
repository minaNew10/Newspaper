package com.example.android.newssandwich;

import android.support.v4.content.AsyncTaskLoader;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
