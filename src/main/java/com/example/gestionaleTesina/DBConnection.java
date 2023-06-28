package com.example.gestionaleTesina;

import com.example.gestionaleTesina.classes.TravelOption;
import com.example.gestionaleTesina.classes.TravelOptionComponent;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.TimeZone;

public class DBConnection {
    public HikariDataSource dataSource;
    public void initialize(){
        getConnection();
    }
    public void getConnection() {
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
    void deleteTravelOption(String groupID, String travelName, String optionName  ) throws SQLException {
    try (Connection connection = dataSource.getConnection();
        PreparedStatement removeExistingOptionAccommodation = connection.prepareStatement("DELETE FROM accommodation WHERE groupID = ? AND travelName = ? AND optionName = ?");
        PreparedStatement removeExistingOptionRental = connection.prepareStatement("DELETE FROM rental WHERE groupID = ? AND travelName = ? AND optionName = ?");
        PreparedStatement removeExistingOptionTransport = connection.prepareStatement("DELETE FROM transport WHERE groupID = ? AND travelName = ? AND optionName = ?");
        PreparedStatement removeExistingOption = connection.prepareStatement("DELETE FROM traveloptions WHERE groupID = ? AND travelName = ? AND optionName = ?")
    ) {
            removeExistingOptionAccommodation.setString(1, groupID);
            removeExistingOptionAccommodation.setString(2, travelName);
            removeExistingOptionAccommodation.setString(3, optionName);
            removeExistingOptionAccommodation.executeUpdate();

            removeExistingOptionRental.setString(1, groupID);
            removeExistingOptionRental.setString(2, travelName);
            removeExistingOptionRental.setString(3, optionName);
            removeExistingOptionRental.executeUpdate();

            removeExistingOptionTransport.setString(1, groupID);
            removeExistingOptionTransport.setString(2, travelName);
            removeExistingOptionTransport.setString(3, optionName);
            removeExistingOptionTransport.executeUpdate();

            removeExistingOption.setString(1, groupID);
            removeExistingOption.setString(2, travelName);
            removeExistingOption.setString(3, optionName);
            removeExistingOption.executeUpdate();
        }
    }
    void  storeTravelOption(String groupID, String travelName, TravelOption travelOption) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertTravelOption = connection.prepareStatement("INSERT INTO traveloptions (groupID, travelName, optionName, totalCost, perPersonCost, comment) VALUES (?, ?, ?, ?, ?, ?)")) {
            insertTravelOption.setString(1, groupID);
            insertTravelOption.setString(2, travelName);
            insertTravelOption.setString(3, travelOption.getName());
            insertTravelOption.setDouble(4, travelOption.getTotalCost());
            insertTravelOption.setDouble(5, travelOption.getPerPersonCost());
            insertTravelOption.setString(6, travelOption.getComment());
            insertTravelOption.executeUpdate();
        }
    }
}
