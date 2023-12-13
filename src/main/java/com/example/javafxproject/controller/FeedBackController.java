package com.example.javafxproject.controller;

import com.example.javafxproject.dto.FeedBack;
import com.example.javafxproject.service.FeedBackService;
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
import java.util.List;
import java.util.ResourceBundle;

import static com.example.javafxproject.controller.HelloController.feedBackService;

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
        // 리스트에 피드백 추가 // => 리스팅 (리스트에 추가하는 작업) => View 리스트들을 전부 보여주는작업
        listing();

    }

    // "추가" 버튼 클릭 시 실행되는 메서드 => FeedBack 추가하기
    @FXML
    public void addFeedBack(){
        // 1. 텍스트 필드에 있는 값을 가지고 온다. 2. 피드백 데이터에List 추가
        String getText = newItemTextField.getText();
        System.out.println(getText);
        FeedBack feedBack = new FeedBack();
        feedBack.setFeedBack(getText); //적은것
        feedBack.setSolve(false); //미해결로 새로추가
        feedBackService.addFeedBack(feedBack);
        listing();
    }

    //FeedBackList FeedBack List정리하기 =>
    public void listing(){

        //VBox를 만들어서 scrllpane에 추가
        VBox content = new VBox();
        content.setSpacing(30);
        feedBackContainer.setContent(content);

        //만약 feedback리스트가 있다면 화면에 미리 띄우기
        List<FeedBack> feedBacks = feedBackService.getFeedBackList();
        System.out.println(feedBacks.size()!=0);
        if(feedBacks.size()!=0) {
            for (int i = 0; i < feedBacks.size(); i++) {
                String getText = feedBacks.get(i).getFeedBack();
                System.out.println(getText);
                feedBackView(getText,content);

            }

        }

    }

    //FeedBack View 보여주는곳 fxml
    public void feedBackView(String getText, VBox content) {

        // 2. Label을 포함한 VBox를 생성하고 스타일을 지정한다.
        Label titleLabel = new Label(getText);
        titleLabel.setStyle("-fx-alignment: CENTER;");
        VBox newContent = new VBox(titleLabel);
        newContent.setPadding(new Insets(10)); // 패딩값 설정
        newContent.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,new CornerRadii(10.0) , Insets.EMPTY))); // 배경색 설정
        content.getChildren().add(0, newContent);
        newItemTextField.clear(); //텍스트 필드 비우기

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
