package model;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Main extends Application {
    private Stage stage;
    private FXMLLoader loader;
    private Parent root;
    private Scene scene;
    private DatabaseManagement dbm;
    private LoginController loginController = new LoginController();
    private RegisterController registerController = new RegisterController();
    private SelectionController selectionController = new SelectionController();
    private MathController mathController= new MathController();
    private GermanController germanController = new GermanController();
    private EnglischController englischController = new EnglischController();
    private HashMap<String, User> userMap = new HashMap<>();
    GameOverController gameOverController= new GameOverController();
    AddVocabController addVocabController= new AddVocabController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        dbm = new DatabaseManagement();
        userMap = dbm.getAllUsers();
        for (User value : userMap.values()) {
            System.out.println("Nickname: " +value.getNickname() +", Points: "+ value.getPoints()+"\n");
        }
        loadLogin();
    }

    public void loadLogin() throws IOException {
        try {
            stage.close();
            stage = new Stage();
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/login.fxml"));
            root = loader.load();
            stage.setTitle("Login");
            scene = new Scene(root);
            userMap = dbm.getAllUsers();
            loginController = loader.getController();
            loginController.setModel(this);
            loginController.setDbm(dbm);
            loginController.setUserMap(userMap);
            scene.getStylesheets().add("../view/login.css");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (SQLException throwables) {
            System.out.println("Error while loading DB..");
            throwables.printStackTrace();
        }
    }

    public void loadApplication() throws IOException {
        try {
            stage.close();
            stage = new Stage();
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/selectionStage.fxml"));
            root = loader.load();
            stage.setTitle("Selection");
            scene = new Scene(root);
            selectionController = loader.getController();
            selectionController.setModel(this);
            selectionController.setDbm(dbm);
            selectionController.setUserMap(userMap);
            selectionController.setAmountOfPoints();
            scene.getStylesheets().add("../view/login.css");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadRegister() throws IOException {
        try {
            stage.close();
            stage = new Stage();
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/register.fxml"));
            root = loader.load();
            stage.setTitle("Register");
            scene = new Scene(root);
            registerController = loader.getController();
            registerController.setModel(this);
            registerController.setDbm(dbm);
            userMap = dbm.getAllUsers();
            scene.getStylesheets().add("../view/login.css");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void loadEnglischWindow() throws IOException {
        try {
            stage.close();
            stage = new Stage();
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/englischScene.fxml"));
            root = loader.load();
            stage.setTitle("Englisch");
            scene = new Scene(root);
            englischController = loader.getController();
            englischController.setDbm(dbm);
            englischController.setUserMap(userMap);
            englischController.fillStartValue();
            englischController.setModel(this);
            scene.getStylesheets().add("../view/login.css");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadGermanWindow() throws IOException {
        stage.close();
        stage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/germanScene.fxml"));
        root = loader.load();
        stage.setTitle("German");
        scene = new Scene(root);
        germanController = loader.getController();
        germanController.setModel(this);
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

    }

    public void loadMathWindows() throws IOException {
        stage.close();
        stage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/mathScene.fxml"));
        root = loader.load();
        stage.setTitle("Math");
        scene = new Scene(root);
        mathController = loader.getController();
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void loadAddWindow() throws IOException {
        stage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/addWindow.fxml"));
        root = loader.load();
        stage.setTitle("AddWindow");
        scene = new Scene(root);
        addVocabController = loader.getController();
        addVocabController.setModel(this);
        addVocabController.setDbm(dbm);
        scene.getStylesheets().add("../view/login.css");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void loadGameOverWindow(){
        try {
            stage.close();
            stage = new Stage();
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/gameOver.fxml"));
            root = loader.load();
            stage.setTitle("Game Over!");
            scene = new Scene(root);
            gameOverController = loader.getController();
            gameOverController.setModel(this);
            scene.getStylesheets().add("../view/login.css");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
