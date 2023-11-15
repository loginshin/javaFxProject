package com.example.javafxproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    //버튼 클릭시 화면 이동 func
//    @FXML
//    protected void onHelloButtonClick() {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("objectQuiz.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    private void goToAnimalsPage() {
        changePage("GptPage.fxml");
    }


    //fxml을 Stage에 등록한다.
    @FXML
    private void goToGptPage() {
        changePage("/com/example/javafxproject/fxml/GptPage.fxml");
    }

    @FXML
    private void goToTodoList() {
        changePage("/com/example/javafxproject/fxml/TodoList.fxml");
    }

    //페이지 바꾸는 메서드
    private void changePage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}