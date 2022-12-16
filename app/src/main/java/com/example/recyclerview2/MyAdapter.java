package com.example.recyclerview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<item> items;
    private ItemClickListener mItemListener;


    public MyAdapter(Context context, List<item> items,ItemClickListener itemClickListener) {
        this.context = context;
        this.items = items;
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(items.get(position).getNum());
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(position);
        });

    }

    @Override
    public int getItemCount() {

        return items.size();
    }
}
