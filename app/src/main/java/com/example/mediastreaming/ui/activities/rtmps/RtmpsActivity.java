package com.example.mediastreaming.ui.activities.rtmps;

import static com.example.mediastreaming.utils.Utils.displayHeight;
import static com.example.mediastreaming.utils.Utils.displayWidth;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.mediastreaming.R;
import com.example.mediastreaming.base.BaseActivity;
import com.example.mediastreaming.databinding.ActivityRtmpsBinding;
import com.example.mediastreaming.utils.Constants;
import com.pedro.encoder.input.video.CameraHelper;
import com.pedro.library.rtmp.RtmpCamera1;
import com.pedro.rtmp.utils.ConnectCheckerRtmp;

public class RtmpsActivity extends BaseActivity {
    private RtmpCamera1 rtmpCamera1;
    private ActivityRtmpsBinding binding;
    private String streamKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        streamKey = getIntent().getStringExtra("streamKey");
        binding = DataBindingUtil.setContentView(this,R.layout.activity_rtmps);
        binding.setLifecycleOwner(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rtmpCamera1 = new RtmpCamera1(binding.openGL, new ConnectCheckerRtmp() {
                    @Override
                    public void onConnectionStartedRtmp(@NonNull String s) {

                    }

                    @Override
                    public void onConnectionSuccessRtmp() {

                    }

                    @Override
                    public void onConnectionFailedRtmp(@NonNull String s) {

                    }

                    @Override
                    public void onNewBitrateRtmp(long l) {

                    }

                    @Override
                    public void onDisconnectRtmp() {

                    }

                    @Override
                    public void onAuthErrorRtmp() {

                    }

                    @Override
                    public void onAuthSuccessRtmp() {

                    }
                });
                // Assuming you have a button or some trigger to start the stream:
                binding.stream.setOnClickListener(v -> startStream());
            }
        },600);
    }

    private void startStream() {
        int rotation = CameraHelper.getCameraOrientation(this);
        int height = (int) (640*(displayHeight(this)/displayWidth(this)));
        int width = 640;
        if (!rtmpCamera1.isStreaming()) {
            if (rtmpCamera1.prepareAudio() && rtmpCamera1.prepareVideo(640, 480, 30, 1228800, rotation)) {
                rtmpCamera1.startStream("rtmp://"+ Constants.RTMP_STREAM_IP +"/live/"+streamKey);
                Log.d(getClass().getName(), "startStream: "+streamKey);
            } else {
                // Handle error, e.g., show a Toast
            }
        } else {
            rtmpCamera1.stopStream();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rtmpCamera1!=null && !rtmpCamera1.isStreaming()) {
            rtmpCamera1.startPreview(CameraHelper.Facing.FRONT);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!rtmpCamera1.isStreaming()) {
            rtmpCamera1.stopPreview();
        }
    }
}
