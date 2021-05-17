package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.DatabaseManagement;
import model.Main;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class SelectionController {
    private Main model;
    private int points;
    private DatabaseManagement dbm;
    private HashMap<String, User> userMap = new HashMap<>();

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
    void startEnglischGame(ActionEvent event) throws IOException {
        model.loadEnglischWindow();

    }

    @FXML
    void startGermanGame(ActionEvent event) throws IOException {
        model.loadGermanWindow();

    }

    @FXML
    void startMathGame(ActionEvent event) throws IOException {
        model.loadMathWindows();

    }

    public void setModel(Main model) {
        this.model = model;
    }

    public void setUserMap(HashMap<String, User> userMap) throws SQLException, IOException {
        this.userMap = userMap;
    }

    public void setAmountOfPoints() throws SQLException, IOException {
        for (User value : userMap.values()) {
            if (value.isLoggedIn()) {
                points = (dbm.getPointsFromUser(value.getNickname()));
                if (points < 10) dbm.setPointsFromUser(value.getNickname(),10);
                else amountOfPoints.setText(String.valueOf(points));
            }
        }
    }

    public void setDbm(DatabaseManagement dbm) throws SQLException, IOException {
        this.dbm = dbm;
    }
}

