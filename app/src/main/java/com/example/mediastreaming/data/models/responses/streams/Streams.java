package com.example.mediastreaming.data.models.responses.streams;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Streams implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_live")
    @Expose
    private Boolean isLive;
    @SerializedName("current_playback_time")
    @Expose
    private String currentPlaybackTime;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("video_thumbnail")
    @Expose
    private String videoThumbnail;

    private final static long serialVersionUID = 1935377295471195587L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }

    public String getCurrentPlaybackTime() {
        return currentPlaybackTime;
    }

    public void setCurrentPlaybackTime(String currentPlaybackTime) {
        this.currentPlaybackTime = currentPlaybackTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Streams)) return false;
        Streams streams = (Streams) o;
        return getId().equals(streams.getId()) && getTitle().equals(streams.getTitle()) && getDescription().equals(streams.getDescription()) && getIsLive().equals(streams.getIsLive()) && getCurrentPlaybackTime().equals(streams.getCurrentPlaybackTime()) && getVideoUrl().equals(streams.getVideoUrl()) && getVideoThumbnail().equals(streams.getVideoThumbnail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getIsLive(), getCurrentPlaybackTime(), getVideoUrl(), getVideoThumbnail());
    }

    @Override
    public String toString() {
        return "Streams{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isLive=" + isLive +
                ", currentPlaybackTime='" + currentPlaybackTime + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoThumbnail='" + videoThumbnail + '\'' +
                '}';
    }
}