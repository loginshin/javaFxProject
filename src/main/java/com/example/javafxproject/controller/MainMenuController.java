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
    private BookService bookService;
    private BookListController controller;

    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn1.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                //클릭시 실행할 이벤트
                System.out.println("bookListPage로 이동합니다");
                changePage("/com/example/javafxproject/fxml/bookListPage.fxml");
            }
        });

    }


    private void changePage(String fxml) {
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            controller=loader.getController(); // 새로운 컨트롤러에 BookService 객체 전달
            System.out.println("send data => "+bookService);

            // 현재 Stage 가져오기
            Stage currentStage = (Stage) btn1.getScene().getWindow();

            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}



