package com.example.mediastreaming.data.remote.repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mediastreaming.data.models.responses.createStreams.CreateStreamResponse;
import com.example.mediastreaming.data.models.responses.streams.LiveStreamsResponse;
import com.example.mediastreaming.data.models.responses.streams.Streams;
import com.example.mediastreaming.data.utils.APIs;
import com.example.mediastreaming.data.utils.RetrofitClient;
import com.example.mediastreaming.ui.activities.dashboard.ui.liveStream.LiveStreamFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveStreamFragmentRepo {
    public MutableLiveData<String> streamKey = new MutableLiveData<>();
    public MutableLiveData<List<Streams>> streams = new MutableLiveData<>();
    private APIs api;

    public LiveStreamFragmentRepo() {
        init();
    }
    private void init(){
        api = RetrofitClient.getInstance().getApIs();
        getStreams();
    }

    public void startStream() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title","");
        jsonObject.put("description","");
        jsonObject.put("is_live",true);
        jsonObject.put("current_playback_position","");
        api.createStream(jsonObject).enqueue(new Callback<CreateStreamResponse>() {
            @Override
            public void onResponse(@NonNull Call<CreateStreamResponse> call, @NonNull Response<CreateStreamResponse> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    streamKey.postValue(response.body().getData().getStream());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CreateStreamResponse> call, @NonNull Throwable t) {

            }
        });
    }

    public void getStreams(){
        api.getLiveStreams().enqueue(new Callback<LiveStreamsResponse>() {
            @Override
            public void onResponse(@NonNull Call<LiveStreamsResponse> call, @NonNull Response<LiveStreamsResponse> response) {
                assert response.body() != null;
                streams.postValue(response.body().getData());
            }

            @Override
            public void onFailure(@NonNull Call<LiveStreamsResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
