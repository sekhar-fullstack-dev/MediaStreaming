package com.example.mediastreaming.data.utils;

import com.example.mediastreaming.data.models.requestbodies.VideoRecyclerItemRequestBody;
import com.example.mediastreaming.data.models.responses.createStreams.CreateStreamResponse;
import com.example.mediastreaming.data.models.responses.streams.LiveStreamsResponse;
import com.example.mediastreaming.data.models.responses.streams.Streams;
import com.example.mediastreaming.data.models.responses.VideoRecyclerViewItemResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIs {
    @POST("/getFile")
    Call<VideoRecyclerViewItemResponse> getVideoRecyclerItems(@Body VideoRecyclerItemRequestBody body);

    @GET("/getMath")
    Call<VideoRecyclerViewItemResponse> getVideoRecyclerItems2();

    @POST("/startStream")
    Call<CreateStreamResponse> createStream(@Body JSONObject body);

    @GET("/streams")
    Call<LiveStreamsResponse>getLiveStreams();
}
