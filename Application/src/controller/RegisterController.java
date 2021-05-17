package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.DatabaseManagement;
import model.Main;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    private Main model;
    private DatabaseManagement dbm;

    @FXML private TextField usernameField;
    @FXML private PasswordField pwdField;
    @FXML private PasswordField pwdField2;
    @FXML private TextField nicknameField;
    @FXML private Button buttonRegister;

    @FXML
    void register(ActionEvent event) throws SQLException, IOException {
        if (pwdField.getText().equals(pwdField2.getText())) {
            dbm.addUser(usernameField.getText(),pwdField.getText(),nicknameField.getText());
            model.loadLogin();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Passwörter müssen überein stimmen!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void setModel(Main main) {
        model = main;
    }

    public void setDbm(DatabaseManagement dbm) {
        this.dbm = dbm;
    }
}