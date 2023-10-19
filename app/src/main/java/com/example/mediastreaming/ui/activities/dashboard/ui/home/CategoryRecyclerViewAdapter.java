package com.example.mediastreaming.ui.activities.dashboard.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediastreaming.MainActivity;
import com.example.mediastreaming.data.models.VideoRecyclerItem;
import com.example.mediastreaming.databinding.CategoryRecyclerViewItemBinding;

import java.util.ArrayList;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<VideoRecyclerItem> itemList;
    private final VideoRecyclerViewAdapter.OnVideoItemClickListener listener;

    public CategoryRecyclerViewAdapter(ArrayList<VideoRecyclerItem> itemList, VideoRecyclerViewAdapter.OnVideoItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryRecyclerViewItemBinding binding = CategoryRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.binding.setModel(itemList.get(position));
        holder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.videoItemClicked(itemList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList!=null?itemList.size():0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CategoryRecyclerViewItemBinding binding;

        public ViewHolder(@NonNull CategoryRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
