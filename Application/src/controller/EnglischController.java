package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nextButton.disableProperty().bind(translationField.textProperty().isEmpty());
        pointField.textProperty().bind(points.asString());
    }

    public void fillStartValue() throws SQLException, IOException {
        if (firstStarted){
            firstStarted=false;
            dbm = new DatabaseManagement();
            vocabMap= dbm.readVocabTable();
        }
        random = r.nextInt(vocabMap.size());
        wordField.setText(String.valueOf(vocabMap.get(random).getGermanWord()));
    }
}
