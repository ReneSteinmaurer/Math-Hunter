package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Main;

import java.io.IOException;

public class SelectionController {
    private Main model;


    @FXML
    private AnchorPane selectionPane;

    @FXML
    private Label selectionLabel;

    @FXML
    private Label pointLabel;

    @FXML
    private Label amountOfPoints;

    @FXML
    private Button deutschButton;

    @FXML
    private Button deutschButton1;

    @FXML
    private Button deutschButton11;

    @FXML
    void startEnglischGame(ActionEvent event) {
        try {
            model.loadEnglischWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void startGermanGame(ActionEvent event) {
        try {
            model.loadGermanWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void startMathGame(ActionEvent event) {
        try {
            model.loadMathWindows();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setModel(Main model) {
        this.model = model;
    }
}

