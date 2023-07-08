package com.example.gestionaleTesina.classes;

import com.example.gestionaleTesina.controllers.DBConnection;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

    public class SwitchButton extends Label
    {
        private SimpleBooleanProperty switchedOn= new SimpleBooleanProperty(false);
        public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
        private String travelName;
        DBConnection database;

        public void setTravelName(String travelName) {
            this.travelName = travelName;
        }

        public SwitchButton(String groupID, String travelName, boolean status, DBConnection database)
        {
            /*FirstPageController controller= new FirstPageController();
            controller.initializeForSwitchButton();*/
            //database.initializeConnection();
            this.travelName=travelName;
            this.database=database;

            Button switchBtn = new Button();
            switchBtn.setPrefWidth(25);
            switchBtn.setPrefHeight(10);
            setGraphic(switchBtn);
            switchBtn.setStyle("-fx-background-radius:20px; -fx-border-radius:20px; -fx-border-color: #d62090; -fx-background-color: #FAE5F6;");

            switchedOn.set(status);
            if (status) {
                setText("  Done");
                setStyle("-fx-background-color: #C6D8FF;-fx-text-fill:blue; -fx-font-size: 10px; -fx-text-alignment:CENTER; -fx-background-radius:20px; -fx-border-radius:20px; -fx-border-color: #d62090;");
                setContentDisplay(ContentDisplay.RIGHT);
            }
            else {
                setText("To Do  ");
                setStyle("-fx-background-color: #e7ebff; -fx-text-fill:blue; -fx-font-size: 10px; -fx-text-alignment:CENTER; -fx-background-radius:20px; -fx-border-radius:20px; -fx-border-color: #d62090;");
                setContentDisplay(ContentDisplay.LEFT);
            }

            switchBtn.setOnAction(h -> {
                switchedOn.set(!switchedOn.get());
                database.updateTravelStatus(groupID, this.travelName, switchedOn.get());
            });

            switchedOn.addListener((ov, t, t1) -> {
                if (t1) {
                    setText("  Done");
                    setStyle("-fx-background-color: #C6D8FF;-fx-text-fill:blue; -fx-font-size: 10px; -fx-text-alignment:CENTER; -fx-background-radius:20px; -fx-border-radius:20px; -fx-border-color: #d62090;");
                    setContentDisplay(ContentDisplay.RIGHT);
                }
                else {
                    setText("To Do  ");
                    setStyle("-fx-background-color:#e7ebff; -fx-text-fill:blue; -fx-font-size: 10px; -fx-text-alignment:CENTER; -fx-background-radius:20px; -fx-border-radius:20px; -fx-border-color: #d62090;");
                    setContentDisplay(ContentDisplay.LEFT);
                }
            });
        }
    }

