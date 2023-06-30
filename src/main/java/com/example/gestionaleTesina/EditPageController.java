package com.example.gestionaleTesina;

import com.example.gestionaleTesina.classes.OptionComponentsGraphic;
import com.example.gestionaleTesina.classes.TravelOption;
import com.example.gestionaleTesina.classes.TravelOptionComponent;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.util.*;
import java.util.Date;


public class EditPageController {

    @FXML
    private AnchorPane background;
    @FXML
    private TextField tf_optionName;
    @FXML
    private Button firstPlusButton;
    private Button plusButtonJustClicked;
    TreeSet<Button> lessButtonList=new TreeSet<>(Comparator.comparing((Button b)-> b.getLayoutY()));
    TreeSet<Button> plusButtonList=new TreeSet<>(Comparator.comparing((Button b)-> b.getLayoutY()));
    TreeSet<OptionComponentsGraphic> componentsList=new TreeSet<>(Comparator.comparing((OptionComponentsGraphic c)-> c.getTf_name().getLayoutY()));

    AddressApplication main= new AddressApplication();
    DBConnection database = new DBConnection();
    String groupID;
    Integer numberOfMemebers;
    //String travelName;
    TravelOption travelOption;
    ListView<String> componentsListView;


    /**
     * Display existing components
     */
    public void initialize(){
        database.initializeConnection();
        //initialize plusButtonList
        plusButtonList.add(firstPlusButton);
        firstPlusButton.setOnAction(h->onPlusButton(firstPlusButton));    firstPlusButton.setPrefWidth(26);
        //initialize components
        componentsListView= new ListView<>();
        componentsListView.getItems().add("accommodation");
        componentsListView.getItems().add("transport");
        componentsListView.getItems().add("rental");
        componentsListView.getItems().add("hide listview");
        componentsListView.setMaxHeight(90);    componentsListView.setLayoutX(firstPlusButton.getLayoutX()+firstPlusButton.getPrefWidth()+5);
        componentsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[hide listview]"))  background.getChildren().remove(componentsListView);
            else addComponent(plusButtonJustClicked);});


        //to test
        numberOfMemebers=3;
        travelOption.setGroupID("speriam");
        travelOption.setTravelName("speriamDavvero");
        travelOption=new TravelOption("test", 20, null);
        travelOption.getComponents().add(new TravelOptionComponent("accommodation", "a", "agosto", "test", 2, "aleks", 100.0, "hotel Sirena", new Date(2023, Calendar.JULY, 1), null, null, null, null, true ));
        travelOption.getComponents().add(new TravelOptionComponent("rental", "a", "agosto", "test", 1, "aleks", 100.0, "kayak", null, null, null, new Time(16, 45, 00), 7, true ));
        //end test

        for (TravelOptionComponent c : travelOption.getComponents()) {
            plusButtonJustClicked=plusButtonList.last();
            if (c.getComponentName().get().equals("transport")) {
                addTransport(plusButtonJustClicked).initializeTransport(c);
            }
            if (c.getComponentName().get().equals("rental")) {
                addRental(plusButtonJustClicked).initializeRental(c);
            }
            if (c.getComponentName().get().equals("accommodation")) {
                addAccommodation(plusButtonJustClicked).initializeAccommodation(c);
            }
            createButtons(plusButtonJustClicked);
        }
    }

    /**
     * Plus button action:
     * displays a list of component and create the new component if the user selects it
     */
    void onPlusButton(Button plusButton){
        plusButtonJustClicked=plusButton;
        componentsListView.setLayoutY(plusButtonJustClicked.getLayoutY());
        if(!background.getChildren().contains(componentsListView))  background.getChildren().add(componentsListView);
    }

    /**
     * LessButton action:
     * deletes graphic components of the OptionComponentGraphic
     * deletes the OptionComponentGraphic, plusButton and lessButton from the LinkedLIst*
     *
     * @param lessButton at the beginning of the OptionComponentGraphic to be deleted
     */
    void onLessButton(Button lessButton){
        //System.out.println("DEBUG LESSBUTTON-> lessButton prima "+lessButtonList.toString()+"\nplusButton prima "+plusButtonList.toString()+"\ncomponents prima"+componentsList.toString());
        //remove elements from layout
        background.getChildren().removeIf(el-> (el.getLayoutY()>=lessButton.getLayoutY()) && el.getLayoutY()<(lessButton.getLayoutY())+200);

        //remove elements from lists
        Button buttonToBeRemoved=plusButtonList.stream()
                .filter(b-> (b.getLayoutY()>=lessButton.getLayoutY() && b.getLayoutY()<(lessButton.getLayoutY())+200))
                .findFirst().get();
        plusButtonList.remove(buttonToBeRemoved);
        buttonToBeRemoved=lessButtonList.stream()
                .filter(b-> (b.getLayoutY()>=lessButton.getLayoutY() && b.getLayoutY()<=(lessButton.getLayoutY())+200))
                .findFirst().get();
        lessButtonList.remove(buttonToBeRemoved);
        OptionComponentsGraphic componentToBeRemoved= componentsList.stream()
                .filter(b-> (b.getTf_name().getLayoutY()>=lessButton.getLayoutY() && b.getLayoutY()<=(lessButton.getLayoutY())+200))
                .findFirst().get();
        componentsList.remove(componentToBeRemoved);
        System.out.println("DEBUG LESSBUTTON-> lessButton dopo "+lessButtonList.toString()+"\nplusButton dopo "+plusButtonList.toString()+"\ncomponents dopo"+componentsList.toString());

        //translate elements below
        background.getChildren().forEach(el-> {
                    if(el.getLayoutY()>=lessButton.getLayoutY()) {
                        el.setLayoutY(el.getLayoutY()-200);
                    }
        });
    }
    /**
     * To create the following plusButton
     * To create the actual lessButton
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
        nextLessButton.setOnAction(h-> onLessButton(nextLessButton));

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
                    if(el.getLayoutY()>plusButton.getLayoutY()) {
                        el.setLayoutY(el.getLayoutY()+200);
                    }
        });
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[accommodation]")){
            addAccommodation(plusButton);
        }
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[rental]")){
            addRental(plusButton);
        }
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[transport]")){
            addTransport(plusButton);
        }
        createButtons(plusButton);
        componentsListView.getSelectionModel().select("hide listview");
    }

    OptionComponentsGraphic addAccommodation(Button plusButton){
        OptionComponentsGraphic componentAccommodation= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getPrefHeight()+10), background, "accommodation");
        componentsList.add(componentAccommodation);
        return componentAccommodation;
    }
    OptionComponentsGraphic addRental(Button plusButton){
        OptionComponentsGraphic componentRental= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getPrefHeight()+10), background, "rental");
        componentsList.add(componentRental);
        return componentRental;
    }
    OptionComponentsGraphic addTransport(Button plusButton){
        OptionComponentsGraphic componentTransport= new OptionComponentsGraphic(((int) plusButton.getLayoutX()), (int) (plusButton.getLayoutY()+plusButton.getPrefHeight()+10), background, "transport");
        componentsList.add(componentTransport);
        return componentTransport;
    }

    @FXML
    TravelOption onSaveButton() {
        /*
        1) check if the option name has been changed
        yes-> check if already present and in this case ask a newOne
            set it as new option name in accommodation, rental and transport in (3)
        no-> continue

        2)create-> TreeSet<TravelOptionComponent> components= new TreeSet<>(Comparator.comparing((TravelOptionComponent e) -> e.getPosInTravelOption().get()));
        3)add all the graphic elements
          in the same time update the database
        4) calculate perPerson cost, totalCost
        set OptionName right in travelOption and latter information
         */

        //check if the optionName has been changed-> in case check if it is already present in the database
        /*
        if (!tf_optionName.getText().equals(travelOption.getName())) {
            try {
                if (checkOptionNameExists(groupID, travelName, tf_optionName.getText())) {
                    new Alert(Alert.AlertType.ERROR, "This option name is already used for this travel, choose a new one").showAndWait();
                    return null;
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
            }
        }


        //delete the existing info about this option in the database Accommodation, Rental, Transport
        try{
            database.deleteTravelOption(groupID, travelName, travelOption.getName());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\n previous option configuration is lost").showAndWait();
            System.out.println("FATAL ERROR, PREVIOUS OPTION CONFIGURATION IS LOST");
        }
         */

        //creation of new TreeSet<TravelOptionComponent> components to return to MetaPage
        //meanwhile storing data in the database
        TreeSet<TravelOptionComponent> components = new TreeSet<>(Comparator.comparing((TravelOptionComponent e) -> e.getPosInTravelOption().get()));
        Integer tmpPosInTravelOption = 1;
        System.out.println("DEBUG SAVE-> componentsList "+componentsList.toString());

        for (OptionComponentsGraphic c : componentsList) {
            TravelOptionComponent newComponent = null;
            System.out.println(c.getLb_kindOfComponent().getText());
            if ("Accommodation".equals(c.getLb_kindOfComponent().getText())) {
                newComponent = c.convertAccommodation(travelOption.getGroupID(), travelOption.getTravelName(), tf_optionName.getText(), tmpPosInTravelOption);
                try {
                    database.storeAccommodation(newComponent, groupID, travelOption.getTravelName(), tf_optionName.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing Accommodation").showAndWait();
                }
            }
            if ("Rental".equals(c.getLb_kindOfComponent().getText())) {
                newComponent = c.convertRental(groupID, travelOption.getTravelName(), tf_optionName.getText(), tmpPosInTravelOption);
                try {
                    database.storeRental(newComponent, groupID, travelOption.getTravelName(), tf_optionName.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing Rental").showAndWait();
                }
            }
            if ("Transport".equals(c.getLb_kindOfComponent().getText())) {
                newComponent = c.convertTransport(groupID, travelOption.getTravelName(), tf_optionName.getText(), tmpPosInTravelOption);
                try {
                    database.storeTransport(newComponent, groupID, travelOption.getTravelName(), tf_optionName.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing Transport").showAndWait();
                }
            }
            ++tmpPosInTravelOption;
            components.add(newComponent);
        }

        //update travelOption Table
        travelOption.setOptionName(tf_optionName.getText());
        travelOption.setComponents(components);
        travelOption.setTotalCost(
                components.stream()
                        .mapToDouble(el -> el.getPrice().orElse(0.0))
                        .sum()
        );
        travelOption.setPerPersonCost(travelOption.getTotalCost()/numberOfMemebers);
        try {
            database.storeTravelOption(groupID, travelOption.getTravelName(), travelOption);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing TravelOption").showAndWait();
        }
        /*
        //comeback to the previous page
        try {
            main.changeScene("metaPage-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("META-PAGE NOT FOUND");
        }
         */


        return travelOption;
    }




    private boolean checkOptionNameExists(String groupID, String travelName, String optionName) throws SQLException {
        try (Connection connection = database.dataSource.getConnection();
             PreparedStatement checkData = connection.prepareStatement("SELECT * FROM traveloptions WHERE groupID = ? AND travelName = ? AND optionName = ?")) {
            checkData.setString(1, groupID);
            checkData.setString(2, travelName);
            checkData.setString(3, optionName);

            ResultSet optionFound = checkData.executeQuery();
            if (optionFound.isBeforeFirst()) {
                return true;
            }
            return false;
        }
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

