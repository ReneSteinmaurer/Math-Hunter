package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Main;

import java.io.IOException;

public class EnglischController {
    private Main model;
    private Integer points;

    @FXML
    private AnchorPane englischPane;

    @FXML
    private Label vocabularyLabel;

    @FXML
    private Button quitButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField wordField;

    @FXML
    private TextField translationField;

    @FXML
    private Label wordLabel;

    @FXML
    private Label translationLabel;

    @FXML
    private TextField pointField;

    @FXML
    private Label pointLabel;

    public void setModel(Main model) {
        this.model = model;
    }

    @FXML
    void addVocabulary(ActionEvent event) throws IOException {
        model.loadAddWindow();
    }

    @FXML
    void next(ActionEvent event) {

    }

    @FXML
    void quit(ActionEvent event) throws IOException {
        model.loadApplication();

    }

}
