package com.example.javafxproject;

import com.example.javafxproject.dto.Book;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //javaFX View
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/javafxproject/fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setResizable(false); // 창 크기 변경 불가능하게 설정
        stage.setTitle("Kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}