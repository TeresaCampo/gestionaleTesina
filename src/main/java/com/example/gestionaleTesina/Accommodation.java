package com.example.gestionaleTesina;

import java.sql.Time;
import java.util.Date;

public class Accommodation extends TravelOptionComponent{

    public Accommodation(String groupID, String travelName, String optionName, Integer posInTravelOption, String payed, Double price, String name, Date checkInDate, Date checkOutDate, Time chechInTime, Time chechOutTime, Integer numberOfRooms, boolean privateToilet) {
        super(groupID, travelName, optionName, posInTravelOption, payed, price, name, checkInDate, checkOutDate, chechInTime, chechOutTime);
        this.numberOfRooms=numberOfRooms;
        this.privateToilet=privateToilet;
    }
}
