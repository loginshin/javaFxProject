package com.example.javafxproject.controller;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.example.javafxproject.service.BookService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private ImageView btn1;

    @FXML
    private ImageView btn2;

    @FXML
    private ImageView btn3;

    @FXML
    private ImageView btn4;

    @FXML
    private ImageView btn5;


    private BookService bookService;


    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    private void btn1ClickEvent() {
        System.out.println("btn1 click event");
        System.out.println("bookListPage로 이동합니다");

        Stage newStage = new Stage();
        //BookListController controller;

        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/bookListPage.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btn1.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    // btn 2 클릭시
    @FXML
    private void btn2ClickEvent() {
        System.out.println("btn2 click event");
        System.out.println("feedbackPage 이동합니다");
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/feedBackPage.fxml"));
            Parent root = loader.load();

            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) btn2.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // btn 3 클릭시
    @FXML
    private void btn3ClickEvent() {
        System.out.println("btn3 click event");
        System.out.println("phoneAuthPage 이동합니다");
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/admin/phoneAuthPage.fxml"));
            Parent root = loader.load();

            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) btn3.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // btn 4 클릭시
    @FXML
    private void btn4ClickEvent() {
        System.out.println("btn4 click event");
        System.out.println("phoneAuthPage로 이동합니다");
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/admin/phoneAuthPage.fxml"));
            Parent root = loader.load();

            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) btn4.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // btn 2 클릭시
    @FXML
    private void btn5ClickEvent() {
        System.out.println("btn5 click event");
        System.out.println("adminAuthPage로 이동합니다");
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/admin/adminAuthPage.fxml"));
            Parent root = loader.load();

            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) btn5.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


