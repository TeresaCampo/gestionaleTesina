package com.example.gestionaleTesina.classes;

import com.example.gestionaleTesina.FirstPageController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

    public class SwitchButton extends Label
    {
        private SimpleBooleanProperty switchedOn= new SimpleBooleanProperty(false);
        public SimpleBooleanProperty switchOnProperty() { return switchedOn; }


        public SwitchButton(Group group, Travel travel)
        {
            FirstPageController controller= new FirstPageController();
            controller.initializeForSwitchButton();

            Button switchBtn = new Button();
            switchBtn.setPrefWidth(15);
            switchBtn.setPrefHeight(15);
            setGraphic(switchBtn);

            switchedOn.set(travel.getStatus());
            if (travel.getStatus()) {
                setText("DONE");
                setStyle("-fx-background-color: green;-fx-text-fill:white;");
                setContentDisplay(ContentDisplay.RIGHT);
            }
            else {
                setText("TO DO");
                setStyle("-fx-background-color: grey;-fx-text-fill:black;");
                setContentDisplay(ContentDisplay.LEFT);
            }

            switchBtn.setOnAction(h -> {
                switchedOn.set(!switchedOn.get());
                travel.setStatus(switchedOn.get());
                controller.updateTravelStatus(group.getGroupID(), travel.getName(), travel.getStatus());
            });

            switchedOn.addListener((ov, t, t1) -> {
                if (t1) {
                    setText("DONE");
                    setStyle("-fx-background-color: green;-fx-text-fill:white;");
                    setContentDisplay(ContentDisplay.RIGHT);
                }
                else {
                    setText("TO DO");
                    setStyle("-fx-background-color: grey;-fx-text-fill:black;");
                    setContentDisplay(ContentDisplay.LEFT);
                }
            });
        }
    }

