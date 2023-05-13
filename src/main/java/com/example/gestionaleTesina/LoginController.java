package com.example.gestionaleTesina;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginController {

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

    public void onLoginButton() {
        AddressApplication main = new AddressApplication();
        DBConnection doConnection = new DBConnection();

        if (!pf_password.getText().isEmpty() && !tf_groupID.getText().isEmpty()) {
            String groupID = tf_groupID.getText();
            String password = pf_password.getText();

            //check in the database
            try {
                Connection connection = doConnection.getConnection();
                PreparedStatement checkData = connection.prepareStatement("SELECT * FROM authentication WHERE groupID = ?");
                checkData.setString(1, groupID);
                ResultSet accountFound = checkData.executeQuery();

                if (!accountFound.isBeforeFirst()) {  //if there are no results, this groupID doesn't correspond to any account
                    lb_alert.setText("Wrong GroupID or password");
                }
                else{   //if there is a result, check the password
                    accountFound.next();
                    if(!accountFound.getString(password).equals(password)){
                        lb_alert.setText("Wrong GroupID or password");
                    }
                    else{
                        //main.changeScene();  load the following stage, we haven't done it yet
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        if (tf_groupID.getText().isEmpty() || pf_password.getText().isEmpty()) {
            lb_alert.setText("Please enter all your data");
        }
    }

    public void onSignUpButton(){
        try {
            String signUpScene="singUp-view.fxml";
            AddressApplication main= new AddressApplication();
            main.changeScene(signUpScene);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}