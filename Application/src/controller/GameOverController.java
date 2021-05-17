package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Main;

import java.io.IOException;

public class GameOverController {
    private Main model;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label gameOverLabel;

    @FXML
    private Button gameOverButton;

    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {
        model.loadApplication();
    }


    public void setModel(Main model) {
        this.model = model;
    }
}
