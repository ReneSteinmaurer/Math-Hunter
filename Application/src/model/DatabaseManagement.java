package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseManagement {
    private Connection con;
    private ResultSet rs;
    private Statement stmt;

    public DatabaseManagement() throws IOException, SQLException {
        Properties prop = new Properties();
        String url;
        String name;
        String pwd;

        try (FileInputStream in = new FileInputStream("Application/dbconnect.properties")) {
            prop.load(in);
            url = prop.getProperty("url");
            name = prop.getProperty("name");
            pwd = prop.getProperty("password");
            con = DriverManager.getConnection(url, name, pwd);
            stmt = con.createStatement();
            System.out.println("Connected to db...");
        }
    }

    public void addUser(String name, String pwd, String playerName) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "call addUser(?,?,?)")) {

            ps.setString(1, name);
            ps.setString(2, pwd);
            ps.setString(3, playerName);
            rs = ps.executeQuery();
        }
    }

    public boolean userLogin(String name, String pwd) throws SQLException {
        boolean loggedIn = false;
        try (PreparedStatement ps = con.prepareStatement(
                "select * from users where name = ?")) {

            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.printf("%d, %s, %s \n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                if (pwd.equals(rs.getString(3))) {
                    loggedIn = true;
                    System.out.println("eingeloggt!");
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Username oder Passwort stimmen nicht überein!", ButtonType.OK);
                alert.showAndWait();
            }
        }
        return loggedIn;
    }

    public Connection getCon() {
        return con;
    }
}
