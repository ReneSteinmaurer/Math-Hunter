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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class MathController implements Initializable {
    private Main model;
    private IntegerProperty points = new SimpleIntegerProperty();
    private int random;
    private HashMap<Integer, Calculations> calcMap;
    private HashMap<String, User> userMap = new HashMap<>();
    private DatabaseManagement dbm;
    private String nickname;
    private boolean firstStarted;
    private Random r = new Random();

    @FXML
    private AnchorPane mathPane;

    @FXML
    private Label mathLabel;

    @FXML
    private Button quitButton;

    @FXML
    private Button nextButton;

    @FXML
    private TextField calcField;

    @FXML
    private TextField resultField;

    @FXML
    private Label calcLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField pointField;

    @FXML
    private Label pointLabel;

    @FXML
    void next(ActionEvent event) throws SQLException {
        calcMap=dbm.readCalcTable();

        if (String.valueOf(calcMap.get(random).getResult()).equals(resultField.getText())) {
            resultField.clear();
            points.setValue(points.getValue() + 1);
            System.out.println("Korrekt!");
        } else {
            resultField.clear();
            if (points.getValue() >= 2) {
                points.setValue(points.getValue() - 1);
            } else {
                model.loadGameOverWindow();
            }
        }

        random = r.nextInt(calcMap.size());


        calcField.setText(String.valueOf(calcMap.get(random).getCalculation()));
        dbm.setPointsFromUser(nickname, points.getValue());




    }

    @FXML
    void quit(ActionEvent event) throws IOException {
        model.loadApplication();

    }

    public void setModel(Main model) {
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nextButton.disableProperty().bind(resultField.textProperty().isEmpty());
        pointField.textProperty().bind(points.asString());
    }

    public void fillStartValue() throws SQLException {
        calcMap = dbm.readCalcTable();
        for (User value : userMap.values()) {
            if (value.isLoggedIn()) {
                nickname = value.getNickname();
                points.setValue(dbm.getPointsFromUser(value.getNickname()));
            }
        }

        random = r.nextInt(calcMap.size());
        calcField.setText(String.valueOf(calcMap.get(random).getCalculation()));
    }

    public void setUserMap(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }

    public void setDbm(DatabaseManagement dbm) {
        this.dbm = dbm;
    }
}
