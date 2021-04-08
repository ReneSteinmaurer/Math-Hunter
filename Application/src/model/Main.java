package model;

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

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        loadLogin();
    }

    public void loadLogin() throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setTitle("Hello World");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("../view/login.css");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
