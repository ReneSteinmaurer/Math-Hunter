package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private String driver;
    private String url;
    private String name;
    private String pwd;
    private boolean loggedIn = false;

    @FXML private TextField inputName;
    @FXML private Label labelName;
    @FXML private Label labelPasw;
    @FXML private TextField inputPasw;
    @FXML private Button buttonLogin;

    private void userLogin(String url, String name, String pwd) throws SQLException {
        Connection con;
        Statement stmt;
        ResultSet rs;
        String sql;

        con = DriverManager.getConnection(url, name, pwd);
        stmt = con.createStatement();
        sql = "select * from users where name = '"+ inputName.getText()+"'";
        rs = stmt.executeQuery(sql);

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
            Alert alert = new Alert(Alert.AlertType.ERROR,"Username oder Passwort stimmen nicht überein!", ButtonType.APPLY);
            alert.showAndWait();
        }
    }

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        Properties prop = new Properties();

        try (FileInputStream in = new FileInputStream("dbconnect.properties")) {

            prop.load(in);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            name = prop.getProperty("name");
            pwd = prop.getProperty("password");
        }
        userLogin(url,name,pwd);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonLogin.disableProperty().bind(inputName.textProperty().isEmpty());
        buttonLogin.disableProperty().bind(inputPasw.textProperty().isEmpty());

    }
}
