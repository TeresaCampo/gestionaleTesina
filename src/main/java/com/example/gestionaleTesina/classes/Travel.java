package com.example.gestionaleTesina.classes;

import com.example.gestionaleTesina.controllers.DBConnection;
import com.example.gestionaleTesina.controllers.MyTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Travel {
    private String groupID;
    private String travelName;
    private ArrayList<TravelOption> options;
    private boolean status;
    private SwitchButton statusButton;
    private javafx.scene.control.TextField tf_travelName;
    private Integer numberOfOptions;

    DBConnection database = new DBConnection();

    /**
     * Create new travel for editPage.
     */
    public Travel(String groupID) {
        this.groupID = groupID;
    }

    /**
     * Create travel for firstPage
     */
    public Travel(String groupID, String travelName, Integer numberOfOptions, boolean status, SwitchButton statusButton, DBConnection database) {
        this.groupID=groupID;
        this.travelName = travelName;
        this.tf_travelName=new TextField(travelName);
        MyTextField.maxLen45(tf_travelName);
        tf_travelName.setOnAction((h)-> updateTables(groupID, tf_travelName.getText()));
        this.database=database;
        this.numberOfOptions=numberOfOptions;
        this.status = status;
        this.statusButton=statusButton;
        statusButton.switchOnProperty().addListener(((observable, oldValue, newValue) -> this.status=statusButton.switchOnProperty().get()));
    }

    void updateTables(String groupID, String newTravelName){
        System.out.println("Updating travel name...");
        try {
            if (!checkTravelNameExists(groupID, newTravelName) && !newTravelName.isEmpty()) {
                database.updateTravelName(this, newTravelName);
                this.statusButton.setTravelName(newTravelName);
                this.setTravelName(newTravelName);
                return;
            }
            if(newTravelName.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Travel name can't be an empty string\nPlease choose a new one").showAndWait();
                return;
            }
            else{
                new Alert(Alert.AlertType.ERROR, "This travel name already exists in your group\nPlease choose a new one").showAndWait();
                return;
            }
        } catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
        }

    }

    private boolean checkTravelNameExists(String groupID, String newTravelName) throws SQLException {
        try (Connection connection = database.dataSource.getConnection();
             PreparedStatement checkData = connection.prepareStatement("SELECT * FROM travels WHERE groupID = ? AND travelName=?")) {
            checkData.setString(1, groupID);
            checkData.setString(2, newTravelName);
            ResultSet accountFound = checkData.executeQuery();
            if (accountFound.isBeforeFirst()) {
                return true;
            }
            return false;
        }
    }

    /**
     * Create travel for EditPage
     */

    public Travel(String groupID, String travelName) {
        this.groupID = groupID;
        this.travelName = travelName;
    }
    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public ArrayList<TravelOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<TravelOption> options) {
        this.options = options;
    }

    public Integer getNumberOfOptions() {
        return numberOfOptions;
    }

    public void setNumberOfOptions(Integer numberOfOptions) {
        this.numberOfOptions = numberOfOptions;
    }

    public SwitchButton getStatusButton() {
        return statusButton;
    }

    public void setStatusButton(SwitchButton statusButton) {
        this.statusButton = statusButton;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public boolean isStatus() {
        return status;
    }

    public TextField getTf_travelName() {
        return tf_travelName;
    }

    public void setTf_travelName(TextField tf_travelName) {
        this.tf_travelName = tf_travelName;
    }
}
