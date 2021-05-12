package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.DatabaseManagement;
import model.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Main model;
    private DatabaseManagement dbm;
    private boolean loggedIn = false;
    private boolean startedOnce = true;
    private Statement stmt;
    private ResultSet rs;

    @FXML private TextField inputName;
    @FXML private Label labelName;
    @FXML private Label labelPasw;
    @FXML private PasswordField pwdField;
    @FXML private Button buttonLogin;
    @FXML private Button buttonRegister;

    @FXML
    void register(ActionEvent event) throws SQLException, IOException {
        model.loadRegister();
    }

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        if (startedOnce) {
            startedOnce = false;
            dbm = new DatabaseManagement();
        }
        loggedIn = dbm.userLogin(inputName.getText(),pwdField.getText());
        if (loggedIn) model.loadApplication();
        else {
            inputName.clear();
            pwdField.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonLogin.disableProperty().bind(inputName.textProperty().isEmpty().or(pwdField.textProperty().isEmpty()));

    }

    public void setModel(Main main) {
        model = main;
    }
}
