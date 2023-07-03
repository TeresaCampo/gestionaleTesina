package com.example.gestionaleTesina;

import com.example.gestionaleTesina.classes.Group;
import com.example.gestionaleTesina.classes.Travel;
import com.example.gestionaleTesina.classes.TravelOption;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

public class MetaPageController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button groupMembersButton;
    @FXML
    private Label lb_to;
    @FXML
    private Label lb_from;
    @FXML
    private Label lb_meta;
    @FXML
    private Label lb_option;
    @FXML
    private Label lb_price;
    @FXML
    private Label lb_pricePerson;
    @FXML
    private Button logoutButton;
    @FXML
    private Button newButton;
    @FXML
    private TableView<TravelOption> optionTable;
    @FXML
    private ScrollBar scrollbar_options;
    @FXML
    private TextArea ta_info;
    @FXML
    private TableColumn<TravelOption, String> tv_tabOptions;


    AddressApplication main=new AddressApplication();
    DBConnection database;
    private Group group;
    private Travel travel;

    public void initialize(){
        tv_tabOptions.setCellValueFactory(new PropertyValueFactory<>("optionName"));
        optionTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTravelOptionDetails(newValue));
        //clear travelOption details.
        showTravelOptionDetails(null);
    }

    void showTravelOptionDetails(TravelOption travelOption){
        if(travelOption!=null){
            lb_option.setText(travelOption.getOptionName());
            travelOption.getComponents().first().getCheckInDate().ifPresentOrElse((date)-> lb_from.setText(date.toString()), ()-> lb_from.setText(""));
            travelOption.getComponents().last().getCheckOutDate().ifPresentOrElse((date)-> lb_to.setText(date.toString()), ()-> lb_to.setText(""));
            lb_price.setText(String.valueOf(travelOption.getTotalCost()));
            lb_pricePerson.setText(String.valueOf(travelOption.getPerPersonCost()));
        }
        else{
            lb_option.setText("");
            lb_from.setText("");
            lb_to.setText("");
            lb_price.setText("");
            lb_pricePerson.setText("");
        }
    }

    /**
     * Load data from the database and display it in the tableview.
     */
    public void loadData() {
            optionTable.setItems(FXCollections.observableArrayList(travel.getOptions()));
    }

    @FXML
    void onEditShowButton(ActionEvent event) {
        try {
            main.changeScene("editPage-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EDIT-PAGE NOT FOUND");
        }
    }
    @FXML
    void onNewButton(ActionEvent event) {
        try {
            main.changeScene("editPage-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EDIT-PAGE NOT FOUND");
        }
    }
    @FXML
    void onLogoutButton(){
        try {
            database.dataSource.close();
            main.changeScene("login-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOGIN-PAGE NOT FOUND");
        }
    }

    @FXML
    void onBackButton(){
        try {
            FXMLLoader loader = main.changeScene("firstPage-view.fxml");
            FirstPageController firstPageController = loader.getController();
            firstPageController.setGroup(new Group(group.getGroupID(), group.getPassword(), new ArrayList<>(), new ArrayList<>()));
            firstPageController.setDatabase(database);
            firstPageController.loadData();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FIRST-PAGE NOT FOUND");
        }
    }

    //getter and setter
    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public DBConnection getDatabase() {
        return database;
    }

    public void setDatabase(DBConnection database) {
        this.database = database;
    }
}
