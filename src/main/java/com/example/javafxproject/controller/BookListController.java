package com.example.javafxproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookListController implements Initializable {

    @FXML
    private ImageView goMainBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    //home버튼 눌러 홈으로 돌아가기
    @FXML
    public void goMainBtnFunc(){
        System.out.println("goMainBtnFunc를 실행합니다.");

        Stage newStage = new Stage();
        MainMenuController controller;

        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/mainMenuPage.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) goMainBtn.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
