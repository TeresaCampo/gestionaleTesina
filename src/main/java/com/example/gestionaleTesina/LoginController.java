package com.example.gestionaleTesina;

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

    DBConnection database = new DBConnection();
    AddressApplication main = new AddressApplication();
    FirstPageController firstPageController;

    public void initialize() {
        database.initializeConnection();
    }

    /**
     * Display signUpPage.
     */
    @FXML
    public void onRegisterButton() {
        try {
            main.changeScene("signUp-view.fxml");
            database.dataSource.close();
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
            if (!validateLogin(groupID, password)) return;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
        }

        loginWarningLabel.setText("Loading...");
        try {
            FXMLLoader loader = main.changeScene("firstPage-view.fxml");
            firstPageController = loader.getController();
            firstPageController.setGroup(new Group(groupID, password, new ArrayList<>(), new ArrayList<>()));
            firstPageController.setDatabase(database);
            firstPageController.loadData();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FIRST-PAGE NOT FOUND");
        }
    }

    /**
     * To check authentication data.
     */
    private boolean validateLogin(String groupID, String password) throws SQLException {
        try (Connection connection = database.dataSource.getConnection();
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

}

