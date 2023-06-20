package com.example.gestionaleTesina;


import com.example.gestionaleTesina.classes.OptionComponentsGraphic;
import com.example.gestionaleTesina.classes.TravelOption;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class EditPageController {

    @FXML
    private AnchorPane background;
    @FXML
    private TextField tf_optionName;
    private Integer componentNumber;
    @FXML
    private Button plusButton;


    TravelOption travelOption;
    ListView<String> components;

    /*
    void initialize(){
        componentNumber=travelOption.components.size();
        for (TravelOptionComponent component : travelOption.components) {

        }
    }
     */

    void addAccommodation(){
        OptionComponentsGraphic componentAccommodation= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), ((int) plusButton.getLayoutY()), background);
        background.getChildren().remove(components);
    }

    @FXML
    void onPlusButton(){
        components= new ListView<>();
        components.getItems().add("accommodation");
        components.getItems().add("transport");
        components.getItems().add("rental");
        components.setLayoutX(plusButton.getLayoutX()+plusButton.getWidth()+5);
        components.setLayoutY(plusButton.getLayoutY());
        components.setMaxHeight(90);
        components.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> addComponent());

        background.getChildren().add(components);
    }
    void addComponent(){
        if(components.getSelectionModel().getSelectedItems().toString().equals("[accommodation]")){
            addAccommodation();
        }
        System.out.println(components.getSelectionModel().getSelectedItems());
    }

}
