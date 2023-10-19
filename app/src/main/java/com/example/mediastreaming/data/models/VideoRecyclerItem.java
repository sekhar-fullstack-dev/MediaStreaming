package com.example.mediastreaming.data.models;

import androidx.annotation.NonNull;

import com.example.mediastreaming.base.BaseDataModel;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class VideoRecyclerItem extends BaseDataModel {
    @SerializedName("name")
    String videoTitle = "Video Title";
    String videoDescription = "Video Description",publisher;
    @SerializedName("video_url")
    String videoUrl;
    @SerializedName("thumbnail")
    String thumbnailURL;
    int videoDuration,previuosPlaybackPosition;

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    public int getPreviuosPlaybackPosition() {
        return previuosPlaybackPosition;
    }

    public void setPreviuosPlaybackPosition(int previuosPlaybackPosition) {
        this.previuosPlaybackPosition = previuosPlaybackPosition;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
