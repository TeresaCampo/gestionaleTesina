package com.example.gestionaleTesina.classes;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class OptionComponentsGraphic extends Node {
    int layoutX;
    int layoutY;
    javafx.scene.control.Label lb_kindOfComponent;
    javafx.scene.control.TextField tf_name;
    Label lb_from;

    /**
     * accommodation not initialised constructor
     */

    public OptionComponentsGraphic(int layoutX, int layoutY, AnchorPane background) {
        this.layoutX = layoutX;
        this.layoutY = layoutY;

        lb_kindOfComponent=new javafx.scene.control.Label("Accommodation");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(182);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Name of the accommodation/url");
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);
        tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);
        lb_from=new Label("from");
        lb_from.setLayoutX(tf_name.getLayoutX());
        lb_from.setLayoutY(tf_name.getLayoutY()-25);
        background.getChildren().add(lb_from);
    }

}
