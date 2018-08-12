package com.example.android.newssandwich;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 14/07/2018.
 */

public class SportFragment extends Fragment  implements LoaderManager.LoaderCallbacks<List<ItemNews>>  {
    private static final String TAG = "SportFragment";
//    String url = "https://content.guardianapis.com/sport?api-key=b70d4f5d-e00b-44c3-8d15-6c700f643073";
    private static final String GUARDIAN_URL = "https://content.guardianapis.com";

    RecyclerView recyclerView;
    List<ItemNews> news = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view,container,false);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),news);
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(recyclerViewAdapter);
        getLoaderManager().initLoader(0,null,this);
        final Handler handler = new Handler();
        Runnable runable = new Runnable() {

            @Override
            public void run() {
                if(isAdded()) {
                    getLoaderManager().restartLoader(0,null,SportFragment.this);
                    recyclerViewAdapter.notifyDataSetChanged();
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(runable, 1000);

        return v;

    }



    @NonNull
    @Override
    public Loader<List<ItemNews>> onCreateLoader(int id, @Nullable Bundle args) {
        Uri baseUrl = Uri.parse(GUARDIAN_URL);
        Uri.Builder uriBuilder = baseUrl.buildUpon();
        uriBuilder.appendPath("sport");
        uriBuilder.appendQueryParameter("api-key","b70d4f5d-e00b-44c3-8d15-6c700f643073");
        uriBuilder.appendQueryParameter("show-fields","all");
        Log.i(TAG, "onCreateLoader: " + uriBuilder.toString());
        return new NewsLoader(this.getActivity(),uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<ItemNews>> loader, List<ItemNews> data) {
        if(data != null){

            recyclerViewAdapter.newsFeed = data;
        }

        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<ItemNews>> loader) {

    }
}
