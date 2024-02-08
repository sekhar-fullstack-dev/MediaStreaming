package com.example.mediastreaming.ui.activities.dashboard.ui.myQuestions;

public class Question {
    private String question;
    private int option=-1;
    private boolean isSelected;

    public Question(String question, int option, boolean isSelected) {
        this.question = question;
        this.option = option;
        this.isSelected = isSelected;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
