package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.example.javafxproject.dto.*;

public class HelloController implements Initializable {

    public HelloController(){

    }

    public List<Book> dtoBox(){



        return null;
    }






    @FXML
    private Button btn;


    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

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
    private void goToMainPage() {
        changePage("/com/example/javafxproject/fxml/mainMenuPage.fxml");
    }

    //페이지 바꾸는 메서드
    private void changePage(String fxml) {
        Stage newStage = new Stage();
        Stage stage = (Stage)btn.getScene().getWindow();


        try {


            // 새로운 루트 로드
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            newStage.setScene(new Scene(root));
            newStage.show();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}