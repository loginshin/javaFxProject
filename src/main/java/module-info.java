module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.javafxproject to javafx.fxml;
    exports com.example.javafxproject;
    exports com.example.javafxproject.controller;
    opens com.example.javafxproject.controller to javafx.fxml;
    exports com.example.javafxproject.controller.admin;
    opens com.example.javafxproject.controller.admin to javafx.fxml;
    exports com.example.javafxproject.controller.auth;
    opens com.example.javafxproject.controller.auth to javafx.fxml;
}