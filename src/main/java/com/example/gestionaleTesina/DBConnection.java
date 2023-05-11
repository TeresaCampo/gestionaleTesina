package com.example.gestionaleTesina;

import java.sql.*;
import java.util.TimeZone;

public class DBConnection {
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String schemeName="progettojavafx";
        String userName="progettojavafx";
        String password="siSperaBene!";

        System.out.println("-Trying to get connection...");
        Connection connection= DriverManager.getConnection( "jdbc:mysql://localhost:3306/"+schemeName+"?user="+userName+"&password="+password+"&serverTimezone=" + TimeZone.getDefault().getID());
        System.out.println("-DB connected!");
        return connection;
    }

    public void testDB() {
        try {
            DBConnection doConnection = new DBConnection();
            Connection connection = doConnection.getConnection();
            PreparedStatement testAuthentication = connection.prepareStatement("SELECT * FROM authentication");
            ResultSet ret = testAuthentication.executeQuery();

            while (ret.next()) {
                System.out.println(ret.getString("groupID") + ret.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
