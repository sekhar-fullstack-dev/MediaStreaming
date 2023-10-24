package com.example.mediastreaming.ui.activities.dashboard.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.mediastreaming.data.models.responses.VideoRecyclerItem;
import com.example.mediastreaming.data.remote.repos.HomeFragmentRepository;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<ArrayList<VideoRecyclerItem>> videoRecyclerViewitemList = new MutableLiveData<>();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        videoRecyclerViewitemList.postValue(getDummyList());
        HomeFragmentRepository repository = new HomeFragmentRepository();
        repository.getVideoRecyclerItemList().observeForever(new Observer<ArrayList<VideoRecyclerItem>>() {
            @Override
            public void onChanged(ArrayList<VideoRecyclerItem> videoRecyclerItems) {
                if (videoRecyclerItems!=null && !videoRecyclerItems.isEmpty()){
                    videoRecyclerViewitemList.postValue(videoRecyclerItems);
                }
            }
        });
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<VideoRecyclerItem>> getVideoRecyclerViewitemList() {
        return videoRecyclerViewitemList;
    }

    private ArrayList<VideoRecyclerItem> getDummyList(){
        ArrayList<VideoRecyclerItem> _l = new ArrayList<>();
        for(int i=0;i<10;i++){
            _l.add(new VideoRecyclerItem());
        }
        return _l;
    }
}