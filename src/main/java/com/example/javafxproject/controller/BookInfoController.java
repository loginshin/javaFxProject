package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BookInfoController implements Initializable {

    //책 데이터 dto 받아오기
    Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
