package com.example.mediastreaming.ui.activities.liveStream;

import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mediastreaming.R;
import com.example.mediastreaming.base.BaseActivity;
import com.example.mediastreaming.databinding.ActivityLiveStreamBinding;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LiveStreamActivity extends BaseActivity {
    private ActivityLiveStreamBinding binding;
    private LiveStreamViewModel model;
    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;
    private int bytesRead = 0;
    private ParcelFileDescriptor pfd;
    private final String TAG="LiveStreamActivity.class";
    private ExecutorService executor;
    private Socket _socket;
    private String SERVER_IP = "192.168.104.60";
    private int SERVER_PORT = 4000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_stream);
        model = new ViewModelProvider(this).get(LiveStreamViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        executor = Executors.newFixedThreadPool(2);
        askCameraAndMicPermission();
        /*File file = new File(file());
        try {
            pfd = ParcelFileDescriptor.open(file,ParcelFileDescriptor.MODE_READ_WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
    @Override
    protected void onCameraMicPermissionResult(boolean isPermission) {
        if (isPermission){
            initialiseRecorder();
            listenInterface();
        }
    }
    private void initialiseRecorder() {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            mediaRecorder.setAudioSamplingRate(44100);
            mediaRecorder.setVideoEncodingBitRate(10 * 1024 * 1024 * 8);
            mediaRecorder.setVideoFrameRate(15);
            mediaRecorder.setOrientationHint(0);

            mediaRecorder.setOutputFile(nextFile().getPath());
            mediaRecorder.setMaxFileSize(1000*1000);
            mediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
                @Override
                public void onInfo(MediaRecorder mr, int what, int extra) {
                    Log.d(TAG, "onInfo: what:"+what+" extra:"+extra);
                    if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_APPROACHING){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            try {
                                mediaRecorder.setNextOutputFile(nextFile());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    if (what == MediaRecorder.MEDIA_RECORDER_INFO_NEXT_OUTPUT_FILE_STARTED){
                        streamFile();
                    }
                }
            });
            mediaRecorder.setPreviewDisplay(binding.surfaceView.getHolder().getSurface());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void streamFile() {
        File f = streamingFile();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),f);
        Request request = new Request.Builder()
                .url("http://192.168.82.60:4000/upload2")
                .header("file_name",f.getName())
                .post(requestBody)
                .build();
        client().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: "+response.body());
            }
        });
    }

    private OkHttpClient client;
    private OkHttpClient client() {
        if (client==null){
            client = new OkHttpClient();
        }
        return client;
    }

    private void listenInterface() {
        binding.toggleRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRecording();
            }
        });
    }
    private void toggleRecording() {
        if (isRecording){
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
        }
        else{
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
               /* new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkFileInfo();
                    }
                },5000);*/
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        isRecording = !isRecording;
    }
    private void checkFileInfo() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int offset = 0;
                    FileDescriptor fd = pfd.getFileDescriptor();
                    FileInputStream fis = new FileInputStream(fd);
                    byte[] buff = new byte[100];
                    OutputStream outPutStream = new FileOutputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"another_video.mp4"));

                    while(isRecording){
                        bytesRead = fis.read(buff);
                        if (bytesRead>0){
                            Log.d(TAG, "run: "+ bytesRead);
                            outPutStream.write(buff);
                            //outPutStream.flush();
                        }
                    }
                    while ((bytesRead=fis.read(buff))>0){
                        Log.d(TAG, "run: "+ bytesRead);
                        outPutStream.write(buff);
                        //outPutStream.flush();
                    }
                    outPutStream.flush();
                    fis.close();
                    outPutStream.close();
                    //socket().close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void transferData(byte[] data) throws IOException {
        outPutStream().write(data);
    }

    private Socket socket(){
        try {
            if (_socket==null){
            _socket = new Socket(SERVER_IP,SERVER_PORT);
        }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return _socket;
    }

    private OutputStream outPutStream() throws IOException {
        return socket().getOutputStream();
    }

    private int nextFileCounter = 0;
    private File nextFile(){
        File mediaStreamingDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"MediaStreaming");
        if (!mediaStreamingDir.exists()){
            mediaStreamingDir.mkdir();
        }
        nextFileCounter+=1;
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"MediaStreaming/"+nextFileCounter+"_video.mp4");
    }
    private int streamingFile = 0;
    private File streamingFile(){
        streamingFile +=1;
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"MediaStreaming/"+ streamingFile +"_video.mp4");
    }
}
