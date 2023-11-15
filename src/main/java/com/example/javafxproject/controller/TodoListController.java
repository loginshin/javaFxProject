package com.example.javafxproject.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TodoListController extends Application {
    @FXML
    private ListView<String> todoListView;  // 할 일 목록을 표시하는 ListView
    @FXML
    private TextField todoInput;  // 새 할 일을 입력하는 TextField

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("투 두 리스트에영");

        // To-Do List View
        todoListView = new ListView<>();

        // Input Field
        todoInput = new TextField();

        // Add Button
        Button addButton = new Button("추가할게영");
        addButton.setOnAction(e -> addTodoItem());

        // Delete Button
        Button deleteButton = new Button("삭제할게영");
        deleteButton.setOnAction(e -> deleteTodoItem());

        // Clear All Button
        Button clearAllButton = new Button("전체 삭제할게영");
        clearAllButton.setOnAction(e -> clearAllItems());

        // Layout
        VBox layout = new VBox(20);  // 수직으로 정렬된 컨테이너 VBox 생성
        layout.setPadding(new Insets(20));  // 여백 설정
        layout.getChildren().addAll(todoListView, todoInput, addButton, deleteButton, clearAllButton);

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    // 할 일을 추가하는 메서드
    @FXML
    private void addTodoItem() {
        String todoItem = todoInput.getText();
        if (!todoItem.isEmpty()) {
            todoListView.getItems().add(todoItem);
            todoInput.clear();  // 입력 필드 초기화
        }
    }

    // 선택한 할 일을 삭제하는 메서드
    @FXML
    private void deleteTodoItem() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            todoListView.getItems().remove(selectedIndex);
        }
    }
    // 전체 할 일을 삭제하는 메서드
    @FXML
    private void clearAllItems() {
        todoListView.getItems().clear();
    }
}
