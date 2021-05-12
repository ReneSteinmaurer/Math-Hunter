package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DatabaseManagement;
import model.Main;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    private Main model;
    private DatabaseManagement dbm;
    private boolean startedOnce = true;

    @FXML private TextField usernameField;
    @FXML private PasswordField pwdField;
    @FXML private TextField nicknameField;
    @FXML private Button buttonRegister;

    @FXML
    void register(ActionEvent event) throws SQLException, IOException {
        if (startedOnce) {
            startedOnce = false;
            dbm = new DatabaseManagement();
        }
        dbm.addUser(usernameField.getText(),pwdField.getText(),nicknameField.getText());
    }

    public void setModel(Main main) {
        model = main;
    }
}