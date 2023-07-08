package com.example.gestionaleTesina.controllers;

import com.example.gestionaleTesina.AddressApplication;
import com.example.gestionaleTesina.classes.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.util.*;

public class EditPageController {
    @FXML
    private AnchorPane background;
    @FXML
    private TextField tf_optionName;
    @FXML
    private Button firstPlusButton;
    private Button plusButtonJustClicked;
    private Button lessButtonJustClicked;

    private final TreeSet<Button> lessButtonList=new TreeSet<>(Comparator.comparing((Button b)-> b.getLayoutY()));
    private final TreeSet<Button> plusButtonList=new TreeSet<>(Comparator.comparing((Button b)-> b.getLayoutY()));
    private final TreeSet<TravelOptionComponent> componentsList=new TreeSet<>(Comparator.comparing((TravelOptionComponent c)-> c.getTf_name().getLayoutY()));
    private final AddressApplication main= new AddressApplication();
    private DBConnection database;
    private TravelOption travelOption;
    private Travel travel;
    private Group group;
    private ListView<String> componentsListView;


    /**
     * Initialize database, plusButtonList, firstPlusButton
     * Create componentsListView to display choices for the new component to be created
     * Display actual components
     */
    public void initialize(){
        //initialize plusButtonList
        plusButtonList.add(firstPlusButton);
        firstPlusButton.setOnAction(h->{
            plusButtonJustClicked=firstPlusButton;
            onPlusButton();
        });
        firstPlusButton.setPrefWidth(25);
    }

    /**
     * Create a new listView to choose next component
     * @param layoutY Y coordinates to display the listView
     */
    void createListView(double layoutY){
        componentsListView= new ListView<>();
        componentsListView.getItems().add("Accommodation");
        componentsListView.getItems().add("Transport");
        componentsListView.getItems().add("Rental");
        componentsListView.getItems().add("Hide Listview");
        componentsListView.setMaxHeight(90);
        componentsListView.setLayoutX(firstPlusButton.getLayoutX()+firstPlusButton.getPrefWidth()+5);   componentsListView.setLayoutY(layoutY);
        componentsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            background.getChildren().remove(componentsListView);
            if(!componentsListView.getSelectionModel().getSelectedItems().toString().equals("[Hide Listview]"))     addComponent();
            });
    }

    /**
     * Display travelOption's travelOptionComponents.
     */
    void loadData(){
        //initialize tf_optionName
        tf_optionName.setText(travelOption.getOptionName());
        //initialize existing components
        for (TravelOptionComponent c : travelOption.getComponents()) {
            plusButtonJustClicked=plusButtonList.last();
            c.addInitializedGraphicComponent(((int) plusButtonJustClicked.getLayoutX()), (int) (plusButtonJustClicked.getLayoutY()+plusButtonJustClicked.getPrefHeight()+10), background);
            componentsList.add(c);
            createButtons();
        }
        System.out.println("Data loaded successfully!\nYou're ready to edit");
    }

    /**
     * Display listView components.
     */
    void onPlusButton(){
        background.getChildren().remove(componentsListView);
        createListView(plusButtonJustClicked.getLayoutY());
        background.getChildren().add(componentsListView);
    }

    /**
     * Remove the selected OptionComponentGraphic
     */
    void onLessButton(){
        //remove elements from layout
        background.getChildren().removeIf(el-> (el.getLayoutY()>=lessButtonJustClicked.getLayoutY()) && el.getLayoutY()<(lessButtonJustClicked.getLayoutY())+200);

        //remove elements from lists
        Button buttonToBeRemoved=plusButtonList.stream()
                .filter(b-> (b.getLayoutY()>=lessButtonJustClicked.getLayoutY() && b.getLayoutY()<(lessButtonJustClicked.getLayoutY())+200))
                .findFirst().get();
        plusButtonList.remove(buttonToBeRemoved);
        buttonToBeRemoved=lessButtonList.stream()
                .filter(b-> (b.getLayoutY()>=lessButtonJustClicked.getLayoutY() && b.getLayoutY()<=(lessButtonJustClicked.getLayoutY())+200))
                .findFirst().get();
        lessButtonList.remove(buttonToBeRemoved);
        TravelOptionComponent componentToBeRemoved= componentsList.stream()
                .filter(b-> (b.getTf_name().getLayoutY()>=lessButtonJustClicked.getLayoutY() && b.getLayoutY()<=(lessButtonJustClicked.getLayoutY())+200))
                .findFirst().get();
        componentsList.remove(componentToBeRemoved);

        //swipe up other elements
        background.getChildren().forEach(el-> {
                    if(el.getLayoutY()>=lessButtonJustClicked.getLayoutY()) {
                        el.setLayoutY(el.getLayoutY()-200);
                    }
        });
        componentsList.stream()
                .forEach(el-> el.updateLayoutY());
        System.out.println("DEBUG LESSBUTTON-> "+ componentsList);
    }

    /**
     * Add a new OptionComponentGraphic.
     */
    void addComponent(){
        //swipe down other components and update their layoutY
        background.getChildren().forEach(el-> {
                    if(el.getLayoutY()>plusButtonJustClicked.getLayoutY()) {
                        el.setLayoutY(el.getLayoutY()+200);
                    }
        });
        componentsList.stream()
                .forEach(el-> el.updateLayoutY());

        //add the selected kind of TravelOptionComponent
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[Accommodation]")){
            TravelOptionComponent componentAccommodation= new Accommodation("Accommodation", travelOption.getGroupID(), travelOption.getTravelName(), travelOption.getOptionName(), null, null, null, null, null, null, null, database,null, null);
            componentAccommodation.addEmptyGraphicComponent(((int) plusButtonJustClicked.getLayoutX()), (int) (plusButtonJustClicked.getLayoutY()+plusButtonJustClicked.getPrefHeight()+10), background);
            componentsList.add(componentAccommodation);
        }
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[Rental]")){
            TravelOptionComponent componentRental= new Rental("Rental", travelOption.getGroupID(), travelOption.getTravelName(), travelOption.getOptionName(), null, null, null, null, null, null, null, database,null);
            componentRental.addEmptyGraphicComponent(((int) plusButtonJustClicked.getLayoutX()), (int) (plusButtonJustClicked.getLayoutY()+plusButtonJustClicked.getPrefHeight()+10), background);
            componentsList.add(componentRental);
        }
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[Transport]")){
            TravelOptionComponent componentTransport= new Transport("Transport", travelOption.getGroupID(), travelOption.getTravelName(), travelOption.getOptionName(), null, null, null, null, null, null, null,database,null, null, null);
            componentTransport.addEmptyGraphicComponent(((int) plusButtonJustClicked.getLayoutX()), (int) (plusButtonJustClicked.getLayoutY()+plusButtonJustClicked.getPrefHeight()+10), background);
            componentsList.add(componentTransport);
        }
        createButtons();
        System.out.println("DEBUG PLUSBUTTON-> "+ componentsList);
    }

    /**
     * Create plusButton to create following component
     * Create lessButton to delete actual component
     */
    void createButtons(){
        Button nextPlusButton=new Button("+");  nextPlusButton.setPrefHeight(26);    nextPlusButton.setPrefWidth(26);
        nextPlusButton.setLayoutX(plusButtonJustClicked.getLayoutX()); nextPlusButton.setLayoutY(plusButtonJustClicked.getLayoutY()+200);
        plusButtonList.add(nextPlusButton); background.getChildren().add(nextPlusButton);
        nextPlusButton.setOnAction(h-> {
            plusButtonJustClicked=nextPlusButton;
            onPlusButton();
        });

        Button nextLessButton=new Button("-");  nextLessButton.setPrefHeight(26);    nextLessButton.setPrefWidth(26);
        nextLessButton.setLayoutX(plusButtonJustClicked.getLayoutX()+plusButtonJustClicked.getPrefWidth()+10); nextLessButton.setLayoutY(plusButtonJustClicked.getLayoutY()+1);
        lessButtonList.add(nextLessButton); background.getChildren().add(nextLessButton);
        nextLessButton.setOnAction(h-> {
            lessButtonJustClicked=nextLessButton;
            onLessButton();
        });
    }

    /**
     * Display metaPage.
     */
    @FXML
    void onCancelButton() {
        try {
            FXMLLoader loader =main.changeScene("metaPage-view.fxml");
            MetaPageController metaPageController= loader.getController();

            metaPageController.setGroup(group);
            metaPageController.setTravel(travel);
            metaPageController.setDatabase(database);
            metaPageController.loadData();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("META-PAGE NOT FOUND");
        }
    }

    /**
     * Save data and display metaPage
     */
    @FXML
    void onSaveButton() {
        //check if the optionName has been changed-> in case check if it is already present in the database
        if (!tf_optionName.getText().equals(travelOption.getOptionName())) {
            try {
                if (checkOptionNameExists(travelOption.getGroupID(), travelOption.getTravelName(), tf_optionName.getText())) {
                    new Alert(Alert.AlertType.ERROR, "This option name is already used for this travel, choose a new one").showAndWait();
                    return;
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
            }
        }

        //check if there is at least one component
        if(componentsList.size()<1){
            new Alert(Alert.AlertType.ERROR, "A travel option should contain at least one component.").showAndWait();
            return;
        }

        //delete the existing info about this option in the database Accommodation, Rental, Transport and traveloptions
        try{
            database.deleteTravelOption(travelOption.getGroupID(), travelOption.getTravelName(), travelOption.getOptionName());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nPrevious option configuration is lost").showAndWait();
            System.out.println("FATAL ERROR, PREVIOUS OPTION CONFIGURATION IS LOST");
        }

        //update travelOption
        travelOption.setOptionName(tf_optionName.getText());
        travelOption.setComponents(componentsList);
        travelOption.setTotalCost(
                componentsList.stream()
                        .mapToDouble(el -> el.getPrice().orElse(0.0))
                        .sum()
        );
        travelOption.setPerPersonCost(travelOption.getTotalCost()/group.getUsers().size());

        //store componentsList in the database
        Integer tmpPosInTravelOption = 1;
        System.out.println("DEBUG SAVE-> componentsList "+ componentsList);
        for (TravelOptionComponent c : componentsList) {
            c.setOptionName(tf_optionName.getText());
            c.setPosInTravelOption(tmpPosInTravelOption);
            c.storeInDB();
            ++tmpPosInTravelOption;
        }

        //store travelOption
        try {
            database.storeTravelOption(travelOption);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing TravelOption").showAndWait();
        }

        //comeback to the previous page
        try {
            FXMLLoader loader =main.changeScene("metaPage-view.fxml");
            MetaPageController metaPageController= loader.getController();

            Travel updatedTravel=travel;
            updatedTravel.setOptions(database.loadTravelOption(updatedTravel.getGroupID(), updatedTravel.getTravelName()));
            metaPageController.setGroup(group);
            metaPageController.setTravel(updatedTravel);
            metaPageController.setDatabase(database);
            metaPageController.loadData();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("META-PAGE NOT FOUND");
        }
    }

    /**
     * Check if the inserted TravelOption's name already exists in the database
     * @param groupID key1
     * @param travelName key2
     * @param optionName key3
     * @return true if TravelOption's name already exists
     * @throws SQLException if connection leaks
     */
    private boolean checkOptionNameExists(String groupID, String travelName, String optionName) throws SQLException {
        try (Connection connection = database.dataSource.getConnection();
             PreparedStatement checkData = connection.prepareStatement("SELECT * FROM traveloptions WHERE groupID = ? AND travelName = ? AND optionName = ?")) {
            checkData.setString(1, groupID);
            checkData.setString(2, travelName);
            checkData.setString(3, optionName);

            ResultSet optionFound = checkData.executeQuery();
            return optionFound.isBeforeFirst();
        }
    }

    /**
     * getter and setter
     *
     */
    public void setTravelOption(TravelOption travelOption) {
        this.travelOption = travelOption;
    }

    public DBConnection getDatabase() {
        return database;
    }

    public void setDatabase(DBConnection database) {
        this.database = database;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}

