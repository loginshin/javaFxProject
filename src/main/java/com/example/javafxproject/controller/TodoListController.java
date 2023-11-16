package com.example.javafxproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class TodoListController {

    @FXML
    private ListView<TodoItem> todoListView;  // 할 일 목록을 표시하는 ListView
    @FXML
    private TextField todoInput;  // 새 할 일을 입력하는 TextField


    //실행 시 작동 메서드
    public void initialize() {
        System.out.println("come todoListController in initialize method");
        // ListView의 각 항목에 checkbox를 포함하도록 설정
        todoListView.setCellFactory( new TodoListCellFactory());
        System.out.println("last last todoListController in initialize method");
    }
    @FXML
    private void addTodoItem() {
        System.out.println("start addToDoItem method");
        String todoItemText = todoInput.getText();
        System.out.println(todoItemText);
        if (!todoItemText.isEmpty()) {
            System.out.println("addTodoItem => 가져온 텍스트 값 : " + todoItemText);
            TodoItem todoItem = new TodoItem(todoItemText);
            todoListView.getItems().add(todoItem);
            todoInput.clear();  // 입력 필드 초기화
        }
    }

    //ToDoList All Remove
    @FXML
    private void clearAllItems() {
        todoListView.getItems().clear();
    }


    // TodoItem class 객체
    public static class TodoItem {
        private final String text;
        private boolean completed;

        public TodoItem(String text) {
            this.text = text;
            this.completed = false;
        }

        public String getText() {
            return text;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }


    //TodoItem을 위한 사용자 정의 ListCell 클래스
    public class TodoListCellFactory implements Callback<ListView<TodoItem>, ListCell<TodoItem>> {

        @Override
        public ListCell<TodoListController.TodoItem> call(ListView<TodoListController.TodoItem> param) {
            return new ListCell<TodoListController.TodoItem>() {
                private final CheckBox checkBox = new CheckBox();

                //뷰를 구성하는 곳 updateItem
                @Override
                protected void updateItem(TodoListController.TodoItem item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Label label = new Label(item.getText());
//                        setText(item.getText());
//                        checkBox.setText(item.getText());
                        Button deleteButton = new Button("del");
                        deleteButton.setOnAction(event -> {
                            getListView().getItems().remove(item);
                        });

                        HBox hBox = new HBox(); //레이아웃 생성
                        hBox.getChildren().addAll(checkBox, label,  deleteButton); //체크박스와 삭제버튼 추가 !!!!

                        setGraphic(hBox); // 그래픽을 HBox로 설정

                    }
                }
            };
        }

    }
}


