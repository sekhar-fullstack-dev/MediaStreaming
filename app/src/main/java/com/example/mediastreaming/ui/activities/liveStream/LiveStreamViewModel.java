package com.example.mediastreaming.ui.activities.liveStream;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveStreamViewModel extends ViewModel {
    MutableLiveData<Boolean> isLive = new MutableLiveData<>(false);
}
