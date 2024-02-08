package com.example.mediastreaming.ui.activities.dashboard.ui.myQuestions;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyQuestionFragViewModel extends ViewModel {
    private MutableLiveData<Boolean> addQuestions = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> getAddQuestions() {
        return addQuestions;
    }

    public void addQuestions(){
        addQuestions.setValue(true);
    }
}
