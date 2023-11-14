module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxproject to javafx.fxml;
    exports com.example.javafxproject;
    exports com.example.javafxproject.controller;
    opens com.example.javafxproject.controller to javafx.fxml;
}