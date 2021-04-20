package model;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;
    private LoginController loginController = new LoginController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        loadLogin();
    }

    public void loadLogin() throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setTitle("Login");
        Scene scene = new Scene(root);
        loginController.setModel(this);
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void loadApplication() throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setTitle("Application");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
