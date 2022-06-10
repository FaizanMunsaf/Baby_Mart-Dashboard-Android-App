package com.example.babymart_dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Context context;
    ArrayList<UserModel> mylist;

    public RecyclerAdapter(Context context, ArrayList<UserModel> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        UserModel currentItem = mylist.get(position);
        Picasso.with(context).load(mylist.get(position).getImage()).into(holder.image);
        holder.name.setText(currentItem.getName());
        holder.rating.setText(currentItem.getRating());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }
}

