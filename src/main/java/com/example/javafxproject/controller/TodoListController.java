package com.example.javafxproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ListCell;

public class TodoListController {
    @FXML
    private ListView<TodoItem> todoListView;  // 할 일 목록을 표시하는 ListView
    @FXML
    private TextField todoInput;  // 새 할 일을 입력하는 TextField

    public void initialize() {
        // ListView의 각 항목에 CheckBox를 포함하도록 설정
        todoListView.setCellFactory(param -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(TodoItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getText());
                    setGraphic(checkBox);
                }
            }
        });
    }

    // 할 일을 추가하는 메서드
    @FXML
    private void addTodoItem() {
        String todoItemText = todoInput.getText();
        if (!todoItemText.isEmpty()) {
            TodoItem todoItem = new TodoItem(todoItemText);
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

    // TodoItem 클래스 정의 (체크 리스트의 각 항목)
    public static class TodoItem {
        private final String text;

        public TodoItem(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
