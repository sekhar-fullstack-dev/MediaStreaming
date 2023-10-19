package com.example.mediastreaming.ui.activities.dashboard.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.util.UnstableApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediastreaming.MainActivity;
import com.example.mediastreaming.data.models.VideoRecyclerItem;
import com.example.mediastreaming.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Objects;

@UnstableApi
public class HomeFragment extends Fragment implements VideoRecyclerViewAdapter.OnVideoItemClickListener {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        initObservers(homeViewModel);
        binding.setModel(homeViewModel);
        return binding.getRoot();
    }

    private void initObservers(HomeViewModel homeViewModel) {
        homeViewModel.getVideoRecyclerViewitemList().observe(requireActivity(), videoRecyclerItems -> {
            if (videoRecyclerItems!=null){
                updateRecyclerView(videoRecyclerItems);
            }
        });
    }

    private void updateRecyclerView(ArrayList<VideoRecyclerItem> videoRecyclerItems) {
        ((VideoRecyclerViewAdapter) Objects.requireNonNull(recyclerView().getAdapter())).updateItems(videoRecyclerItems);
    }

    @Override
    public void videoItemClicked(VideoRecyclerItem item) {
        if (item!=null && item.getVideoUrl()!=null && !item.getVideoUrl().isEmpty()){
            Bundle b = new Bundle();
            b.putString("video_url",item.getVideoUrl());
            Intent intent = new Intent(requireContext(), MainActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }
    }

    private RecyclerView recyclerView(){
        if (binding.videoRecyclerView.getAdapter()==null){
            binding.videoRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            VideoRecyclerViewAdapter adapter = new VideoRecyclerViewAdapter(new ArrayList<>(),this);
            binding.videoRecyclerView.setAdapter(adapter);
        }
        return binding.videoRecyclerView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}