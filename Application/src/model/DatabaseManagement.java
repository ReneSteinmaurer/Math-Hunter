package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class DatabaseManagement {
    private Connection con;
    private ResultSet rs;

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
            System.out.println("Connected to db...");
        }
    }

    public int getPointsFromUser(String playerName) throws SQLException {
        int points;
        try (PreparedStatement ps = con.prepareStatement(
                "select points from players where name = ?")) {
            ps.setString(1,playerName);

            rs = ps.executeQuery();
            rs.next();
            points = rs.getInt(1);
        }

        return points;
    }

    public void setPointsFromUser(String playerName, int points) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "update players set points = ? where name = ?")) {
            ps.setInt(1,points);
            ps.setString(2,playerName);

            ps.execute();
        }
    }

    public HashMap<String, User> getAllUsers() throws SQLException {
        HashMap<String, User> userMap = new HashMap<>();
        try (PreparedStatement ps = con.prepareStatement(
                "select * from user_player")){
            rs = ps.executeQuery();

            while (rs.next()) {
                userMap.put(rs.getString(2), new User(rs.getString(3),false,10));
            }
        }
        return userMap;
    }

    public void addUser(String name, String pwd, String playerName) throws SQLException {
        if (!name.isEmpty() && !pwd.isEmpty() && !playerName.isEmpty()) {
            try (PreparedStatement ps2 = con.prepareStatement(
                    "call addUser(?,?,?)")) {
                try (PreparedStatement ps = con.prepareStatement(
                        "select count(*) from users where name = ?")) {
                    ps.setString(1, name);
                    rs = ps.executeQuery();
                    rs.next();

                    if (rs.getInt(1) >= 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Dieser User ist bereits vorhanden!", ButtonType.OK);
                        alert.showAndWait();
                    } else {
                        ps2.setString(1, name);
                        ps2.setString(2, pwd);
                        ps2.setString(3, playerName);
                        ps2.executeQuery();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "User hinzugefügt!", ButtonType.OK);
                        alert.showAndWait();
                    }
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bitte alle Felder ausfüllen!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public boolean userLogin(String name, String pwd) throws SQLException {
        boolean loggedIn = false;
        try (PreparedStatement ps = con.prepareStatement(
                "select * from users where name = ?")) {

            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.printf("%s, %s \n",
                        rs.getString(1),
                        rs.getString(2));
                if (pwd.equals(rs.getString(2))) {
                    loggedIn = true;
                    System.out.println("eingeloggt!");
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Username oder Passwort stimmen nicht überein!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }
        return loggedIn;
    }

    public void addVocab(String germanWord, String englishWord, String difficulty) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("call addVocab (?,?,?)")){
            ps.setString(1, germanWord);
            ps.setString(2, englishWord);
            ps.setString(3, difficulty);

            ps.execute();
        }
    }

    public HashMap<Integer, Vocabulary> readVocabTable() throws SQLException {
        int rowCount = 0;
        HashMap<Integer, Vocabulary> vocabMap = new HashMap<>();
        try(PreparedStatement preparedStatement=con.prepareStatement("Select * from vocabs")){
            rs=preparedStatement.executeQuery();

            while (rs.next()){
                Vocabulary vocabulary = new Vocabulary(rs.getString(2),rs.getString(3),rs.getString(4));
                vocabMap.put(rowCount, vocabulary);
                rowCount++;
            }
        }

        return vocabMap;
    }

    public HashMap<Integer, Calculations> readCalcTable(){
        int rows = 0;
        HashMap<Integer, Calculations> calcMap = new HashMap<>();
        try(PreparedStatement preparedStatement=con.prepareStatement("Select * from calculations")){
            rs=preparedStatement.executeQuery();

            while (rs.next()){
                Calculations calculation= new Calculations(rs.getString(2), rs.getInt(3));
                calcMap.put(rows, calculation);
                rows++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return calcMap;

    }


    public Connection getCon() {
        return con;
    }
}
