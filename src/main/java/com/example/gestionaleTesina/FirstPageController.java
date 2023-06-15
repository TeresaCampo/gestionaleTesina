package com.example.gestionaleTesina;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FirstPageController {
    @FXML
    private AnchorPane anchorPane;
    DBConnection connector = new DBConnection();
    AddressApplication main = new AddressApplication();
    String groupID;
    String password;
    Group group;

    public void initialize(){
        connector.getConnection();
        group= new Group(groupID, password, new ArrayList<String>(), new ArrayList<Travel>(), new ArrayList<Travel>());

    }

    private void loadData(){

        try (Connection connection = connector.dataSource.getConnection();
            PreparedStatement loadUsernames = connection.prepareStatement("SELECT username FROM usernames WHERE groupID = ?")) {
            loadUsernames.setString(1, groupID);
            ResultSet rs = loadUsernames.executeQuery();
            while (rs.next()) {
                group.users.add(rs.getString("username"));
            }
            //group.travelsToDo, Travel.name
            PreparedStatement loadTravelsToDo = connection.prepareStatement("SELECT * FROM travelstodo WHERE groupID = ?");
                loadTravelsToDo.setString(1, groupID);
                rs = loadTravelsToDo.executeQuery();
                while (rs.next()) {
                    group.travelsToDo.add(new Travel(
                            rs.getString("travelName"),
                            new ArrayList<TravelOption>(),
                            null));
                }
            //Travel.options
            //...to be finished...

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
        }

    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
