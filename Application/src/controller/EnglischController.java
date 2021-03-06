package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DatabaseManagement;
import model.Main;
import model.User;
import model.Vocabulary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class EnglischController implements Initializable {
    private Main model;
    private IntegerProperty points = new SimpleIntegerProperty();
    private int random;
    private HashMap<Integer, Vocabulary> vocabMap;
    private HashMap<String, User> userMap = new HashMap<>();
    private DatabaseManagement dbm;
    private String nickname;
    private String difficulty;
    private boolean firstStarted;
    private boolean addedVocabs = false;
    private Random r = new Random();

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
    @FXML
    private Label difficutlyLabel;


    public void setModel(Main model) {
        this.model = model;
    }

    @FXML
    void addVocabulary(ActionEvent event) throws IOException {
        model.loadAddWindow();
        addedVocabs = true;
    }

    @FXML
    void next(ActionEvent event) throws SQLException {
        if (addedVocabs) {
            vocabMap = dbm.readVocabTable();
            addedVocabs = false;
        }
        if (vocabMap.get(random).getEnglishWord().equals(translationField.getText())) {
            translationField.clear();
            points.setValue(points.getValue() + 1);
            System.out.println("Korrekt!");
        } else {
            translationField.clear();
            if (points.getValue() >= 2) {
                points.setValue(points.getValue() - 1);
            } else {
                model.loadGameOverWindow();
            }
        }

        random = r.nextInt(vocabMap.size());
        if (points.getValue() < 30) {
            while (vocabMap.get(random).getDifficulty().equals("2")) {
                random = r.nextInt(vocabMap.size());
            }
            difficulty = "1";
        } else {
            while (vocabMap.get(random).getDifficulty().equals("1")) {
                random = r.nextInt(vocabMap.size());
            }
            difficulty = "2";
        }

        difficutlyLabel.setText("Difficulty: "+difficulty);
        wordField.setText(String.valueOf(vocabMap.get(random).getGermanWord()));
        dbm.setPointsFromUser(nickname, points.getValue());
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

    public void fillStartValue() throws SQLException {
        vocabMap = dbm.readVocabTable();
        for (User value : userMap.values()) {
            if (value.isLoggedIn()) {
                nickname = value.getNickname();
                points.setValue(dbm.getPointsFromUser(value.getNickname()));
            }
        }
        if (points.getValue() < 30) difficulty = "1";
        else if (points.getValue() >= 2) difficulty = "2";
        difficutlyLabel.setText("Difficulty: "+difficulty);
        random = r.nextInt(vocabMap.size());
        wordField.setText(String.valueOf(vocabMap.get(random).getGermanWord()));
    }

    public void setUserMap(HashMap<String, User> userMap) throws SQLException, IOException {
        this.userMap = userMap;
    }

    public void setDbm(DatabaseManagement dbm) {
        this.dbm = dbm;
    }
}
