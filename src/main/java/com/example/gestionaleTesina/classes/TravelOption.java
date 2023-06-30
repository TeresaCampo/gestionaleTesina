package com.example.gestionaleTesina.classes;

import java.util.Comparator;
import java.util.TreeSet;

public class TravelOption {
    private String groupID;
    private String travelName;
    private String optionName;
    private double totalCost;
    private double perPersonCost;
    private String comment;
    private TreeSet<TravelOptionComponent> components= new TreeSet<>(Comparator.comparing((TravelOptionComponent e) -> e.getPosInTravelOption().get()));

    //constructor
    public TravelOption(String groupID, String travelName, String optionName, double totalCost, double perPersonCost, String comment, TreeSet<TravelOptionComponent> components) {
        this.groupID = groupID;
        this.travelName = travelName;
        this.optionName = optionName;
        this.totalCost = totalCost;
        this.perPersonCost = perPersonCost;
        this.comment = comment;
        this.components = components;
    }

    //constructor to test
    public TravelOption(String optionName, double totalCost, TreeSet<TravelOptionComponent> components) {
        this.optionName = optionName;
        this.totalCost = totalCost;
        this.components = components;
    }

    //getter and setter
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getPerPersonCost() {
        return perPersonCost;
    }

    public void setPerPersonCost(double perPersonCost) {
        this.perPersonCost = perPersonCost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TreeSet<TravelOptionComponent> getComponents() {
        return components;
    }

    public void setComponents(TreeSet<TravelOptionComponent> components) {
        this.components = components;
    }
}
