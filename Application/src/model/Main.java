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

public class Main extends Application {
    private Stage stage;
    private FXMLLoader loader;
    private Parent root;
    private Scene scene;
    private LoginController loginController = new LoginController();
    private RegisterController registerController = new RegisterController();
    private SelectionController selectionController = new SelectionController();
    private MathController mathController= new MathController();
    private GermanController germanController = new GermanController();
    private EnglischController englischController = new EnglischController();
    AddVocabController addVocabController= new AddVocabController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        loadLogin();
    }

    public void loadLogin() throws IOException {
        stage.close();
        stage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/login.fxml"));
        root = loader.load();
        stage.setTitle("Login");
        scene = new Scene(root);
        loginController = loader.getController();
        loginController.setModel(this);
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void loadApplication() throws IOException {
        stage.close();
        stage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/selectionStage.fxml"));
        root = loader.load();
        stage.setTitle("Selection");
        scene = new Scene(root);
        selectionController = loader.getController();
        selectionController.setModel(this);
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void loadRegister() throws IOException {
        stage.close();
        stage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/register.fxml"));
        root = loader.load();
        stage.setTitle("Register");
        scene = new Scene(root);
        registerController = loader.getController();
        registerController.setModel(this);
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }


    public void loadEnglischWindow() throws IOException {
        stage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/englischScene.fxml"));
        root = loader.load();
        stage.setTitle("Englisch");
        scene = new Scene(root);
        englischController = loader.getController();
        try {
            englischController.fillStartValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        englischController.setModel(this);
        scene.getStylesheets().add("../view/login.css");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void loadGermanWindow() throws IOException {
        stage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/germanScene.fxml"));
        root = loader.load();
        stage.setTitle("German");
        scene = new Scene(root);
        germanController = loader.getController();
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

    }

    public void loadMathWindows() throws IOException {
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
        scene.getStylesheets().add("../view/login.css");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
