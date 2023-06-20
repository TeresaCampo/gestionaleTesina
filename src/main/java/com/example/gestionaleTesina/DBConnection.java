package com.example.gestionaleTesina;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.TimeZone;

public class DBConnection {
    public HikariDataSource dataSource;
    public void getConnection() {
        String schemeName="progettojavafx";
        String userName="progettojavafx";
        String password="siSperabene!";
        String JDBC_Driver_MySQL = "com.mysql.cj.jdbc.Driver";
        String JDBC_URL_MySQL = "jdbc:mysql://localhost:3306/"+schemeName+"?user="+userName+"&password="+password+"&serverTimezone=" + TimeZone.getDefault().getID();

        System.out.println("- dbConnection()...");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(JDBC_Driver_MySQL);
        config.setJdbcUrl(JDBC_URL_MySQL);
        config.setLeakDetectionThreshold(2000);
        dataSource = new HikariDataSource(config);
    }


}
