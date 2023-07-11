package com.example.gestionaleTesina.classes;

import com.example.gestionaleTesina.controllers.DBConnection;
import com.example.gestionaleTesina.controllers.MyTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.TreeSet;

public class TravelOption {
    DBConnection database;
    private String groupID;
    private String travelName;
    private String optionName;
    private double totalCost;
    private double perPersonCost;
    private String comment;
    private TreeSet<TravelOptionComponent> components = new TreeSet<>(Comparator.comparing((TravelOptionComponent e) -> e.getPosInTravelOption()));
    private TextField tf_optionName;

    /**
     * Create travelOption
     */
    public TravelOption(String groupID, String travelName, String optionName, double totalCost, double perPersonCost, String comment, TreeSet<TravelOptionComponent> components, DBConnection database) {
        this.groupID = groupID;
        this.travelName = travelName;
        this.optionName = optionName;
        this.tf_optionName = new TextField(optionName);
        MyTextField.maxLen45(tf_optionName);
        tf_optionName.setMaxWidth(150);
        tf_optionName.setOnAction((h) -> updateTables(groupID, travelName, tf_optionName.getText()));
        this.database = database;
        this.totalCost = totalCost;
        this.perPersonCost = perPersonCost;
        this.comment = comment;
        this.components = components;
    }

    public TravelOption(String groupID, String travelName, String optionName, DBConnection database) {
        this.groupID = groupID;
        this.travelName = travelName;
        this.optionName = optionName;
        this.tf_optionName = new TextField(optionName);
        MyTextField.maxLen45(tf_optionName);
        tf_optionName.setMaxWidth(150);
        tf_optionName.setOnAction((h) -> updateTables(groupID, travelName, tf_optionName.getText()));
    }

    void updateTables(String groupID, String travelName, String newTravelOptionName) {
        try {
            if (!checkTravelOptionNameExists(groupID, travelName, newTravelOptionName) && !newTravelOptionName.isEmpty()) {
                database.updateTravelOptionName(this, newTravelOptionName);
                this.setOptionName(newTravelOptionName);
                System.out.println("Travel option name successfully updated!");
                return;
            }
            if (newTravelOptionName.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "TravelOption name can't be an empty string\nPlease choose a new one").showAndWait();
                return;
            } else {
                new Alert(Alert.AlertType.ERROR, "This travelOption name already exists in your travel\nPlease choose a new one").showAndWait();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
        }
    }

    private boolean checkTravelOptionNameExists(String groupID, String travelName, String newTravelOptionName) throws SQLException {
        try (Connection connection = database.dataSource.getConnection(); PreparedStatement checkData = connection.prepareStatement("SELECT * FROM traveloptions WHERE groupID = ? AND travelName=? AND optionName = ?")) {
            checkData.setString(1, groupID);
            checkData.setString(2, travelName);
            checkData.setString(3, newTravelOptionName);
            ResultSet accountFound = checkData.executeQuery();
            if (accountFound.isBeforeFirst()) {
                return true;
            }
            return false;
        }
    }

    //constructor to test
    public TravelOption(String optionName, double totalCost) {
        this.optionName = optionName;
        this.totalCost = totalCost;
    }

    //getter and setter
    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getPerPersonCost() {
        return perPersonCost;
    }

    public void setPerPersonCost(double perPersonCost) {
        this.perPersonCost = perPersonCost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TreeSet<TravelOptionComponent> getComponents() {
        return components;
    }

    public void setComponents(TreeSet<TravelOptionComponent> components) {
        this.components = components;
    }

    public DBConnection getDatabase() {
        return database;
    }

    public void setDatabase(DBConnection database) {
        this.database = database;
    }

    public TextField getTf_optionName() {
        return tf_optionName;
    }

    public void setTf_optionName(TextField tf_optionName) {
        this.tf_optionName = tf_optionName;
    }
}
