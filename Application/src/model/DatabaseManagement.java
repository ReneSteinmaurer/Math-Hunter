package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManagement {
    private Connection con;

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

    public Connection getCon() {
        return con;
    }

    public void connectDB() throws IOException {

    }
}
