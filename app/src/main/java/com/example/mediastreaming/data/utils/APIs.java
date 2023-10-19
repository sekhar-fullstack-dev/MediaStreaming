package com.example.mediastreaming.data.utils;

import com.example.mediastreaming.data.models.requestbodies.VideoRecyclerItemRequestBody;
import com.example.mediastreaming.data.models.responses.VideoRecyclerViewItemResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIs {
    @POST("/getFile")
    Call<VideoRecyclerViewItemResponse> getVideoRecyclerItems(@Body VideoRecyclerItemRequestBody body);
}
