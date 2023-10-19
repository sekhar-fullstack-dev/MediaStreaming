package com.example.mediastreaming.data.remote.repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mediastreaming.data.models.VideoRecyclerItem;
import com.example.mediastreaming.data.models.requestbodies.VideoRecyclerItemRequestBody;
import com.example.mediastreaming.data.models.responses.VideoRecyclerViewItemResponse;
import com.example.mediastreaming.data.utils.APIs;
import com.example.mediastreaming.data.utils.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentRepository {
    private APIs apIs;
    private MutableLiveData<ArrayList<VideoRecyclerItem>> videoRecyclerItemList = new MutableLiveData<>();
    public HomeFragmentRepository(){
        init();
    }
    private void init(){
        apIs = RetrofitClient.getInstance().getApIs();
        getVideoRecyclerViewItems();
    }

    public void getVideoRecyclerViewItems() {
        VideoRecyclerItemRequestBody input = new VideoRecyclerItemRequestBody();
        apIs.getVideoRecyclerItems(input).enqueue(new Callback<VideoRecyclerViewItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<VideoRecyclerViewItemResponse> call, @NonNull Response<VideoRecyclerViewItemResponse> response) {
                assert response.body() != null;
                videoRecyclerItemList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(@NonNull Call<VideoRecyclerViewItemResponse> call, @NonNull Throwable t) {
                videoRecyclerItemList.postValue(null);
            }
        });
    }


    //getters and setters
    public LiveData<ArrayList<VideoRecyclerItem>> getVideoRecyclerItemList() {
        return videoRecyclerItemList;
    }
}
