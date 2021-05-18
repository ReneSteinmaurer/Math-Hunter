package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.DatabaseManagement;
import model.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddVocabController implements Initializable {
    private Main model;
    private DatabaseManagement dbm;
    private boolean firstStarted = true;

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
    private TextField difficultyField;


    @FXML
    void addWord(ActionEvent event) throws SQLException, IOException {
        if (difficultyField.getText().equals("1") || difficultyField.getText().equals("2")) {
            dbm.addVocab(germanField.getText(), englishField.getText(),difficultyField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Vocabel hinzugefügt!", ButtonType.OK);
            alert.showAndWait();
            germanField.clear();
            englishField.clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Difficulty kann nicht: "+difficultyField.getText()+" sein!", ButtonType.OK);
            alert.showAndWait();
        }
        difficultyField.clear();
    }

    public void setModel(Main model) {
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.disableProperty().bind(germanField.textProperty().isEmpty().or(englishField.textProperty().isEmpty().or(difficultyField.textProperty().isEmpty())));

    }

    public void setDbm(DatabaseManagement dbm) {
        this.dbm = dbm;
    }
}
