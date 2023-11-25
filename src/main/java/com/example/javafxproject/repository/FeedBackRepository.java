package com.example.javafxproject.repository;

import com.example.javafxproject.dto.FeedBack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FeedBackRepository {
    ObservableList FeedBackList;

    public FeedBackRepository() {
        FeedBackList = FXCollections.observableArrayList();
    }

    public ObservableList<FeedBack> getFeedBackList() {
        return FeedBackList;
    }

    public void setFeedBackList(ObservableList<FeedBack> feedBackList) {this.FeedBackList=FeedBackList;}

    public void addFeedBack(FeedBack FeedBack){
        FeedBackList.add(FeedBack);
    }

    public void deleteFeedBack(FeedBack FeedBack){
        FeedBackList.remove(FeedBack);
    }


}
