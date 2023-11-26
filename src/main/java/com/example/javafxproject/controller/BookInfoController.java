package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BookInfoController implements Initializable {

    //책 데이터 dto 받아오기
    Book book;
    @FXML
    private ImageView closeBtn;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookInfo;

    public void setBook(Book book) {
        this.book = book;
        indicationImage(this.book.getImgUrl());
        indicationText(this.book.getInfo());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //이미지 데이터가 있으면 이미지 가져와서 바꾸기

    }

    //이미지 최신화
    public void indicationImage(String imagUrl){
        Image newImage = new Image(imagUrl);
        bookImage.setImage(newImage);
    }

    public void indicationText(String text){
        bookInfo.setText(text);
    }

    @FXML
    public void closeFunc(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

}
