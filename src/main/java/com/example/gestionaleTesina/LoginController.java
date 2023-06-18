package com.example.gestionaleTesina;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;

public class LoginController{
    DBConnection connector = new DBConnection();
    AddressApplication main = new AddressApplication();
    FirstPageController firstPageController;


    @FXML
    private TextField IDGroupTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Label loginWarningLabel;

    public void initialize(){
        connector.getConnection();

    }

    /**
     * To go to the Register page
     */
    @FXML
    public void onRegisterButton(){
        try {
            main.changeScene("signUp-view.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To close the app
     */
    @FXML
    void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * To signIn
     * Check data and eventually goes to First Page
     */

    @FXML
    void onSignInButton(){
        if(IDGroupTextField.getText().isEmpty() || enterPasswordField.getText().isEmpty()){
            loginWarningLabel.setText("Please enter all your data");
            return;
        }
        String groupID = IDGroupTextField.getText();
        String password = enterPasswordField.getText();

        try {
            if (!validateLogin(groupID, password))
                return;
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
        }

        loginWarningLabel.setText("Loading...");
        try {
            FXMLLoader loader=main.changeScene("firstPage-view.fxml");
            firstPageController= loader.getController();
            firstPageController.setGroup( new Group(groupID, password, new ArrayList<String>(), new ArrayList<Travel>()));
            firstPageController.setGroupID(groupID);
            firstPageController.setPassword(password);
            firstPageApp();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FIRST-PAGE NOT FOUND");
        }
    }

    /**
     * To check authentication data
     */
    private boolean validateLogin(String groupID, String password) throws SQLException {
        try (Connection connection = connector.dataSource.getConnection();
            PreparedStatement checkData = connection.prepareStatement("SELECT * FROM authentication WHERE groupID = ?")) {
            checkData.setString(1, groupID);
            ResultSet accountFound = checkData.executeQuery();

            if (!accountFound.isBeforeFirst()) {
                loginWarningLabel.setText("Wrong GroupID or Password");
                return false;
            } else {
                accountFound.next();
                if (!accountFound.getString("password").equals(password)) {
                    loginWarningLabel.setText("Wrong GroupID or Password");
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * To control the whole app
     */

    void firstPageApp(){
        firstPageController.loadData();




    }


}

