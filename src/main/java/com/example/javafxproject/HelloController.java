package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
        changePage("AnimalsPage.fxml");
    }

    @FXML
    private void goToObjectsPage() {
        changePage("ObjectsPage.fxml");
    }

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