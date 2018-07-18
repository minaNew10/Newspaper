package com.example.android.newssandwich;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 14/07/2018.
 */

public class ArtFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<ItemNews>> {
    String url = "https://content.guardianapis.com/search?api-key=b70d4f5d-e00b-44c3-8d15-6c700f643073";
    RecyclerView recyclerView;
    List<ItemNews> news = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view,container,false);
        news.add(new ItemNews("dfsdfdf","dfsdaasdf","dfadfeasdfda"));
        news.add(new ItemNews("dfsdfdf","dfsdaasdf","dfadfeasdfda"));
        news.add(new ItemNews("dfsdfdf","dfsdaasdf","dfadfeasdfda"));
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new RecyclerViewAdapter(getContext(),news));
        return v;

    }

    @Override
    public Loader<List<ItemNews>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<ItemNews>> loader, List<ItemNews> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<ItemNews>> loader) {

    }
}
