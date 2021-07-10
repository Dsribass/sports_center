module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    exports org.example.application.controller;
    opens org.example.application.controller to javafx.fxml;
    exports org.example.application.view;
    opens org.example.application.view to javafx.fxml;

    opens org.example.domain.entities.client to javafx.base;
    opens org.example.domain.entities.sports_court to javafx.base;
    opens org.example.domain.entities.sports_court.soccer_court to javafx.base;
    opens org.example.domain.entities.sports_court.volleyball_court to javafx.base;
    opens org.example.domain.entities.sports_court_rental to javafx.base;
}