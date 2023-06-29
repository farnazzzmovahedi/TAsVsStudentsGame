package com.example.tasvsstudents;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    Image ds = new Image("F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\sfdd.png");

    @FXML
    void exitButtonClick(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void playButtonClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playButton.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void saveButtonClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("saveButton.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}