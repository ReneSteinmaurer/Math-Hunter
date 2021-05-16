package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DatabaseManagement;
import model.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddVocabController implements Initializable {
    private Main model;
    private DatabaseManagement databaseManagement;

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
    void addWord(ActionEvent event) throws SQLException, IOException {
        try {
            databaseManagement= new DatabaseManagement();
            databaseManagement.addVocab(germanField.getText(), englishField.getText());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            model.loadApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setModel(Main model) {
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.disableProperty().bind(germanField.textProperty().isEmpty().or(englishField.textProperty().isEmpty()));

    }
}
