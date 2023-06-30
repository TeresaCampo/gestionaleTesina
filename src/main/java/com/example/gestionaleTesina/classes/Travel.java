package com.example.gestionaleTesina.classes;

import java.util.ArrayList;

public class Travel {
    private String groupID;
    private String travelName;
    private ArrayList<TravelOption> options;
    private String favouriteOption;
    private boolean status;
    private SwitchButton statusButton;
    private Integer numberOfOptions;

    /**
     * Create new travel for editPage.
     */
    public Travel(String groupID) {
        this.groupID = groupID;
    }

    /**
     * Create travel for firstPage
     */
    public Travel(String groupID, String travelName, Integer numberOfOptions, boolean status, SwitchButton statusButton) {
        this.groupID=groupID;
        this.travelName = travelName;
        this.numberOfOptions=numberOfOptions;
        this.status = status;
        this.statusButton=statusButton;
        statusButton.switchOnProperty().addListener(((observable, oldValue, newValue) -> this.status=statusButton.switchOnProperty().get()));
    }

    /**
     * Create travel for EditPage
     */

    public Travel(String groupID, String travelName) {
        this.groupID = groupID;
        this.travelName = travelName;
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
