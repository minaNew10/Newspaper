package com.example.android.newssandwich;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    Context context;
    List<ItemNews> newsFeed;

    public RecyclerViewAdapter(Context context, List<ItemNews> newsFeed) {
        this.context = context;
        this.newsFeed = newsFeed;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final ItemNews itemNews = newsFeed.get(position);
        holder.txtvTitle.setText(itemNews.getmTitle());
        holder.txtvSection.setText(itemNews.getmSection());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse(itemNews.getmUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW,url);
                context.startActivity(intent);


            }
        });
        holder.txtvAuthorName.setText(itemNews.getmAuthor());
        String date = itemNews.getmDate();
        String[] dateAndTime = date.split("T");
        holder.txtvDate.setText(dateAndTime[0]);


    }

    @Override
    public int getItemCount() {
        return newsFeed.size();
    }
}
