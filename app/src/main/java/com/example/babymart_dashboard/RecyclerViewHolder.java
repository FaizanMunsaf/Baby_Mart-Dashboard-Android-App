package com.example.babymart_dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView rating,name;
    LinearLayout linearLayout;
    public RecyclerViewHolder(@NonNull View itemView) {

        super(itemView);
        image = itemView.findViewById(R.id.images);
        name = itemView.findViewById(R.id.name);
        rating = itemView.findViewById(R.id.raiting);
        linearLayout = itemView.findViewById(R.id.linearlayout);
    }
}
