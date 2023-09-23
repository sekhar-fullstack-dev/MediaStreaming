package com.example.mediastreaming;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;

import com.example.mediastreaming.base.BaseActivity;
import com.example.mediastreaming.databinding.ActivityMainBinding;
import com.example.mediastreaming.utils.Constants;

@UnstableApi public class  MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private MainViewModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        init();
    }

    private void init(){
        try {
            DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
// Create a progressive media source pointing to a stream uri.
//            MediaSource mediaSource =
//                    new ProgressiveMediaSource.Factory(dataSourceFactory)
//                            .createMediaSource(MediaItem.fromUri(Uri.parse(Constants.video_uri)));

            ExoPlayer player = new ExoPlayer.Builder(this).build();
            binding.playerView.setPlayer(player);
            MediaItem mediaItem = MediaItem.fromUri(Constants.video_uri);
            player.setMediaItem(mediaItem);
//            player.setMediaSource(mediaSource);
            player.prepare();
            player.play();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
