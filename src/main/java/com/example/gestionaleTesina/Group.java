package com.example.gestionaleTesina;

import java.util.ArrayList;

public class Group {
    String groupID;
    String password;
    ArrayList<String> users;
    ArrayList<Travel> travels;

    public Group(String groupID, String password, ArrayList<String> users, ArrayList<Travel> travels) {
        this.groupID = groupID;
        this.password = password;
        this.users = users;
        this.travels = travels;
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
