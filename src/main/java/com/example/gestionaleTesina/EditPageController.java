package com.example.gestionaleTesina;


import com.example.gestionaleTesina.classes.OptionComponentsGraphic;
import com.example.gestionaleTesina.classes.TravelOption;
import com.example.gestionaleTesina.classes.TravelOptionComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class EditPageController {

    @FXML
    private AnchorPane background;
    @FXML
    private TextField tf_optionName;
    private Integer componentNumber;
    @FXML
    private Button plusButton;
    LinkedList<Button> lessButtonList= new LinkedList<>();
    LinkedList<Button> plusButtonList= new LinkedList<>();
    LinkedList<OptionComponentsGraphic> componentsLIst= new LinkedList<>();
    TravelOption travelOption;
    ListView<String> components;


    /**
     * to initialize an existing travel
     */
    public void initialize(){
        plusButtonList.add(plusButton);
        plusButton.setOnAction(h->onPlusButton(plusButton));    plusButton.setPrefHeight(26);
        //to test
        travelOption=new TravelOption("test", 13, 4, null);
        travelOption.getComponents().add(new TravelOptionComponent("accommodation", "a", "agosto", "test", 2, "aleks", 100.0, "hotel Sirena", new Date(2023, Calendar.JULY, 1), null, null, null, null, true ));
        travelOption.getComponents().add(new TravelOptionComponent("rental", "a", "agosto", "test", 1, "aleks", 100.0, "kayak", null, null, null, new Time(16, 45, 00), 7, true ));
        //end test

        int componentNumber=0;
        for (TravelOptionComponent c : travelOption.getComponents()) {
            System.out.println(c.getComponentName().get());
            if (c.getComponentName().get().equals("transport")) {
                addTransport(plusButtonList.get(componentNumber)).initializeTransport(c);
            }
            if (c.getComponentName().get().equals("rental")) {
                addRental(plusButtonList.get(componentNumber)).initializeRental(c);
            }
            if (c.getComponentName().get().equals("accommodation")) {
                addAccommodation(plusButtonList.get(componentNumber)).initializeAccommodation(c);
            }
            createButtons(plusButtonList.get(componentNumber));
            ++componentNumber;
        }
    }

    /**
     * Plus button action:
     * displays a list of component and create the new component if the user selects it
     */
    void onPlusButton(Button plusButton){
        components= new ListView<>();
        components.getItems().add("accommodation");
        components.getItems().add("transport");
        components.getItems().add("rental");
        components.getItems().add("hide listview");

        components.setLayoutX(plusButton.getLayoutX()+plusButton.getWidth()+5);
        components.setLayoutY(plusButton.getLayoutY());
        components.setMaxHeight(90);
        components.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(components.getSelectionModel().getSelectedItems().toString().equals("[hide listview]"))  background.getChildren().remove(components);
            else addComponent(plusButton);});
        background.getChildren().add(components);
    }

    /**
     * LessButton action:
     * deletes graphic components of the OptionComponentGraphic
     * deletes the OptionComponentGraphic, plusButton and lessButton from the LinkedLIst*
     *
     * @param toBeDeleted OptionComponentGraphic to be deleted
     * @param lessButton at the beginning of the OptionComponentGraphic to be deleted
     * @param plusButton at the end of the OptionComponentGraphic to be deleted
     */
    void onLessButton(OptionComponentsGraphic toBeDeleted, Button lessButton, Button plusButton){
        background.getChildren().removeIf(el-> (el.getLayoutY()>=lessButton.getLayoutY()) && el.getLayoutY()<=plusButton.getLayoutY());
        background.getChildren().forEach(el-> {
                    if(el.getLayoutY()>=toBeDeleted.getTf_price().getLayoutY()) {
                        el.setLayoutY(el.getLayoutY()-200);
                    }
        });
        plusButtonList.remove(plusButton);  lessButtonList.remove(lessButton);
        componentsLIst.remove(toBeDeleted);
    }

    /**
     * To add a new OptionComponentGraphic
     * creates a default layout of the corresponding TravelOptionComponent
     * calls createButtons to create the corresponding plusButton and lessButton
     *
     * @param plusButton that has been clicked
     */
    void addComponent(Button plusButton){
        background.getChildren().forEach(el-> {
                    if(el.getLayoutY()>plusButton.getLayoutY()) el.setTranslateY(200);
        });
        if(components.getSelectionModel().getSelectedItems().toString().equals("[accommodation]")){
            addAccommodation(plusButton);
        }
        if(components.getSelectionModel().getSelectedItems().toString().equals("[rental]")){
            addRental(plusButton);
        }
        if(components.getSelectionModel().getSelectedItems().toString().equals("[transport]")){
            addTransport(plusButton);
        }
        createButtons(plusButton);
    }

    OptionComponentsGraphic addAccommodation(Button plusButton){
        OptionComponentsGraphic componentAccommodation= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getPrefHeight()+10), background, "accommodation");
        componentsLIst.add(plusButtonList.indexOf(plusButton),componentAccommodation);
        System.out.println("DEBUG-component index "+componentsLIst.indexOf(componentAccommodation));
        background.getChildren().remove(components);
        return componentAccommodation;
    }
    OptionComponentsGraphic addRental(Button plusButton){
        OptionComponentsGraphic componentRental= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getPrefHeight()+10), background, "rental");
        componentsLIst.add(plusButtonList.indexOf(plusButton),componentRental);
        System.out.println("DEBUG-componentindex "+componentsLIst.indexOf(componentRental));
        background.getChildren().remove(components);
        return componentRental;
    }
    OptionComponentsGraphic addTransport(Button plusButton){
        OptionComponentsGraphic componentTransport= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getPrefHeight()+10), background, "transport");
        componentsLIst.add(plusButtonList.indexOf(plusButton),componentTransport);
        System.out.println("DEBUG-component index "+componentsLIst.indexOf(componentTransport));
        background.getChildren().remove(components);
        return componentTransport;
    }

    /**
     * To create the plusButton to the create a following OptionComponentGraphic
     * To create the lessButton to delete the actual OptionComponentGraphic
     * @param plusButton that has been clicked
     */
    void createButtons(Button plusButton){
        Button nextPlusButton=new Button("+");  nextPlusButton.setPrefHeight(26);    nextPlusButton.setPrefWidth(26);
        nextPlusButton.setLayoutX(plusButton.getLayoutX()); nextPlusButton.setLayoutY(plusButton.getLayoutY()+200);
        plusButtonList.add(nextPlusButton); background.getChildren().add(nextPlusButton);
        nextPlusButton.setOnAction(h-> onPlusButton(nextPlusButton));

        Button nextLessButton=new Button("-");  nextLessButton.setPrefHeight(26);    nextLessButton.setPrefWidth(26);
        nextLessButton.setLayoutX(plusButton.getLayoutX()+plusButton.getPrefWidth()+10); nextLessButton.setLayoutY(plusButton.getLayoutY()+1);
        lessButtonList.add(nextLessButton); background.getChildren().add(nextLessButton);
        //test stamp
        System.out.println("less button index "+lessButtonList.indexOf(nextLessButton));
        nextLessButton.setOnAction(h-> onLessButton(componentsLIst.get(lessButtonList.indexOf(nextLessButton)), nextLessButton, nextPlusButton));

    }

    /**
     * getter and setter
     *
     */
    public TravelOption getTravelOption() {
        return travelOption;
    }
    public void setTravelOption(TravelOption travelOption) {
        this.travelOption = travelOption;
    }
}
