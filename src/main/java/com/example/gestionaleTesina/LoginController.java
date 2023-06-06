package com.example.gestionaleTesina;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;

public class LoginController{
    DBConnection connector = new DBConnection();
    AddressApplication main = new AddressApplication();
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

    @FXML
    void onSignInButton(){
        if(IDGroupTextField.getText().isEmpty() || enterPasswordField.getText().isEmpty()){
            loginWarningLabel.setText("Please enter all your data");
        }
        else{
            String groupID = IDGroupTextField.getText();
            String password = enterPasswordField.getText();

            try{
                if(validateLogin(groupID, password)){
                    loginWarningLabel.setText("Loading...");
                }
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
            }
        }
    }
    @FXML
    void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

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

    @FXML
    public void onRegisterButton(){
        try {
            String signUpScene="signUp-view.fxml";
            main.changeScene(signUpScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

