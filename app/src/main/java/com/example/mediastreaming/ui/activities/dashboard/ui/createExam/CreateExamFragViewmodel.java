package com.example.mediastreaming.ui.activities.dashboard.ui.createExam;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateExamFragViewmodel extends ViewModel {
    public MutableLiveData<Boolean> addQuestions = new MutableLiveData<>(false);
    public void addQuestion(){
        addQuestions.setValue(true);
    }
}
