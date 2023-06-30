package com.example.gestionaleTesina.classes;

import java.util.ArrayList;

public class Travel {
    String groupID;
    String travelName;
    ArrayList<TravelOption> options;
    String favouriteOption;
    boolean status;

    SwitchButton statusButton;
    Integer numberOfOptions;


    public Travel(String groupID, String travelName, ArrayList<TravelOption> options, String favouriteOption, boolean status, SwitchButton statusButton) {
        this.groupID=groupID;
        this.travelName = travelName;
        this.options = options;
        this.favouriteOption = favouriteOption;
        this.status=status;
        this.numberOfOptions=options.size();
        this.statusButton=statusButton;
    }

    /**
     * Create a new travel for editPage.
     * @param groupID group
     */
    public Travel(String groupID) {
        this.groupID = groupID;
    }

    /**
     * Create a Travel for firstPage (needs only travelName, statusButton)
     * @param travelName travel's name
     * @param numberOfOptions number of options for this travel
     * @param status travel's state
     * @param statusButton button to display travel's state
     */
    public Travel(String groupID, String travelName, Integer numberOfOptions, boolean status, SwitchButton statusButton) {
        this.groupID=groupID;
        this.travelName = travelName;
        this.numberOfOptions=numberOfOptions;
        this.status = status;
        this.statusButton=statusButton;
    }

    public String getFavouriteOption() {
        return favouriteOption;
    }

    public void setFavouriteOption(String favouriteOption) {
        this.favouriteOption = favouriteOption;
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

    @Override
    public String toString() {
        return "Travel{" +
                "travelName='" + travelName + '\'' +
                ", options=" + options +
                ", numberOfOptions=" + numberOfOptions +
                ", favouriteOption=" + favouriteOption +
                ", statusButton=" + statusButton +
                '}';
    }
}
