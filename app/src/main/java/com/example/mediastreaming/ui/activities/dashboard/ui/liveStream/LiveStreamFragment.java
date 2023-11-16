package com.example.mediastreaming.ui.activities.dashboard.ui.liveStream;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediastreaming.MainActivity;
import com.example.mediastreaming.R;
import com.example.mediastreaming.data.models.responses.streams.Streams;
import com.example.mediastreaming.databinding.FragmentLivestreamBinding;
import com.example.mediastreaming.databinding.LiveStreamRItemBinding;
import com.example.mediastreaming.ui.activities.rtmps.RtmpsActivity;
import com.example.mediastreaming.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class LiveStreamFragment extends Fragment {

    private FragmentLivestreamBinding binding;
    private LiveStreamFragmentViewModel model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLivestreamBinding.inflate(inflater, container, false);
        model = new ViewModelProvider(this).get(LiveStreamFragmentViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(getActivity());
        View root = binding.getRoot();
        observeVideModel();
        //loadTestData();
        return root;
    }
    private void observeVideModel() {
        model.getText().observe(getViewLifecycleOwner(), binding.textDashboard::setText);
        model.streamKey.observe(requireActivity(), s -> {
            if(s!=null && !s.isEmpty()){
                Intent intent = new Intent(getContext(), RtmpsActivity.class);
                intent.putExtra("streamKey",s);
                startActivity(intent);
            }
        });
        model.streams.observe(requireActivity(), new Observer<List<Streams>>() {
            @Override
            public void onChanged(List<Streams> streams) {
                if (streams!=null && streams.size()>0){
                    //updateStreamsRecyclerView();
                    updateGridView();
                }
            }
        });
    }
    private void updateStreamsRecyclerView() {
        binding.streamRv.setAdapter(streamsRV_adapter());
        if (binding.streamRv.getLayoutManager()==null){
            binding.streamRv.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }


    /***
     * Grid view
     * ***/
    private GridViewAdapter gridViewAdapter;
    private void updateGridView(){
        if(gridViewAdapter==null){
            gridViewAdapter = new GridViewAdapter(model.streams.getValue());
            binding.gridView.setAdapter(gridViewAdapter);
        }
        else{
            gridViewAdapter.updateGridView(model.streams.getValue());
        }
    }
    public class GridViewAdapter extends BaseAdapter {

        private List<Streams> streamsList;

        public GridViewAdapter(List<Streams> streamsList) {
            this.streamsList = streamsList;
        }

        public void updateGridView(List<Streams> list){
            streamsList = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return streamsList==null?0:streamsList.size();
        }

        @Override
        public Object getItem(int position) {
            return streamsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LiveStreamRItemBinding binding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.live_stream_r_item,parent,false);
                binding.setModel(streamsList.get(position));
                binding.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(requireActivity(),MainActivity.class);
                        Bundle b = new Bundle();
                        b.putString("video_url",streamsList.get(position).getVideoUrl());
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
                convertView = binding.getRoot();
            }
            return convertView;
        }
    }




    StreamsAdapter streamAdapter;
    private StreamsAdapter streamsRV_adapter() {
        if(streamAdapter == null){
            streamAdapter = new StreamsAdapter(model.streams.getValue());
        }
        else{
            streamAdapter.UpdateList(model.streams.getValue());
        }
        return streamAdapter;
    }

    public class StreamsAdapter extends RecyclerView.Adapter<StreamsAdapter.ViewHolder>{
        private List<Streams> list;
        public StreamsAdapter(List<Streams> list){
            this.list = list;
        }
        @SuppressLint("NotifyDataSetChanged")
        public void UpdateList(List<Streams> list){
            this.list.clear();
            this.list.addAll(list);
            this.notifyDataSetChanged();
        }
        @NonNull
        @Override
        public StreamsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LiveStreamRItemBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.live_stream_r_item,parent,false);
            return new ViewHolder(binding);
        }
        @Override
        public void onBindViewHolder(@NonNull StreamsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.binding.setModel(list.get(position));
            holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireActivity(), MainActivity.class);
                    Bundle b = new Bundle();
                    b.putString("video_url",list.get(position).getVideoUrl());
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return list!=null?list.size():0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final LiveStreamRItemBinding binding;
            public ViewHolder(@NonNull LiveStreamRItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

    public void loadTestData(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Streams> s_list = new ArrayList<>();
                Streams s = new Streams();
                s.setDescription(Constants.RTMP_STREAM_IP +"/live/10000/index.m3u8");
                s.setTitle("Test Title");
                s_list.add(s);
                streamsRV_adapter().UpdateList(s_list);
            }
        },1000);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}