package org.example.application.controller;

import javafx.scene.control.Alert;

public class Alerts {

    public static void show(String title, String content, Alert.AlertType typeAlert){
        Alert alert = new Alert(typeAlert);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
