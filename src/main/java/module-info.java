module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    exports org.example.application.controller;
    opens org.example.application.controller to javafx.fxml;
    exports org.example.application.view;
    opens org.example.application.view to javafx.fxml;
}