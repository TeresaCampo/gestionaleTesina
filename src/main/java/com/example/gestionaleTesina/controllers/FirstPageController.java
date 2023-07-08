package com.example.gestionaleTesina.controllers;

import com.example.gestionaleTesina.AddressApplication;
import com.example.gestionaleTesina.classes.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.NoSuchElementException;

public class FirstPageController {
    @FXML
    private ComboBox<String> cb_GroupMember;
    @FXML
    private TableView<Travel> tableTravels;
    @FXML
    private TableColumn<Travel, Integer> numberOptionsColumn;
    @FXML
    private TableColumn<Travel, SwitchButton>statusColumn;
    @FXML
    private TableColumn<Travel, TextField> travelsColumn;

    DBConnection database;
    AddressApplication main = new AddressApplication();
    private Group group;

    public void initialize(){
        travelsColumn.setCellValueFactory(new PropertyValueFactory<>("tf_travelName"));
        numberOptionsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfOptions"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusButton"));
        travelsColumn.setStyle("; -fx-font-size: 13px;");
        numberOptionsColumn.setStyle("; -fx-font-size: 13px;");
        statusColumn.setStyle("; -fx-font-size: 13px;");
    }

    /**
     * Load data from the database and display it in the tableview.
     */
    public void loadData() {
        try {
            group=database.loadUsernames(group);
            group=database.loadTravels(group);
            tableTravels.setItems(FXCollections.observableArrayList(group.getTravels()));
            cb_GroupMember.setItems(FXCollections.observableArrayList(group.getUsers()));
            System.out.println("Data loaded successfully!");
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nError while loading group usernames and travels.").showAndWait();
        }
    }

    /**
     * Check if there's a selected travel.
     * @return if one travel is selected return position in the tableView, else -1 and throws NoSuchElementException
     */
    int selectedIndex() {
        int selectedIndex = tableTravels.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            throw new NoSuchElementException();
        }
        return selectedIndex;
    }

    /**
     * Delete selected travel.
     */
    @FXML
    private void onDeleteTravel() {
        try {
            int selectedIndex = selectedIndex();
            try {
                Travel toBeDeleted= tableTravels.getItems().get(selectedIndex);
                database.deleteTravelDB(toBeDeleted);
                group.getTravels().remove(toBeDeleted);
                System.out.println(group.getTravels().toString());
            }catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database Error\n Error while removing travel "+tableTravels.getItems().get(selectedIndex).getTravelName()).showAndWait();
            }
            tableTravels.getItems().remove(selectedIndex);
        } catch (NoSuchElementException e) {
            new Alert(Alert.AlertType.WARNING, "No travel Selected\nPlease select one from the table.").showAndWait();
        }
    }

    /**
     * Display logInPage.
     */
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

    /**
     * Display metaPage, load travel data from database and set the travel in metaPageController.
     */
    @FXML
    private void onEditTravel() {
        try {
            int selectedIndex = selectedIndex();

            Travel travelToEdit= tableTravels.getItems().get(selectedIndex);
            travelToEdit.setOptions(database.loadTravelOption(travelToEdit.getGroupID(), travelToEdit.getTravelName()));
            travelToEdit.setFavouriteOption(database.loadFavouriteOption(travelToEdit.getGroupID(), travelToEdit.getTravelName()));

            FXMLLoader loader =main.changeScene("metaPage-view.fxml");
            MetaPageController metaPageController= loader.getController();
            metaPageController.setTravel(travelToEdit);
            metaPageController.setGroup(group);
            metaPageController.setDatabase(database);
            metaPageController.loadData();
        }
        catch (NoSuchElementException e) {
            new Alert(Alert.AlertType.WARNING, "No travel Selected\nPlease select one from the table.").showAndWait();
        }
        catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\n Error while loading travel information of "+tableTravels.getItems().get(selectedIndex()).getTravelName()).showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("META-PAGE NOT FOUND");
        }
    }

    /**
     * Display metaPage and set new travel in metaPageController.
     */
    @FXML
    private void onNewTravel() {
        Travel newTravel= new Travel(group.getGroupID(), "Enter the travel name "+(tableTravels.getItems().size()+1), 0, false, new SwitchButton(group.getGroupID(), "Enter the travel name "+(tableTravels.getItems().size()+1) , false, database), database );
        tableTravels.getItems().add(newTravel);
        try {
            database.storeNewTravel(newTravel);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nError while storing new travel.").showAndWait();
        }
    }

    //Getter and Setter
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
