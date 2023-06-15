package com.example.gestionaleTesina;

public class TravelOption {
    String name;
    double totalCost;
    double perPersonCost;
    String comment;

    public TravelOption(String name, double totalCost, double perPersonCost, String comment) {
        this.name = name;
        this.totalCost = totalCost;
        this.perPersonCost = perPersonCost;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
