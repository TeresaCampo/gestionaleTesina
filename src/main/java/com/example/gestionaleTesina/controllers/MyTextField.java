package com.example.gestionaleTesina.controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MyTextField {
    public static void maxLen20(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 20) {
                tf.setStyle("-fx-background-color: #e7ebff; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
                return change;
            } else {
                tf.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
                return null;
            }
        }));
    }

    public static void maxLen450(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 450) {
                tf.setStyle("-fx-background-color: #e7ebff; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
                return change;
            } else {
                tf.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
                return null;
            }
        }));
    }

    public static void maxLen45(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 45) {
                tf.setStyle("-fx-background-color: #e7ebff; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
                return change;
            } else {
                tf.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
                return null;
            }
        }));
    }

    public static void maxLen20(PasswordField pf) {
        pf.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 20)
                return change;
            else {
                pf.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
                pf.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
                return null;
            }
        }));
    }
}
