package com.example.mediastreaming.data.models.responses;

import com.example.mediastreaming.data.models.VideoRecyclerItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideoRecyclerViewItemResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private String error;
    @SerializedName("error_message")
    private String errorMessage;
    @SerializedName("data")
    private List<VideoRecyclerItem> data;

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ArrayList<VideoRecyclerItem> getData() {
        return (ArrayList<VideoRecyclerItem>) data;
    }
}
