package com.example.gestionaleTesina.classes;

import java.sql.Time;
import java.util.Date;

public class TravelOptionComponent {
    //key
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

    /**
     * Constructor for accommodation
     */
    public TravelOptionComponent(String groupID, String travelName, String optionName, Integer posInTravelOption, String payed, Double price, String name, Date checkInDate, Date checkOutDate, Time checkInTime, Time checkOutTime, Integer numberOfRooms, boolean privateToilet) {
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
    public TravelOptionComponent(String groupID, String travelName, String optionName, Integer posInTravelOption, String payed, Double price, String name, Date checkInDate, Date checkOutDate, Time checkInTime, Time checkOutTime, String from, String to) {
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

        this.from=from;
        this.to=to;
    }

    /**
     * Constructor for rental
     */
    public TravelOptionComponent(String groupID, String travelName, String optionName, Integer posInTravelOption, String payed, Double price, String name, Date checkInDate, Date checkOutDate, Time checkInTime, Time checkOutTime, String kindOfRental) {
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

    public Integer getPosInTravelOption() {
        return posInTravelOption;
    }

    public void setPosInTravelOption(Integer posInTravelOption) {
        this.posInTravelOption = posInTravelOption;
    }

    public String getPayed() {
        return payed;
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Time getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Time checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Time getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Time checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isPrivateToilet() {
        return privateToilet;
    }

    public void setPrivateToilet(boolean privateToilet) {
        this.privateToilet = privateToilet;
    }

    public String getKindOfRental() {
        return kindOfRental;
    }

    public void setKindOfRental(String kindOfRental) {
        this.kindOfRental = kindOfRental;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


}

