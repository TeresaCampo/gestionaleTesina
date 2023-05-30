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
    }

    public void changeScene(String fxml) throws IOException{
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource(fxml)));
        Parent pane= loader.load();
        stg.getScene().setRoot(pane);

        if(fxml.equals("signUp-view.fxml")) {
            System.out.println("SignUpController loaded and initialized");
            SignUpController signUpController = loader.getController();
            signUpController.initialize();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}