package com.example.gestionaleTesina;

import com.example.gestionaleTesina.classes.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.NoSuchElementException;


public class FirstPageController {
    @FXML
    private TableView<Travel> tableTravels;
    @FXML
    private TableColumn<Travel, Integer> numberOptionsColumn;
    @FXML
    private TableColumn<Travel, SwitchButton>statusColumn;
    @FXML
    private TableColumn<Travel, String> travelsColumn;

    DBConnection database = new DBConnection();
    AddressApplication main = new AddressApplication();
    private Group group;

    public void initialize(){
        database.initializeConnection();

        travelsColumn.setCellValueFactory(new PropertyValueFactory<>("travelName"));
        numberOptionsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfOptions"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusButton"));
    }

    /**
     * Load data from the database and display it in the tableview.
     */
    public void loadData() {
        try {
            group=database.loadUsernames(group);
            group=database.loadTravels(group);
            tableTravels.setItems(FXCollections.observableArrayList(group.getTravels()));
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nError while loading group usernames and travels.").showAndWait();
        }
    }

    /**
     * Check if there's a selected travel
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
     * Delete selected travel
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
            main.changeScene("login-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOGIN-PAGE NOT FOUND");
        }
    }

    /**
     * Display metaPage, load travel data from database and set the travel in metaPageController
     */
    @FXML
    private void onEditTravel() {
        try {
            int selectedIndex = selectedIndex();

            Travel newTravel= tableTravels.getItems().get(selectedIndex);
            newTravel.setOptions(database.loadTravelOption(newTravel.getGroupID(), newTravel.getTravelName()));
            newTravel.setFavouriteOption(database.loadFavouriteOption(newTravel.getGroupID(), newTravel.getTravelName()));

            FXMLLoader loader =main.changeScene("metaPage-view.fxml");
            MetaPageController metaPageController= loader.getController();
            metaPageController.setTravel(newTravel);
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
     * Display metaPage and set new travel in metaPageController
     */
    @FXML
    private void onNewTravel() {
        try {
            FXMLLoader loader =main.changeScene("metaPage-view.fxml");
            MetaPageController metaPageController= loader.getController();
            metaPageController.setTravel(new Travel(group.getGroupID()));
            metaPageController.setGroup(group);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("META-PAGE NOT FOUND");
        }
    }

    //Getter and Setter
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
}
