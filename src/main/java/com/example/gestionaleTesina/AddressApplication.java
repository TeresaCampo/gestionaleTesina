package com.example.gestionaleTesina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


public class AddressApplication extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Scene scene = new Scene(root);

        stg= primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
        //changeScene("editPage-view.fxml");
    }

    public FXMLLoader changeScene(String fxml) throws IOException{
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource(fxml)));
        Scene scene = new Scene(loader.load());
        stg.setScene(scene);
        return loader;
    }

    public static void main(String[] args) {
        launch();
    }
}