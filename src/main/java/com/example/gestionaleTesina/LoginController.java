package com.example.gestionaleTesina;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {
    DBConnection connector= new DBConnection();
    AddressApplication main= new AddressApplication();
    @FXML
    private Button bt_login;
    @FXML
    private Button bt_signUp;
    @FXML
    private Label lb_alert;
    @FXML
    private PasswordField pf_password;
    @FXML
    private TextField tf_groupID;

    public void initialize(){
        //set the datasource for the connection
        connector.getConnection();
    }

    public void onLoginButton() {
        if (tf_groupID.getText().isEmpty() || pf_password.getText().isEmpty()) {
            lb_alert.setText("Please enter all your data");
        }
        else{
            String groupID = tf_groupID.getText();
            String password = pf_password.getText();

            try {
                if(checkAuthenticationData(groupID, password)){
                    lb_alert.setText("Loading your account...");
                    System.out.println("Correct authentication data, loading the account...");
                    //main.changeScene();  load the following stage, we haven't done it yet
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
            }
        }
    }

    private boolean checkAuthenticationData(String groupID, String password) throws SQLException {
        try(Connection connection=connector.dataSource.getConnection();
            PreparedStatement checkData = connection.prepareStatement("SELECT * FROM authentication WHERE groupID = ?")) {
            checkData.setString(1, groupID);
            ResultSet accountFound = checkData.executeQuery();


            if (!accountFound.isBeforeFirst()) {  //if there are no results, this groupID doesn't correspond to any account
                lb_alert.setText("Wrong GroupID or password");
                return false;
            } else {   //if there is a result, check the password
                accountFound.next();
                if (!accountFound.getString("password").equals(password)) {
                    lb_alert.setText("Wrong GroupID or password");
                    return false;
                } else {   //if the inserted data are ok
                    return true;
                }
            }
        }
    }

    public void onSignUpButton(){
        try {
            String signUpScene="singUp-view.fxml";
            main.changeScene(signUpScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}