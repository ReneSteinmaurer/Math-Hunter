package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Main;

import java.io.IOException;

public class AddVocabController {
    private Main model;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label addLabel;

    @FXML
    private TextField germanField;

    @FXML
    private TextField englishField;

    @FXML
    private Button addButton;

    @FXML
    private Label germanLabel;

    @FXML
    private Label englishLabel;


    @FXML
    void addWord(ActionEvent event) {
        try {
            model.loadApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setModel(Main model) {
        this.model = model;
    }
}
