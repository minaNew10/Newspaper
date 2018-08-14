package com.example.android.newssandwich;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 14/07/2018.
 */

public class PoliticsFragment extends Fragment  implements LoaderManager.LoaderCallbacks<List<ItemNews>> {
    private static final String TAG = "PoliticsFragment";
//    String url = "https://content.guardianapis.com/politics?api-key=b70d4f5d-e00b-44c3-8d15-6c700f643073";
    private static final String GUARDIAN_URL = "https://content.guardianapis.com";

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;
    TextView txtvEmptyState;
    ProgressBar progressBar;

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view,container,false);

        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        txtvEmptyState = v.findViewById(R.id.txtvEmptyState);
        progressBar = v.findViewById(R.id.loading_spinner);

        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            progressBar.setVisibility(View.VISIBLE);
            getLoaderManager().initLoader(0,null,this);
        } else {
            progressBar.setVisibility(View.GONE);
            txtvEmptyState.setVisibility(View.VISIBLE);
            txtvEmptyState.setText("No internet connection");
        }
        final Handler handler = new Handler();
        Runnable runable = new Runnable() {

            @Override
            public void run() {
                if(isAdded()) {
                    if (networkInfo != null && networkInfo.isConnected()) {

                        getLoaderManager().restartLoader(0, null, PoliticsFragment.this);
                        if (recyclerViewAdapter != null)
                            recyclerViewAdapter.notifyDataSetChanged();
                        handler.postDelayed(this, 1000);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        txtvEmptyState.setVisibility(View.VISIBLE);
                        txtvEmptyState.setText("No internet connection");
                    }
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
        uriBuilder.appendPath("politics");
        uriBuilder.appendQueryParameter("api-key","b70d4f5d-e00b-44c3-8d15-6c700f643073");
        uriBuilder.appendQueryParameter("show-fields","all");
        return new NewsLoader(this.getActivity(),uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<ItemNews>> loader, List<ItemNews> data) {
        progressBar.setVisibility(View.GONE);
        if (data == null || data.size() < 1) {
            txtvEmptyState.setVisibility(View.VISIBLE);
            txtvEmptyState.setText("No Data Available");
        } else {
            txtvEmptyState.setVisibility(View.GONE);
            recyclerViewAdapter = new RecyclerViewAdapter(this.getActivity(), data);

            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<ItemNews>> loader) {

    }
}
