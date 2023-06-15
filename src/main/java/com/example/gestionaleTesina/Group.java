package com.example.gestionaleTesina;

import java.util.ArrayList;

public class Group {
    String groupID;
    String password;
    ArrayList<String> users;
    ArrayList<Travel> travelsToDo;
    ArrayList<Travel> travelsDone;

    public Group(String groupID, String password, ArrayList<String> users, ArrayList<Travel> travelsToDo, ArrayList<Travel> travelsDone) {
        this.groupID = groupID;
        this.password = password;
        this.users = users;
        this.travelsToDo = travelsToDo;
        this.travelsDone = travelsDone;
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

    public ArrayList<Travel> getTravelsToDo() {
        return travelsToDo;
    }

    public void setTravelsToDo(ArrayList<Travel> travelsToDo) {
        this.travelsToDo = travelsToDo;
    }

    public ArrayList<Travel> getTravelsDone() {
        return travelsDone;
    }

    public void setTravelsDone(ArrayList<Travel> travelsDone) {
        this.travelsDone = travelsDone;
    }
}
