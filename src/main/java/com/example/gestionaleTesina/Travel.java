package com.example.gestionaleTesina;

import java.util.ArrayList;

public class Travel {
    String name;
    ArrayList<TravelOption> options;
    Integer numberOfOptions;
    String favouriteOption;
    SwitchButton statusButton;
    boolean status;

    void initialize(){
        //this.statusButton.setSelected(status);
    }

    public Travel(String name, ArrayList<TravelOption> options, String favouriteOption, boolean status, SwitchButton statusButton) {
        this.name = name;
        this.options = options;
        this.favouriteOption = favouriteOption;
        this.status=status;
        this.numberOfOptions=options.size();
        this.statusButton=statusButton;
    }

    public String getFavouriteOption() {
        return favouriteOption;
    }

    public void setFavouriteOption(String favouriteOption) {
        this.favouriteOption = favouriteOption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "name='" + name + '\'' +
                ", options=" + options +
                ", numberOfOptions=" + numberOfOptions +
                ", favouriteOption=" + favouriteOption +
                ", statusButton=" + statusButton +
                '}';
    }
}
