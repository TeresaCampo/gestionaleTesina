package com.example.gestionaleTesina;

import com.example.gestionaleTesina.classes.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.TreeSet;


public class FirstPageController {
    DBConnection connector = new DBConnection();
    AddressApplication main = new AddressApplication();
    String groupID;
    String password;
    Group group;
    @FXML
    private TableView<Travel> tableTravels;

    @FXML
    private TableColumn<Travel, Integer> numberOptionsColumn;

    @FXML
    private TableColumn<Travel, SwitchButton>statusColumn;

    @FXML
    private TableColumn<Travel, String> travelsColumn;

    public void initialize(){
        connector.getConnection();
        travelsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberOptionsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfOptions"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusButton"));
    }

    public void initializeForSwitchButton(){
        connector.getConnection();
    }

    public void loadData() {
        try {
            loadUsernames();
            loadTravels();
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\n Error while loading groupInfo").showAndWait();

        }
    }

    void loadUsernames() throws SQLException {
        try (Connection connection = connector.dataSource.getConnection();
             PreparedStatement loadUsernames = connection.prepareStatement("SELECT username FROM usernames WHERE groupID = ?")) {
            //test stamp
            System.out.println("Loading usernames of "+groupID);
            loadUsernames.setString(1, groupID);
            ResultSet rs = loadUsernames.executeQuery();
            while (rs.next()) {
                group.addUser(rs.getString("username"));
                //test stamp
                System.out.println(rs.getString("username"));
            }
        }
    }

    void loadTravels() throws SQLException {
        //load travelsName and status
        try (Connection connection = connector.dataSource.getConnection();
             PreparedStatement loadTravels = connection.prepareStatement("SELECT * FROM travels WHERE groupID = ?")) {
            loadTravels.setString(1, groupID);
            ResultSet rs = loadTravels.executeQuery();
            while (rs.next()) {
                //test stamp
                System.out.println(rs.getString("travelName"));
                Travel toBeAdded= new Travel(
                                rs.getString("travelName"),
                                loadTravelOption(rs.getString("travelName")),
                                loadFavouriteOption(rs.getString("travelName")),
                                rs.getBoolean("status"),
                                null);
                toBeAdded.setStatusButton(new SwitchButton(group, toBeAdded));
                group.addTravel(toBeAdded);
            }
        }
        tableTravels.setItems(FXCollections.observableArrayList(group.getTravels()));
    }
    ArrayList<TravelOption> loadTravelOption(String travelName) throws SQLException {
        ArrayList<TravelOption> travelOptions=new ArrayList<>();
        try (Connection connection = connector.dataSource.getConnection();
            PreparedStatement loadTravelOptions = connection.prepareStatement("SELECT * FROM traveloptions WHERE groupID = ? AND travelName = ?")) {
            loadTravelOptions.setString(1, groupID);
            loadTravelOptions.setString(2, travelName);
            ResultSet rs = loadTravelOptions.executeQuery();

            while (rs.next()) {
                //test stamp
                System.out.println(rs.getString("optionName"));
                travelOptions.add(new TravelOption(
                        rs.getString("optionName"),
                        rs.getDouble("totalCost"),
                        rs.getDouble("perPersonCost"),
                        rs.getString("comment"),
                        loadOption(travelName, rs.getString("optionName") )
                ));
            }
        }
        return travelOptions;
    }

    TreeSet<TravelOptionComponent> loadOption(String travelName, String optionName) throws SQLException {
        TreeSet<TravelOptionComponent> travelOptions = new TreeSet<>(Comparator.comparing((TravelOptionComponent e) -> e.getPosInTravelOption().get()));

        travelOptions=loadFromAccommodation(travelName, optionName, travelOptions);
        travelOptions=loadFromTransport(travelName, optionName,travelOptions);
        travelOptions=loadFromRental(travelName, optionName,travelOptions);
        return travelOptions;
    }

    /**
     * to load specific Option Component
     *
     */
    TreeSet<TravelOptionComponent> loadFromAccommodation(String travelName, String optionName, TreeSet<TravelOptionComponent> travelOptions) throws SQLException {
        try (Connection connection = connector.dataSource.getConnection();
            PreparedStatement loadOptionComponent = connection.prepareStatement("SELECT * FROM accommodation WHERE groupID = ? AND travelName = ? AND optionName=?")) {
            loadOptionComponent.setString(1, groupID);
            loadOptionComponent.setString(2, travelName);
            loadOptionComponent.setString(3, optionName);
            ResultSet rs = loadOptionComponent.executeQuery();
            while(rs.next()){
                //test stamp
                System.out.println(rs.getString("name")+" alla posizione "+ rs.getInt("posInTravelOption"));
                travelOptions.add(new TravelOptionComponent(
                        "accommodation",
                        rs.getString("groupID"),
                        rs.getString("travelName"),
                        rs.getString("optionName"),
                        rs.getInt("posInTravelOption"),
                        rs.getString("payed"),
                        rs.getDouble("price"),
                        rs.getString("name"),
                        rs.getDate("checkInDay"),
                        rs.getDate("checkOutDay"),
                        rs.getTime("checkInTime"),
                        rs.getTime("checkOutTime"),
                        rs.getInt("numberOfRoom"),
                        rs.getBoolean("privateToilet")
                ));
            }
        }
        return travelOptions;
    }
    TreeSet<TravelOptionComponent> loadFromTransport(String travelName, String optionName, TreeSet<TravelOptionComponent> travelOptions) throws SQLException {
        try (Connection connection = connector.dataSource.getConnection();
             PreparedStatement loadOptionComponent = connection.prepareStatement("SELECT * FROM transport WHERE groupID = ? AND travelName = ? AND optionName=?")) {
            loadOptionComponent.setString(1, groupID);
            loadOptionComponent.setString(2, travelName);
            loadOptionComponent.setString(3, optionName);
            ResultSet rs = loadOptionComponent.executeQuery();
            while(rs.next()){
                //test stamp
                System.out.println(rs.getString("name")+" alla posizione "+ rs.getInt("posInTravelOption"));
                travelOptions.add(new TravelOptionComponent(
                        "transport",
                        rs.getString("groupID"),
                        rs.getString("travelName"),
                        rs.getString("optionName"),
                        rs.getInt("posInTravelOption"),
                        rs.getString("payed"),
                        rs.getDouble("price"),
                        rs.getString("name"),
                        rs.getDate("arrivalDay"),
                        rs.getDate("departureDay"),
                        rs.getTime("arrivalTime"),
                        rs.getTime("departureTime"),
                        rs.getString("from"),
                        rs.getString("to"),
                        rs.getString("kindOfTransport")
                ));
            }
        }
        return travelOptions;
    }

    TreeSet<TravelOptionComponent> loadFromRental(String travelName, String optionName, TreeSet<TravelOptionComponent> travelOptions) throws SQLException {
        try (Connection connection = connector.dataSource.getConnection();
             PreparedStatement loadOptionComponent = connection.prepareStatement("SELECT * FROM rental WHERE groupID = ? AND travelName = ? AND optionName=?")) {
            loadOptionComponent.setString(1, groupID);
            loadOptionComponent.setString(2, travelName);
            loadOptionComponent.setString(3, optionName);
            ResultSet rs = loadOptionComponent.executeQuery();
            while(rs.next()){
                //test stamp
                System.out.println(rs.getString("name")+" alla posizione "+ rs.getInt("posInTravelOption"));
                travelOptions.add(new TravelOptionComponent(
                        "rental",
                        rs.getString("groupID"),
                        rs.getString("travelName"),
                        rs.getString("optionName"),
                        rs.getInt("posInTravelOption"),
                        rs.getString("payed"),
                        rs.getDouble("price"),
                        rs.getString("name"),
                        rs.getDate("checkInDay"),
                        rs.getDate("checkOutDay"),
                        rs.getTime("checkInTime"),
                        rs.getTime("checkOutTime"),
                        rs.getString("kindOfRental")
                ));
            }
        }
        return travelOptions;
    }

    String loadFavouriteOption(String travelName) throws SQLException {
        String favouriteOption=null;
        try (Connection connection = connector.dataSource.getConnection();
             PreparedStatement  loadFavouriteOption= connection.prepareStatement("SELECT * FROM favouriteoptions WHERE groupID = ? AND travelName = ?")) {
            loadFavouriteOption.setString(1, groupID);
            loadFavouriteOption.setString(2, travelName);
            ResultSet rs = loadFavouriteOption.executeQuery();

            while (rs.next()) {
                //test stamp
                System.out.println("opzione preferita è "+rs.getString("optionName"));
                favouriteOption=rs.getString("optionName");
            }
        }
        return favouriteOption;
    }

    /**
     * to find the selected travel
     */
    int selectedIndex() {
        int selectedIndex = tableTravels.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            throw new NoSuchElementException();
        }
        return selectedIndex;
    }
    /**
     * to delete a travel
     */
    @FXML
    private void onDeleteTravel() {
        try {
            int selectedIndex = selectedIndex();
            try {
                deleteTravelDB(tableTravels.getItems().get(selectedIndex));
            }catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database Error\n Error while removing travel "+tableTravels.getItems().get(selectedIndex).getName()).showAndWait();
            }
            tableTravels.getItems().remove(selectedIndex);
        } catch (NoSuchElementException e) {
            new Alert(Alert.AlertType.WARNING, "No Travel Selected\nPlease select one from the table." ).showAndWait();
        }
    }

    void deleteTravelDB(Travel travel) throws SQLException {
        try (Connection connection = connector.dataSource.getConnection();
            PreparedStatement removeTravel = connection.prepareStatement("DELETE FROM travels WHERE groupID = ? AND travelName = ?")) {
            removeTravel.setString(1, groupID);
            removeTravel.setString(2, travel.getName());
            removeTravel.executeUpdate();
        }
        try (Connection connection = connector.dataSource.getConnection();
            PreparedStatement removeTravel = connection.prepareStatement("DELETE FROM traveloptions WHERE groupID = ? AND travelName = ?")) {
            removeTravel.setString(1, groupID);
            removeTravel.setString(2, travel.getName());
            removeTravel.executeUpdate();
        }
        try (Connection connection = connector.dataSource.getConnection();
             PreparedStatement removeTravel = connection.prepareStatement("DELETE FROM accommodation WHERE groupID = ? AND travelName = ?")) {
            removeTravel.setString(1, groupID);
            removeTravel.setString(2, travel.getName());
            removeTravel.executeUpdate();
        }
        try (Connection connection = connector.dataSource.getConnection();
             PreparedStatement removeTravel = connection.prepareStatement("DELETE FROM transport WHERE groupID = ? AND travelName = ?")) {
            removeTravel.setString(1, groupID);
            removeTravel.setString(2, travel.getName());
            removeTravel.executeUpdate();
        }
        try (Connection connection = connector.dataSource.getConnection();
             PreparedStatement removeTravel = connection.prepareStatement("DELETE FROM rental WHERE groupID = ? AND travelName = ?")) {
            removeTravel.setString(1, groupID);
            removeTravel.setString(2, travel.getName());
            removeTravel.executeUpdate();
        }
    }

    /**
     * To update travel status through SwitchButton
     */
    public void updateTravelStatus(String groupName, String travelName, boolean newStatus){
        try (Connection connection = connector.dataSource.getConnection();
            PreparedStatement  updateTravelStatus= connection.prepareStatement("UPDATE travels SET status = ? WHERE groupID = ? AND travelName = ?")) {
            updateTravelStatus.setBoolean(1, newStatus);
            updateTravelStatus.setString(2, groupName);
            updateTravelStatus.setString(3, travelName);
            updateTravelStatus.executeUpdate();
            System.out.println(newStatus);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\n Error while updating travelStatus").showAndWait();
        }

    }

    /**
     * to logOut
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
     * Getter and Setter
     */
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
