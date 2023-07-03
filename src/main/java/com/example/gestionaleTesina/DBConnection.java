package com.example.gestionaleTesina;

import com.example.gestionaleTesina.classes.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.*;

public class DBConnection {
    public HikariDataSource dataSource;

    /**
     * Create dataSource for connection to my database
     */
    public void initializeConnection() {
        String schemeName="progettojavafx";
        String userName="progettojavafx";
        String password="siSperabene!";
        String JDBC_Driver_MySQL = "com.mysql.cj.jdbc.Driver";
        String JDBC_URL_MySQL = "jdbc:mysql://localhost:3306/"+schemeName+"?user="+userName+"&password="+password+"&serverTimezone=" + TimeZone.getDefault().getID();

        System.out.println("- dbConnection()...");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(JDBC_Driver_MySQL);
        config.setJdbcUrl(JDBC_URL_MySQL);
        config.setLeakDetectionThreshold(2000);
        dataSource = new HikariDataSource(config);
    }

    public void setDataSource(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
    //To load->
    /**
     * Given a group (groupID), load its usernames(from usernames table).
     * @return group updated with its usernames
     * @param group groupID selected
     * @throws SQLException if connection fails
     */
     Group loadUsernames(Group group) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement loadUsernames = connection.prepareStatement("SELECT username FROM usernames WHERE groupID = ?")) {
            System.out.println("Loading usernames of "+group.getGroupID()+"...");
            loadUsernames.setString(1, group.getGroupID());
            ResultSet rs = loadUsernames.executeQuery();
            while (rs.next()) {
                group.addUser(rs.getString("username"));
                /*test stamp*/  System.out.println(rs.getString("username"));
            }
        }
        return group;
     }

    /**
     * Given a group (groupID), load its travels(from travels table).
     * For each travel loads only travelName, numberOfOptions, status
     * @return group updated with its travels
     * @param group group selected
     * @throws SQLException if connection fails
     */
    Group loadTravels(Group group) throws SQLException {
        //load travelsName and status
        try (Connection connection = dataSource.getConnection();
             PreparedStatement loadTravels = connection.prepareStatement("SELECT * FROM travels WHERE groupID = ?")) {

            loadTravels.setString(1, group.getGroupID());
            ResultSet rs = loadTravels.executeQuery();

            while (rs.next()) {
                /*test stamp*/  System.out.println(rs.getString("travelName"));
                Travel toBeAdded= new Travel(
                        group.getGroupID(),
                        rs.getString("travelName"),
                        loadTravelOption(group.getGroupID(), rs.getString("travelName")).size(),
                        rs.getBoolean("status"),
                        new SwitchButton(group.getGroupID(), rs.getString("travelName"), rs.getBoolean("status"), this),
                        this
                        );
                group.addTravel(toBeAdded);
            }
        }
        return group;
    }

    /**
     * Given a travel(groupID, travelName), load its travelOptions(from traveloptions table).
     * @param groupID key1
     * @param travelName key2
     * @return list of travelOptions
     * @throws SQLException if connection fails
     */
    ArrayList<TravelOption> loadTravelOption(String groupID, String travelName) throws SQLException {
        ArrayList<TravelOption> travelOptions=new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement loadTravelOptions = connection.prepareStatement("SELECT * FROM traveloptions WHERE groupID = ? AND travelName = ?")) {
            loadTravelOptions.setString(1, groupID);
            loadTravelOptions.setString(2, travelName);
            ResultSet rs = loadTravelOptions.executeQuery();

            while (rs.next()) {
                //test stamp
                System.out.println("just loaded OPTION "+rs.getString("optionName"));
                travelOptions.add(new TravelOption(
                        groupID,
                        travelName,
                        rs.getString("optionName"),
                        rs.getDouble("totalCost"),
                        rs.getDouble("perPersonCost"),
                        rs.getString("comment"),
                        loadOption(groupID,travelName, rs.getString("optionName")),
                        this
                ));
            }
        }
        return travelOptions;
    }

    /**
     * Given a travelOption(groupID, travelName, optionName), load its components(from accommodation, rental, transport tables).
     * @param groupID ke1
     * @param travelName key2
     * @param optionName key3
     * @return set of travelComponents
     * @throws SQLException if connection fails
     */
    TreeSet<TravelOptionComponent> loadOption(String groupID,String travelName, String optionName) throws SQLException {
        TreeSet<TravelOptionComponent> travelOptions = new TreeSet<>(Comparator.comparing((TravelOptionComponent e) -> e.getPosInTravelOption().get()));

        travelOptions=loadFromAccommodation(groupID,travelName, optionName, travelOptions);
        travelOptions=loadFromTransport(groupID,travelName, optionName,travelOptions);
        travelOptions=loadFromRental(groupID,travelName, optionName,travelOptions);
        return travelOptions;
    }
    TreeSet<TravelOptionComponent> loadFromAccommodation(String groupID, String travelName, String optionName, TreeSet<TravelOptionComponent> travelOptions) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement loadOptionComponent = connection.prepareStatement("SELECT * FROM accommodation WHERE groupID = ? AND travelName = ? AND optionName=?")) {
            loadOptionComponent.setString(1, groupID);
            loadOptionComponent.setString(2, travelName);
            loadOptionComponent.setString(3, optionName);
            ResultSet rs = loadOptionComponent.executeQuery();
            while(rs.next()){
                //test stamp
                System.out.println("for travelOption "+ optionName +" component " +rs.getString("name")+" alla posizione "+ rs.getInt("posInTravelOption"));
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
    TreeSet<TravelOptionComponent> loadFromTransport(String groupID, String travelName, String optionName, TreeSet<TravelOptionComponent> travelOptions) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement loadOptionComponent = connection.prepareStatement("SELECT * FROM transport WHERE groupID = ? AND travelName = ? AND optionName=?")) {
            loadOptionComponent.setString(1, groupID);
            loadOptionComponent.setString(2, travelName);
            loadOptionComponent.setString(3, optionName);
            ResultSet rs = loadOptionComponent.executeQuery();
            while(rs.next()){
                //test stamp
                System.out.println("for travelOption "+ optionName +" component " +rs.getString("name")+" alla posizione "+ rs.getInt("posInTravelOption"));
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
                        rs.getString("fromPlace"),
                        rs.getString("toPlace"),
                        rs.getString("kindOfTransport")
                ));
            }
        }
        return travelOptions;
    }
    TreeSet<TravelOptionComponent> loadFromRental(String groupID, String travelName, String optionName, TreeSet<TravelOptionComponent> travelOptions) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement loadOptionComponent = connection.prepareStatement("SELECT * FROM rental WHERE groupID = ? AND travelName = ? AND optionName=?")) {
            loadOptionComponent.setString(1, groupID);
            loadOptionComponent.setString(2, travelName);
            loadOptionComponent.setString(3, optionName);
            ResultSet rs = loadOptionComponent.executeQuery();
            while(rs.next()){
                //test stamp
                System.out.println("for travelOption "+ optionName +" component " +rs.getString("name")+" alla posizione "+ rs.getInt("posInTravelOption"));
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
    /**
     * Given a travel(groupID, travelName), load its favouriteOption(from favouriteoptions table).
     * @param groupID group selected
     * @param travelName travel selected
     * @return favourite travelOption
     * @throws SQLException if connection fails
     */
    String loadFavouriteOption(String groupID, String travelName) throws SQLException {
        String favouriteOption=null;
        try (Connection connection = dataSource.getConnection();
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

    //to update->
    /**
     * Update travelStatus when its switchButton is clicked.
     * @param groupName groupID associated to the travel
     * @param travelName name of the travel
     * @param newStatus new status
     */
    public void updateTravelStatus(String groupName, String travelName, boolean newStatus){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement  updateTravelStatus= connection.prepareStatement("UPDATE travels SET status = ? WHERE groupID = ? AND travelName = ?")) {
            updateTravelStatus.setBoolean(1, newStatus);
            updateTravelStatus.setString(2, groupName);
            updateTravelStatus.setString(3, travelName);
            updateTravelStatus.executeUpdate();
            System.out.println(newStatus);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nError while updating travelStatus").showAndWait();
        }
    }

    public void updateTravelName(Travel travel, String newTravelName) throws SQLException {
        ArrayList<String> tables = new ArrayList<>();
        tables.add("travels");
        tables.add("traveloptions");
        tables.add("accommodation");
        tables.add("transport");
        tables.add("rental");
        for (String table : tables) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement updateTravelName = connection.prepareStatement("UPDATE " + table + " SET travelName=? WHERE groupID = ? AND travelName = ?")) {
                updateTravelName.setString(1, newTravelName);
                updateTravelName.setString(2, travel.getGroupID());
                updateTravelName.setString(3, travel.getTravelName());
                updateTravelName.executeUpdate();
            }

        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateTravelName = connection.prepareStatement("UPDATE favouriteoptions SET travelName = ?  WHERE groupID = ? AND travelName = ?")) {
            updateTravelName.setString(1, newTravelName);
            updateTravelName.setString(2, travel.getGroupID());
            updateTravelName.setString(3, travel.getTravelName());
            updateTravelName.executeUpdate();
        }
    }

    public void updateTravelOptionName(TravelOption travelOption, String newTravelOptionName) throws SQLException {
        ArrayList<String> tables = new ArrayList<>();
        tables.add("traveloptions");
        tables.add("accommodation");
        tables.add("transport");
        tables.add("rental");
        for (String table : tables) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement updateTravelName = connection.prepareStatement("UPDATE " + table + " SET optionName=? WHERE groupID = ? AND travelName = ? AND optionName = ?")) {
                updateTravelName.setString(1, newTravelOptionName);
                updateTravelName.setString(2, travelOption.getGroupID());
                updateTravelName.setString(3, travelOption.getTravelName());
                updateTravelName.setString(4, travelOption.getOptionName());
                updateTravelName.executeUpdate();
            }

        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateTravelName = connection.prepareStatement("UPDATE favouriteoptions SET optionName = ?  WHERE groupID = ? AND travelName = ? AND optionName = ?")) {
            updateTravelName.setString(1, newTravelOptionName);
            updateTravelName.setString(2, travelOption.getGroupID());
            updateTravelName.setString(3, travelOption.getTravelName());
            updateTravelName.setString(4, travelOption.getOptionName());
            updateTravelName.executeUpdate();
        }
    }

    //to delete->
    /**
     * Given a selected Travel, delete it from travels, traveloptions, accommodation, rental, transport, favouriteoptions tables.
     * @param travel to be deleted
     * @throws SQLException if connection fails
     */
    void deleteTravelDB(Travel travel) throws SQLException {
        ArrayList<String> tables= new ArrayList<>();
        tables.add("travels");
        tables.add("traveloptions");
        tables.add("accommodation");
        tables.add("transport");
        tables.add("rental");
        for (String table: tables) {
            try (Connection connection = dataSource.getConnection();
                PreparedStatement removeTravel = connection.prepareStatement("DELETE FROM "+table+" WHERE groupID = ? AND travelName = ?")){
                removeTravel.setString(1, travel.getGroupID());
                removeTravel.setString(2, travel.getTravelName());
                removeTravel.executeUpdate();
            }

        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement removeTravel = connection.prepareStatement("DELETE FROM favouriteoptions WHERE travelName = ?")) {
            removeTravel.setString(1, travel.getTravelName());
            removeTravel.executeUpdate();
        }
    }

    /**
     * Given a selected travelOption(groupID, travelName, optionName), delete it from traveloptions table.
     * @param groupID key1
     * @param travelName key2
     * @param optionName key3
     * @throws SQLException if connection fails
     */
    void deleteTravelOption(String groupID, String travelName, String optionName  ) throws SQLException {
        ArrayList<String> tables= new ArrayList<>();
        tables.add("traveloptions");
        tables.add("accommodation");
        tables.add("transport");
        tables.add("rental");
        for (String table: tables) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement removeTravelOption = connection.prepareStatement("DELETE FROM "+table+" WHERE groupID = ? AND travelName = ? AND optionName = ?")){
                removeTravelOption.setString(1, groupID);
                removeTravelOption.setString(2, travelName);
                removeTravelOption.setString(3, optionName);
                removeTravelOption.executeUpdate();
            }

        }
    }

    //To store->


    /**
     * Store new travel in travels table.
     * @param newTravel to be stored
     * @throws SQLException if connection leaks
     */
    void storeNewTravel(Travel newTravel) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertTravelOption = connection.prepareStatement("INSERT INTO travels (groupID, travelName, status) VALUES (?, ?, ?)")) {
            insertTravelOption.setString(1, newTravel.getGroupID());
            insertTravelOption.setString(2, newTravel.getTravelName());
            insertTravelOption.setBoolean(3, newTravel.getStatus());
            insertTravelOption.executeUpdate();
        }
    }
    /**
     * Store TravelOption in traveloptions
     * @param travelOption to be stored
     * @throws SQLException id connection fails
     */
    void  storeTravelOption(TravelOption travelOption) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertTravelOption = connection.prepareStatement("INSERT INTO traveloptions (groupID, travelName, optionName, totalCost, perPersonCost, comment) VALUES (?, ?, ?, ?, ?, ?)")) {
            insertTravelOption.setString(1, travelOption.getGroupID());
            insertTravelOption.setString(2, travelOption.getTravelName());
            insertTravelOption.setString(3, travelOption.getOptionName());
            insertTravelOption.setDouble(4, travelOption.getTotalCost());
            insertTravelOption.setDouble(5, travelOption.getPerPersonCost());
            insertTravelOption.setString(6, travelOption.getComment());
            insertTravelOption.executeUpdate();
        }
    }

    /**
     *Store optionComponents in accommodation, transport, rental
     */
    void storeAccommodation(TravelOptionComponent newComponent, String groupID, String travelName, String optionName) throws SQLException {
        System.out.println("Storing Accommodation in the database...");
        try(Connection connection = dataSource.getConnection();
            PreparedStatement insertAccommodation = connection.prepareStatement("INSERT INTO accommodation (groupID, travelName, optionName, posInTravelOption, name, checkInDay, checkInTime, checkOutTime, checkOutDay, numberOfRoom, privateToilet, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            insertAccommodation.setString(1, groupID);
            insertAccommodation.setString(2, travelName);
            insertAccommodation.setString(3, optionName);
            insertAccommodation.setInt(4, newComponent.getPosInTravelOption().get());
            if(newComponent.getComponentName().isPresent()) insertAccommodation.setString(5, newComponent.getComponentName().get());
            else insertAccommodation.setNull(5, Types.VARCHAR);

            if(newComponent.getCheckInDate().isPresent()) insertAccommodation.setDate(6, new java.sql.Date(newComponent.getCheckInDate().get().getTime()));
            else insertAccommodation.setNull(6, Types.DATE);

            if(newComponent.getCheckInTime().isPresent()) insertAccommodation.setTime(7, newComponent.getCheckInTime().get());
            else insertAccommodation.setNull(7, Types.TIME);

            if(newComponent.getCheckOutTime().isPresent()) insertAccommodation.setTime(8, newComponent.getCheckOutTime().get());
            else insertAccommodation.setNull(8, Types.TIME);

            if(newComponent.getCheckOutDate().isPresent()) insertAccommodation.setDate(9, new java.sql.Date(newComponent.getCheckOutDate().get().getTime()));
            else insertAccommodation.setNull(9, Types.DATE);

            if(newComponent.getNumberOfRooms().isPresent()) insertAccommodation.setInt(10, newComponent.getNumberOfRooms().get());
            else insertAccommodation.setNull(10, Types.INTEGER);

            if(newComponent.isPrivateToilet().isPresent()) insertAccommodation.setBoolean(11, newComponent.isPrivateToilet().get());
            else insertAccommodation.setNull(11, Types.BOOLEAN);

            if(newComponent.getPrice().isPresent()) insertAccommodation.setDouble(12, newComponent.getPrice().get());
            else insertAccommodation.setNull(12, Types.DOUBLE);

            insertAccommodation.executeUpdate();
        }

    }

    void storeTransport(TravelOptionComponent newComponent,String groupID, String travelName, String optionName) throws SQLException {
        try( Connection connection = dataSource.getConnection();
             PreparedStatement insertTransport = connection.prepareStatement("INSERT INTO transport (groupID, travelName, optionName, posInTravelOption, name, departureDay, departureTime, arrivalTime, arrivalDay, fromPlace, toPlace, kindOfTransport, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            insertTransport.setString(1, groupID);
            insertTransport.setString(2, travelName);
            insertTransport.setString(3, optionName);
            insertTransport.setInt(4, newComponent.getPosInTravelOption().get());
            if(newComponent.getComponentName().isPresent()) insertTransport.setString(5, newComponent.getComponentName().get());
            else insertTransport.setNull(5, Types.VARCHAR);

            if(newComponent.getCheckInDate().isPresent()) insertTransport.setDate(6, new java.sql.Date(newComponent.getCheckInDate().get().getTime()));
            else insertTransport.setNull(6, Types.DATE);

            if(newComponent.getCheckInTime().isPresent()) insertTransport.setTime(7, newComponent.getCheckInTime().get());
            else insertTransport.setNull(7, Types.TIME);

            if(newComponent.getCheckOutTime().isPresent()) insertTransport.setTime(8, newComponent.getCheckOutTime().get());
            else insertTransport.setNull(8, Types.TIME);

            if(newComponent.getCheckOutDate().isPresent()) insertTransport.setDate(9, new java.sql.Date(newComponent.getCheckOutDate().get().getTime()));
            else insertTransport.setNull(9, Types.DATE);

            if(newComponent.getFrom().isPresent()) insertTransport.setString(10, newComponent.getFrom().get());
            else insertTransport.setNull(10, Types.VARCHAR);

            if(newComponent.getTo().isPresent()) insertTransport.setString(11, newComponent.getTo().get());
            else insertTransport.setNull(11, Types.VARCHAR);

            if(newComponent.getKindOfTransport().isPresent()) insertTransport.setString(12, newComponent.getKindOfTransport().get());
            else insertTransport.setNull(12, Types.VARCHAR);

            if(newComponent.getPrice().isPresent()) insertTransport.setDouble(13, newComponent.getPrice().get());
            else insertTransport.setNull(13, Types.DOUBLE);
            insertTransport.executeUpdate();
        }
    }

    void storeRental(TravelOptionComponent newComponent,String groupID, String travelName, String optionName) throws SQLException {
        System.out.println("Storing Rental in the database...");
        try( Connection connection = dataSource.getConnection();
             PreparedStatement insertRental = connection.prepareStatement("INSERT INTO rental (groupID, travelName, optionName, posInTravelOption, name, checkInDay, checkInTime, checkOutTime, checkOutDay, kindOfRental, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            insertRental.setString(1, groupID);
            insertRental.setString(2, travelName);
            insertRental.setString(3, optionName);
            insertRental.setInt(4, newComponent.getPosInTravelOption().get());
            if(newComponent.getComponentName().isPresent()) insertRental.setString(5, newComponent.getComponentName().get());
            else insertRental.setString(5, null);

            if(newComponent.getComponentName().isPresent()) insertRental.setString(5, newComponent.getComponentName().get());
            else insertRental.setNull(5, Types.VARCHAR);

            if(newComponent.getCheckInDate().isPresent()) insertRental.setDate(6, new java.sql.Date(newComponent.getCheckInDate().get().getTime()));
            else insertRental.setNull(6, Types.DATE);

            if(newComponent.getCheckInTime().isPresent()) insertRental.setTime(7, newComponent.getCheckInTime().get());
            else insertRental.setNull(7, Types.TIME);

            if(newComponent.getCheckOutTime().isPresent()) insertRental.setTime(8, newComponent.getCheckOutTime().get());
            else insertRental.setNull(8, Types.TIME);

            if(newComponent.getCheckOutDate().isPresent()) insertRental.setDate(9, new java.sql.Date(newComponent.getCheckOutDate().get().getTime()));
            else insertRental.setNull(9, Types.DATE);

            if(newComponent.getKindOfRental().isPresent()) insertRental.setString(10, newComponent.getKindOfRental().get());
            else insertRental.setNull(10, Types.VARCHAR);

            if(newComponent.getPrice().isPresent()) insertRental.setDouble(11, newComponent.getPrice().get());
            else insertRental.setNull(11, Types.DOUBLE);
            insertRental.executeUpdate();
        }

    }

    /**
     * Store authentication data into authentication and usernames tables.
     * @param groupID group's name
     * @param password group's password
     * @param tf_userNames members' usernames of the group
     * @throws SQLException if connection fails
     */
    void storeAuthenticationData(String groupID, String password, LinkedList<TextField> tf_userNames) throws SQLException {
        try( Connection connection = dataSource.getConnection();
             PreparedStatement insertCredentials = connection.prepareStatement("INSERT INTO authentication (groupID, password) VALUES (?, ?)")){
            insertCredentials.setString(1, groupID);
            insertCredentials.setString(2, password);
            insertCredentials.executeUpdate();
        }
        try( Connection connection =dataSource.getConnection();
             PreparedStatement insertUsernames = connection.prepareStatement("INSERT INTO usernames (groupID, username) VALUES (?, ?)")) {
            for (TextField user: tf_userNames) {
                insertUsernames.setString(1, groupID);
                insertUsernames.setString(2, user.getText());
                insertUsernames.executeUpdate();
            }
        }
    }
}
