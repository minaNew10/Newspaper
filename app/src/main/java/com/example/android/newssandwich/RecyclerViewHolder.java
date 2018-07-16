package com.example.android.newssandwich;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView txtvTitle;
    TextView txtvSection;
    ConstraintLayout parent;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        parent = itemView.findViewById(R.id.parent);
        txtvTitle = itemView.findViewById(R.id.txtv_title);
        txtvSection = itemView.findViewById(R.id.txtv_section);
    }
}
