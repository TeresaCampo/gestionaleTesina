package com.example.gestionaleTesina;


import com.example.gestionaleTesina.classes.OptionComponentsGraphic;
import com.example.gestionaleTesina.classes.TravelOption;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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


    public void initialize(){
        plusButtonList.add(plusButton);
        plusButton.setOnAction(h->onPlusButton(plusButton));
    }

    /**
     * to create new components and display them
     *
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

    void onLessButton(OptionComponentsGraphic toBeDeleted, Button lessButton, Button plusButton){
        System.out.println("provo a cancellare");
        background.getChildren().removeIf(el-> (el.getLayoutY()>=lessButton.getLayoutY()) && el.getLayoutY()<=plusButton.getLayoutY());
        background.getChildren().stream().
                forEach(el-> {
                    if(el.getLayoutY()>=toBeDeleted.getTf_price().getLayoutY()) {
                        el.setLayoutY(el.getLayoutY()-200);
                    }
                });
        plusButtonList.remove(plusButton);  lessButtonList.remove(lessButton);
        componentsLIst.remove(toBeDeleted);
    }
    void addComponent(Button plusButton){
        background.getChildren().stream().
                forEach(el-> {
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

        //test stamp
        System.out.println(components.getSelectionModel().getSelectedItems());
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

    void addAccommodation(Button plusButton){
        OptionComponentsGraphic componentAccommodation= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getHeight()+10), background, "accommodation");
        componentsLIst.add(plusButtonList.indexOf(plusButton),componentAccommodation);
        System.out.println("component index "+componentsLIst.indexOf(componentAccommodation));

        background.getChildren().remove(components);
    }

    void addRental(Button plusButton){
        OptionComponentsGraphic componentRental= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getHeight()+10), background, "rental");
        componentsLIst.add(plusButtonList.indexOf(plusButton),componentRental);
        System.out.println("componentindex "+componentsLIst.indexOf(componentRental));
        background.getChildren().remove(components);
    }

    void addTransport(Button plusButton){
        OptionComponentsGraphic componentTransport= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getHeight()+10), background, "transport");
        componentsLIst.add(plusButtonList.indexOf(plusButton),componentTransport);
        System.out.println("component index "+componentsLIst.indexOf(componentTransport));
        background.getChildren().remove(components);
    }

}
