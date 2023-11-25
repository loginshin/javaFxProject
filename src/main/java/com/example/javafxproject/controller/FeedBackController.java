package com.example.javafxproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FeedBackController implements Initializable {

    @FXML
    private TextField newItemTextField; // ToDo 아이템을 입력하는 TextField


    @FXML
    private ScrollPane feedBackContainer;

    @FXML
    private ScrollPane balloonScrollPane;

    @FXML
    private VBox balloonContainer;

    @FXML
    private ImageView goMainBtn; // 홈으로 돌아가는 버튼


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 초기화 코드 (필요에 따라 추가)

    }

    // "추가" 버튼 클릭 시 실행되는 메서드
    @FXML
    public void addItem() {
        // 1. 텍스트 필드에 있는 값을 가지고 온다.
        String getText = newItemTextField.getText();
        System.out.println(getText);

        // 2. Label을 포함한 VBox를 생성하고 스타일을 지정한다.
        Label titleLabel = new Label(getText);
        titleLabel.setStyle("-fx-alignment: CENTER;");
        VBox newContent = new VBox(titleLabel);
        newContent.setPadding(new Insets(10)); // 패딩값 설정
        newContent.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY))); // 배경색 설정

        // 3. 이전 내용을 포함한 VBox를 가져온다.
        VBox existingContent = (VBox) feedBackContainer.getContent();


        // 4. 새로운 내용을 위로 추가한 후, setContent로 설정한다.
        // null 일때 값을 그냥 집어 넣는다.
        if(existingContent ==null){
            feedBackContainer.setContent(newContent);

            // 5. 필드 초기화
            newItemTextField.clear();
        } else {
            existingContent.getChildren().add(0, newContent);
            feedBackContainer.setContent(existingContent);

            // 5. 필드 초기화
            newItemTextField.clear();
        }



    }



    // home 버튼 눌러 홈으로 돌아가기
    @FXML
    public void goMainBtnFunc() {
        System.out.println("goMainBtnFunc를 실행합니다.");

        Stage currentStage = (Stage) goMainBtn.getScene().getWindow();
        // 새로운 Scene 생성 후 현재 Stage에 설정
        Scene scene = new Scene(createParent());
        currentStage.setScene(scene);
    }

    // 새로운 Scene을 생성하는 메서드
    private Parent createParent() {
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/mainMenuPage.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
