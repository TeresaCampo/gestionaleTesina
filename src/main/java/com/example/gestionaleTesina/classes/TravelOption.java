package com.example.gestionaleTesina.classes;

import java.util.Comparator;
import java.util.TreeSet;

public class TravelOption {
    String name;
    double totalCost;
    double perPersonCost;
    String comment;
    TreeSet<TravelOptionComponent> components= new TreeSet<>(Comparator.comparing((TravelOptionComponent e) -> e.getPosInTravelOption().get()));

    public TravelOption(String name, double totalCost, double perPersonCost, String comment, TreeSet<TravelOptionComponent> components) {
        this.name = name;
        this.totalCost = totalCost;
        this.perPersonCost = perPersonCost;
        this.comment = comment;
        this.components = components;
    }
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

    public TreeSet<TravelOptionComponent> getComponents() {
        return components;
    }

    public void setComponents(TreeSet<TravelOptionComponent> components) {
        this.components = components;
    }
}
