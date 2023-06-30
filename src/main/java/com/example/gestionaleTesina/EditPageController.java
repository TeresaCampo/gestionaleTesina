package com.example.gestionaleTesina;

import com.example.gestionaleTesina.classes.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private TravelOption travelOption;
    private Group group;
    ListView<String> componentsListView;


    /**
     * Initialize database, plusButtonList, firstPlusButton
     * Create componentsListView to display choices for the new component to be created
     * Display actual components
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
            else addComponent();});


        //to test
        travelOption=new TravelOption("test", 20);
        group.setUsers(new ArrayList<>());
        group.getUsers().add("terry");
        group.getUsers().add("otta");

        travelOption.setGroupID("speriam");
        travelOption.setTravelName("speriamDavvero");
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
            createButtons();
        }
    }

    /**
     * Set plusButtonJustClicked and display listView components
     */
    void onPlusButton(Button plusButton){
        plusButtonJustClicked=plusButton;
        componentsListView.setLayoutY(plusButtonJustClicked.getLayoutY());
        if(!background.getChildren().contains(componentsListView))  background.getChildren().add(componentsListView);
    }

    /**
     * Remove the selected OptionComponentGraphic
     * @param lessButton just clicked lessButton
     */
    void onLessButton(Button lessButton){
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
     * Add a new OptionComponentGraphic
     */
    void addComponent(){
        background.getChildren().forEach(el-> {
                    if(el.getLayoutY()>plusButtonJustClicked.getLayoutY()) {
                        el.setLayoutY(el.getLayoutY()+200);
                    }
        });
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[accommodation]")){
            addAccommodation(plusButtonJustClicked);
        }
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[rental]")){
            addRental(plusButtonJustClicked);
        }
        if(componentsListView.getSelectionModel().getSelectedItems().toString().equals("[transport]")){
            addTransport(plusButtonJustClicked);
        }
        createButtons();
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

    /**
     * Create plusButton to create following component
     * Create lessButton to delete actual component
     */
    void createButtons(){
        Button nextPlusButton=new Button("+");  nextPlusButton.setPrefHeight(26);    nextPlusButton.setPrefWidth(26);
        nextPlusButton.setLayoutX(plusButtonJustClicked.getLayoutX()); nextPlusButton.setLayoutY(plusButtonJustClicked.getLayoutY()+200);
        plusButtonList.add(nextPlusButton); background.getChildren().add(nextPlusButton);
        nextPlusButton.setOnAction(h-> onPlusButton(nextPlusButton));

        Button nextLessButton=new Button("-");  nextLessButton.setPrefHeight(26);    nextLessButton.setPrefWidth(26);
        nextLessButton.setLayoutX(plusButtonJustClicked.getLayoutX()+plusButtonJustClicked.getPrefWidth()+10); nextLessButton.setLayoutY(plusButtonJustClicked.getLayoutY()+1);
        lessButtonList.add(nextLessButton); background.getChildren().add(nextLessButton);
        nextLessButton.setOnAction(h-> onLessButton(nextLessButton));
    }

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

        //delete the existing info about this option in the database Accommodation, Rental, Transport and traveloptions
        try{
            database.deleteTravelOption(travelOption.getGroupID(), travelOption.getTravelName(), travelOption.getOptionName());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nPrevious option configuration is lost").showAndWait();
            System.out.println("FATAL ERROR, PREVIOUS OPTION CONFIGURATION IS LOST");
        }

        //set new travelName
        travelOption.setOptionName(tf_optionName.getText());
        //set new components and store them in the database
        TreeSet<TravelOptionComponent> newComponents = new TreeSet<>(Comparator.comparing((TravelOptionComponent e) -> e.getPosInTravelOption().get()));
        Integer tmpPosInTravelOption = 1;
        System.out.println("DEBUG SAVE-> componentsList "+componentsList.toString());
        for (OptionComponentsGraphic c : componentsList) {
            TravelOptionComponent newComponent = null;
            System.out.println(c.getLb_kindOfComponent().getText());
            if ("Accommodation".equals(c.getLb_kindOfComponent().getText())) {
                newComponent = c.convertAccommodation(travelOption.getGroupID(), travelOption.getTravelName(), tf_optionName.getText(), tmpPosInTravelOption);
                try {
                    database.storeAccommodation(newComponent, travelOption.getGroupID(), travelOption.getTravelName(), tf_optionName.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing Accommodation").showAndWait();
                }
            }
            if ("Rental".equals(c.getLb_kindOfComponent().getText())) {
                newComponent = c.convertRental(travelOption.getGroupID(), travelOption.getTravelName(), tf_optionName.getText(), tmpPosInTravelOption);
                try {
                    database.storeRental(newComponent, travelOption.getGroupID(), travelOption.getTravelName(), tf_optionName.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing Rental").showAndWait();
                }
            }
            if ("Transport".equals(c.getLb_kindOfComponent().getText())) {
                newComponent = c.convertTransport(travelOption.getGroupID(), travelOption.getTravelName(), tf_optionName.getText(), tmpPosInTravelOption);
                try {
                    database.storeTransport(newComponent, travelOption.getGroupID(), travelOption.getTravelName(), tf_optionName.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing Transport").showAndWait();
                }
            }
            ++tmpPosInTravelOption;
            newComponents.add(newComponent);
        }
        travelOption.setComponents(newComponents);
        //set total cost
        travelOption.setTotalCost(
                newComponents.stream()
                        .mapToDouble(el -> el.getPrice().orElse(0.0))
                        .sum()
        );
        //set per person cost
        travelOption.setPerPersonCost(travelOption.getTotalCost()/group.getTravels().size());

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

            Travel updatedTravel= new Travel(travelOption.getGroupID(), travelOption.getTravelName());
            updatedTravel.setOptions(database.loadTravelOption(updatedTravel.getGroupID(), updatedTravel.getTravelName()));
            updatedTravel.setFavouriteOption(database.loadFavouriteOption(updatedTravel.getGroupID(), updatedTravel.getTravelName()));
            metaPageController.setTravel(updatedTravel);
            metaPageController.setGroup(group);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("META-PAGE NOT FOUND");
        }

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

