package com.example.mediastreaming;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSourceFactory;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.ui.AspectRatioFrameLayout;

import com.example.mediastreaming.base.BaseActivity;
import com.example.mediastreaming.data.utils.EncryptedDatasourceFactory;
import com.example.mediastreaming.databinding.ActivityMainBinding;
import com.example.mediastreaming.utils.Constants;

import java.util.Objects;

@UnstableApi
public class  MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private MainViewModel model;
    private String videoURL="";
    private ExoPlayer player;
    private final String TAG="MainActivity.class";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (savedInstanceState!=null && savedInstanceState.containsKey("playbackPosition")){
            playbackPosition = savedInstanceState.getLong("playbackPosition",0L);
        }
        if (savedInstanceState!=null && savedInstanceState.containsKey("playWhenReady")){
            playWhenReady = savedInstanceState.getBoolean("playWhenReady",true);
        }
        videoURL = Objects.requireNonNull(getIntent().getExtras()).containsKey("video_url")
                ?getIntent().getExtras().getString("video_url"):"";
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(MainActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        if (Util.SDK_INT>23){
            initialisePlayer();
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        super.onResume();
        if (Util.SDK_INT<=23 || player==null){
            initialisePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        if (Util.SDK_INT>23){
            releasePlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        playbackPosition = player.getCurrentPosition();
        playWhenReady = player.getPlayWhenReady();
        Log.d(TAG, "onPause: ");
        if (Util.SDK_INT<=23){
            releasePlayer();
        }
    }

    private void initialisePlayer(){
        try {
            if (player==null){
                player = new ExoPlayer.Builder(getApplicationContext()).build();
                binding.playerView.setPlayer(player);
                _setListeners();
                if(!videoURL.isEmpty()){
                    if(videoURL.contains("index.m3u8")){
                        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)binding.playerView.getLayoutParams();
                        params.dimensionRatio = "3:4";
                        binding.playerView.setLayoutParams(params);
                        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(new DefaultDataSourceFactory(this, "MediaStreaming"))
                                .createMediaSource(MediaItem.fromUri(videoURL));
                        player.setMediaSource(hlsMediaSource);
                        player.prepare();
                        player.setPlayWhenReady(playWhenReady);
                    }
                    else{
                        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoURL));
                        player.setMediaItem(mediaItem);
                        player.prepare();
                        player.seekTo(playbackPosition);
                        player.setPlayWhenReady(playWhenReady);
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void _setListeners() {

        player.addListener(new Player.Listener() {
            @Override
            public void onVideoSizeChanged(@NonNull VideoSize videoSize) {
                adjustPlayerViewSize(videoSize.width, videoSize.height);
            }
        });
    }

    private void adjustPlayerViewSize(int videoWidth, int videoHeight) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;
        if (videoWidth > videoHeight) {
            // Horizontal video
            binding.playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
        } else {
            // Vertical video
            binding.playerView.setVideoSize(screenWidth,screenHeight);
        }
    }

    private boolean playWhenReady = true;
    private int mediaIndexItem = 0;
    private long playbackPosition = 0L;
    private void releasePlayer(){
        if (player!=null){
            playWhenReady = player.getPlayWhenReady();
            mediaIndexItem = player.getCurrentMediaItemIndex();
            playbackPosition = player.getCurrentPosition();
            player.release();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong("playbackPosition",playbackPosition);
        outState.putBoolean("playWhenReady",playWhenReady);
        Log.d(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
    }
}
