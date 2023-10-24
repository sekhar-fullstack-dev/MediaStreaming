package com.example.mediastreaming.ui.activities.dashboard.ui.liveStream;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mediastreaming.data.models.responses.streams.Streams;
import com.example.mediastreaming.data.remote.repos.LiveStreamFragmentRepo;

import org.json.JSONException;

import java.util.List;

public class LiveStreamFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public MutableLiveData<Boolean> gotoLiveStreamActivity = new MutableLiveData<>(null);
    public LiveData<String> streamKey;
    public LiveData<List<Streams>> streams;
    private LiveStreamFragmentRepo repo;

    public LiveStreamFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is livestream fragment");
        repo = new LiveStreamFragmentRepo();
        streamKey = repo.streamKey;
        streams = repo.streams;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void startStream() {
        try {
            repo.startStream();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}