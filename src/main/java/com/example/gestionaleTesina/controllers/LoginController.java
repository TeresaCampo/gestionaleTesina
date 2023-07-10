package com.example.gestionaleTesina.controllers;

import com.example.gestionaleTesina.AddressApplication;
import com.example.gestionaleTesina.classes.Group;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private TextField IDGroupTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Label loginWarningLabel;

    private DBConnection database = new DBConnection();
    private AddressApplication main = new AddressApplication();

    public void initialize() {
        if (database.dataSource == null)
            database.initializeConnection();
        MyTextField.maxLen20(IDGroupTextField);
    }

    /**
     * Display signUpPage.
     */
    @FXML
    public void onRegisterButton() {
        try {
            FXMLLoader loader = main.changeScene("signUp-view.fxml");
            SignUpController signUpController = loader.getController();
            signUpController.setDatabase(database);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SIGNUP-PAGE NOT FOUND");
        }
    }

    /**
     * Close application.
     */
    @FXML
    void onCancelButton() {
        database.dataSource.close();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * SignIn: check inserted data and eventually display firstPage.
     */
    @FXML
    void onSignInButton() {
        if (IDGroupTextField.getText().isEmpty() || enterPasswordField.getText().isEmpty()) {
            loginWarningLabel.setText("Please enter all your data");
            return;
        }

        String groupID = IDGroupTextField.getText();
        String password = enterPasswordField.getText();
        try {
            if (!validateLogin(groupID, password))
                return;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
        }

        loginWarningLabel.setText("Loading...");
        try {
            FXMLLoader loader = main.changeScene("firstPage-view.fxml");
            FirstPageController firstPageController = loader.getController();
            firstPageController.setGroup(new Group(groupID, password, new ArrayList<>(), new ArrayList<>()));
            firstPageController.setDatabase(database);
            firstPageController.loadData();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FIRST-PAGE NOT FOUND");
        }
    }

    /**
     * Check authentication data.
     *
     * @param groupID groupID to be checked
     * @param password password to be checked
     *
     * @return true if account exists and password matches it
     *
     * @throws SQLException if connection leaks
     */
    private boolean validateLogin(String groupID, String password) throws SQLException {
        try (Connection connection = database.dataSource.getConnection(); PreparedStatement checkData = connection.prepareStatement("SELECT * FROM authentication WHERE groupID = ?")) {
            checkData.setString(1, groupID);
            ResultSet accountFound = checkData.executeQuery();

            if (!accountFound.isBeforeFirst()) {
                loginWarningLabel.setText("Wrong GroupID or Password");
                return false;
            }

            accountFound.next();
            if (!accountFound.getString("password").equals(password)) {
                loginWarningLabel.setText("Wrong GroupID or Password");
                return false;
            }

            return true;
        }
    }

    //getter and setter

    public void setDatabase(DBConnection database) {
        this.database = database;
    }
}

