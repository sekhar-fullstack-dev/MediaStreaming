package com.example.mediastreaming.ui.activities.dashboard.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediastreaming.data.models.responses.VideoRecyclerItem;
import com.example.mediastreaming.databinding.CategoryRecyclerViewBinding;

import java.util.ArrayList;
import java.util.Collections;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.VideoHolder> {

    private final OnVideoItemClickListener listener;
    private ArrayList<VideoRecyclerItem> itemList;
    public VideoRecyclerViewAdapter(ArrayList<VideoRecyclerItem> itemList, OnVideoItemClickListener listener){
        this.itemList = itemList;
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public  void updateItems(ArrayList<VideoRecyclerItem> _itemList) {
        itemList = _itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryRecyclerViewBinding binding = CategoryRecyclerViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        //VideoRecycleviewItemBinding binding = VideoRecycleviewItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new VideoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, @SuppressLint("RecyclerView") int position) {
        /*holder.binding.setModel(itemList.get(position));
        holder.binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.videoItemClicked(itemList.get(position));
            }
        });*/

        ArrayList<VideoRecyclerItem> randomised = itemList;
        Collections.shuffle(randomised);
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(randomised,listener);
        holder.binding.recyclerView.setAdapter(adapter);
        holder.binding.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public int getItemCount() {
        return 10;//itemList==null?0:itemList.size();
    }

    public static class VideoHolder extends RecyclerView.ViewHolder {
        //private final VideoRecycleviewItemBinding binding;
        private final CategoryRecyclerViewBinding binding;
        /*public VideoHolder(@NonNull VideoRecycleviewItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }*/
        public VideoHolder(@NonNull CategoryRecyclerViewBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface OnVideoItemClickListener{
        void videoItemClicked(VideoRecyclerItem item);
    }
}
