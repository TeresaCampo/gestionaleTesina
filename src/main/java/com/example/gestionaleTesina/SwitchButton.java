package com.example.gestionaleTesina;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

            switchedOn.set(travel.status);
            if (travel.status) {
                setText("DONE");
                setStyle("-fx-background-color: green;-fx-text-fill:white;");
                setContentDisplay(ContentDisplay.RIGHT);
            }
            else {
                setText("TO DO");
                setStyle("-fx-background-color: grey;-fx-text-fill:black;");
                setContentDisplay(ContentDisplay.LEFT);
            }

            switchBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t)
                {
                    switchedOn.set(!switchedOn.get());
                    travel.status=switchedOn.get();
                    controller.updateTravelStatus(group.groupID, travel.name, travel.status);
                }
            });

            switchedOn.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1)
                {
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
                }
            });
        }
    }

