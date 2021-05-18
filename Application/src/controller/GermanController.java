package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import model.Main;

import java.io.IOException;

public class GermanController {
    private Main model;

    @FXML
    private AnchorPane germanPane;

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        model.loadApplication();
    }

    public void setModel(Main model) {
        this.model = model;
    }
}

