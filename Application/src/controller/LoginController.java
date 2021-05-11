package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.DatabaseManagement;
import model.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Main main;
    private DatabaseManagement dbm;
    private boolean loggedIn = false;
    private Statement stmt;
    private ResultSet rs;

    @FXML private TextField inputName;
    @FXML private Label labelName;
    @FXML private Label labelPasw;
    @FXML private TextField inputPasw;
    @FXML private Button buttonLogin;

    private void userLogin() throws SQLException {

        try (PreparedStatement ps = dbm.getCon().prepareStatement(
                "select * from users where name = ?")){

            ps.setString(1,inputName.getText());

            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.printf("%d, %s, %s \n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                if (inputPasw.getText().equals(rs.getString(3))) {
                    loggedIn = true;
                    System.out.println("eingeloggt!");
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Username oder Passwort stimmen nicht überein!", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        dbm = new DatabaseManagement();
        userLogin();
        if (loggedIn) main.loadApplication();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonLogin.disableProperty().bind(inputName.textProperty().isEmpty().or(inputPasw.textProperty().isEmpty()));

    }

    public void setModel(Main main) {
        this.main = main;
    }
}
