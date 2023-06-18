package com.example.gestionaleTesina;

import java.sql.Time;
import java.util.Date;

public class Transport extends TravelOptionComponent {

    public Transport(String groupID, String travelName, String optionName, Integer posInTravelOption, String payed, Double price, String name, Date checkInDate, Date checkOutDate, Time chechInTime, Time chechOutTime, String from, String to) {
        super(groupID, travelName, optionName, posInTravelOption, payed, price, name, checkInDate, checkOutDate, chechInTime, chechOutTime);
        this.from=from;
        this.to=to;
    }
}
