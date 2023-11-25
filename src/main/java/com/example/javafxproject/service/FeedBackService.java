package com.example.javafxproject.service;

import com.example.javafxproject.dto.Book;
import com.example.javafxproject.dto.FeedBack;
import com.example.javafxproject.repository.FeedBackRepository;
import javafx.collections.ObservableList;

public class FeedBackService {
    private FeedBackRepository feedBackRepository;

    public FeedBackService(){
        feedBackRepository = new FeedBackRepository();
    }

    public ObservableList<FeedBack> getFeedBackList() {return feedBackRepository.getFeedBackList();}

    public void addFeedBack(FeedBack feedBack){
        feedBackRepository.addFeedBack(feedBack);
    }

    public void deleteFeedBack(FeedBack feedBack){
        feedBackRepository.deleteFeedBack(feedBack);
    }

}
