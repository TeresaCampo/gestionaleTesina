package com.example.gestionaleTesina.classes;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;

public class TravelOptionComponent {
    //key
    String componentName;
    String groupID;
    String travelName;
    String optionName;
    Integer posInTravelOption;
    String payed;
    Double price;

    //common one
    String name;
    Date checkInDate;
    Date checkOutDate;
    Time checkInTime;
    Time checkOutTime;
    //Additional one
    Integer numberOfRooms;
    boolean privateToilet;
    String kindOfRental;
    String from;
    String to;
    String kindOfTransport;

    /**
     * Constructor for accommodation
     */
    public TravelOptionComponent(String componentName,String groupID, String travelName, String optionName, Integer posInTravelOption, String payed, Double price, String name, Date checkInDate, Date checkOutDate, Time checkInTime, Time checkOutTime, Integer numberOfRooms, boolean privateToilet) {
        this.componentName=componentName;
        this.groupID = groupID;
        this.travelName = travelName;
        this.optionName = optionName;
        this.posInTravelOption = posInTravelOption;
        this.payed = payed;
        this.price = price;
        this.name = name;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;

        this.numberOfRooms=numberOfRooms;
        this.privateToilet=privateToilet;
    }


    /**
     * Constructor for travel
     */
    public TravelOptionComponent(String componentName, String groupID, String travelName, String optionName, Integer posInTravelOption, String payed, Double price, String name, Date checkInDate, Date checkOutDate, Time checkInTime, Time checkOutTime, String from, String to, String kindOfTransport) {
        this.componentName=componentName;
        this.groupID = groupID;
        this.travelName = travelName;
        this.optionName = optionName;
        this.posInTravelOption = posInTravelOption;
        this.payed = payed;
        this.price = price;
        this.name = name;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;

        this.kindOfTransport=kindOfTransport;
        this.from=from;
        this.to=to;
    }

    /**
     * Constructor for rental
     */
    public TravelOptionComponent(String componentName, String groupID, String travelName, String optionName, Integer posInTravelOption, String payed, Double price, String name, Date checkInDate, Date checkOutDate, Time checkInTime, Time checkOutTime, String kindOfRental) {
        this.componentName=componentName;
        this.groupID = groupID;
        this.travelName = travelName;
        this.optionName = optionName;
        this.posInTravelOption = posInTravelOption;
        this.payed = payed;
        this.price = price;
        this.name = name;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;

        this.kindOfRental=kindOfRental;
    }



    public Optional<String> getGroupID() {
        return Optional.ofNullable(groupID);
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public Optional<String> getTravelName() {
        return Optional.ofNullable(travelName);
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public Optional<String> getOptionName() {
        return Optional.ofNullable(optionName);
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Optional<Integer> getPosInTravelOption() {
        return Optional.ofNullable(posInTravelOption);
    }

    public void setPosInTravelOption(Integer posInTravelOption) {
        this.posInTravelOption = posInTravelOption;
    }

    public Optional<String> getPayed() {
        return Optional.ofNullable(payed);
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }

    public Optional<Double> getPrice() {
        return Optional.ofNullable(price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Date> getCheckInDate() {
        return Optional.ofNullable(checkInDate);
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Optional<Date> getCheckOutDate() {
        return Optional.ofNullable(checkOutDate);
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Optional<Time> getCheckInTime() {
        return Optional.ofNullable(checkInTime);
    }

    public void setCheckInTime(Time checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Optional<Time> getCheckOutTime() {
        return Optional.ofNullable(checkOutTime);
    }

    public void setCheckOutTime(Time checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Optional<Integer> getNumberOfRooms() {
        return Optional.ofNullable(numberOfRooms);
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Optional<Boolean> isPrivateToilet() {
        return Optional.ofNullable(privateToilet);
    }

    public void setPrivateToilet(boolean privateToilet) {
        this.privateToilet = privateToilet;
    }

    public Optional<String> getKindOfRental() {
        return Optional.ofNullable(kindOfRental);
    }

    public void setKindOfRental(String kindOfRental) {
        this.kindOfRental = kindOfRental;
    }

    public Optional<String> getFrom() {
        return Optional.ofNullable(from);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Optional<String> getTo() {
        return Optional.ofNullable(to);
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Optional<String> getComponentName() {
        return Optional.ofNullable(componentName);
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Optional<String> getKindOfTransport() {
        return Optional.ofNullable(kindOfTransport);
    }

    public void setKindOfTransport(String kindOfTransport) {
        this.kindOfTransport = kindOfTransport;
    }
}

