package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class RentCompletedController implements Initializable {

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;

    Book book;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // PauseTransition 초기화
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));
        pauseTransition.setOnFinished(event -> {
            Stage stage = (Stage) bookImage.getScene().getWindow(); // 현재 창의 Stage 가져오기
            stage.close(); // 창 닫기
        });
        pauseTransition.play();


    }

    public void setBook(Book book) {
        this.book = book;
        indicationImage(this.book.getImgUrl());
        indicationText(this.book.getName());
    }

    public void indicationImage(String imageUrl) {
        Image newImage = new Image(imageUrl);
        bookImage.setImage(newImage);
    }

    public void indicationText(String text) {
        bookName.setText(text);
    }
}
