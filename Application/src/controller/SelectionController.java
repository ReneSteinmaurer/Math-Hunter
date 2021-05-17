package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.DatabaseManagement;
import model.Main;

import java.io.IOException;
import java.sql.SQLException;

public class SelectionController {
    private Main model;
    private boolean firstStarted = true;
    private DatabaseManagement dbm;


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

    public void setAmountOfPoints(String playername) throws SQLException, IOException {
        if (firstStarted) {
            firstStarted = false;
            dbm = new DatabaseManagement();
            pointLabel.setText(String.valueOf(dbm.getPointsFromUser(playername)));
        }
    }
}

