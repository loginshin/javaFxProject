package com.example.javafxproject.dto;

public class FeedBack {
    //피드백 내용과 해결 여부
    private String FeedBack;

    private boolean solve;

    public FeedBack(String feedBack, boolean solve) {
        FeedBack = feedBack;
        this.solve = solve;
    }
    public FeedBack(){

    }

    public String getFeedBack() {
        return FeedBack;
    }

    public void setFeedBack(String feedBack) {
        FeedBack = feedBack;
    }

    public boolean isSolve() {
        return solve;
    }

    public void setSolve(boolean solve) {
        this.solve = solve;
    }
}

