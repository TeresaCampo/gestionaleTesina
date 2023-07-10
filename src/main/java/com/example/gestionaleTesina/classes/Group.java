package com.example.gestionaleTesina.classes;

import java.util.ArrayList;

public class Group {
    private String groupID;
    private String password;
    private ArrayList<String> users;
    private ArrayList<Travel> travels;

    public Group(String groupID, String password, ArrayList<String> users, ArrayList<Travel> travels) {
        this.groupID = groupID;
        this.password = password;
        this.users = users;
        this.travels = travels;
    }

    public void addUser(String toBeAdded) {
        users.add(toBeAdded);
    }

    public void addTravel(Travel toBeAdded) {
        travels.add(toBeAdded);
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList<Travel> getTravels() {
        return travels;
    }

    public void setTravels(ArrayList<Travel> travels) {
        this.travels = travels;
    }
}
