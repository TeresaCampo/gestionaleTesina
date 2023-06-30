package com.example.gestionaleTesina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
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
        stg.initStyle(StageStyle.UNDECORATED);
        stg.setScene(scene);
        stg.show();
        centreWindow(stg);
    }

    /**
     * Center stage in the display.
     * @param stg stage with the scene already set
     */
    public static void centreWindow(Stage stg) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stg.setX((primScreenBounds.getWidth() - stg.getWidth()) / 2);
        stg.setY((primScreenBounds.getHeight() - stg.getHeight()) / 2);
    }

    /**
     * Change scene: set the new one in place of the previous one.
     * @param fxml scene to be set
     * @return loader of the scene
     * @throws IOException if fxml is not found
     */
    public FXMLLoader changeScene(String fxml) throws IOException{
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource(fxml)));
        Scene scene = new Scene(loader.load());
        stg.setScene(scene);
        centreWindow(stg);

        return loader;
    }

    public static void main(String[] args) {
        launch();
    }
}