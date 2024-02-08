package com.example.mediastreaming.ui.activities.dashboard.ui.classRoom;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClassRoomFragmentViewModel extends ViewModel {

    private MutableLiveData<Boolean> startExamClicked = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> getStartExamClicked() {
        return startExamClicked;
    }
    public void setStartExamClicked(Boolean startExamClicked) {
        this.startExamClicked.setValue(startExamClicked);
    }

    private MutableLiveData<Boolean> myQuestions = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> getMyQuestions() {
        return myQuestions;
    }
    public void setMyQuestions(Boolean myQuestions) {
        this.myQuestions.setValue(myQuestions);
    }

    public void startExam(){
        startExamClicked.postValue(true);
    }
    public void myQuestions(){
        myQuestions.setValue(true);
    }

}
