package com.example.gestionaleTesina;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.sql.*;
import java.util.LinkedList;


public class SignUpController {
    @FXML
    private TextField tf_groupID;
    @FXML
    private PasswordField pf_password;
    @FXML
    private PasswordField pf_passwordRepeated;
    @FXML
    private Label lb_message;
    @FXML
    private TextField tf_userName1;
    @FXML
    private Label lb_user1;
    @FXML
    private Button plusButton;
    @FXML
    private final Button removeButton= new Button();
    @FXML
    private AnchorPane gp_background;

    DBConnection database = new DBConnection();
    AddressApplication main = new AddressApplication();
    LinkedList<Label> lb_userNames= new LinkedList<>();
    LinkedList<TextField> tf_userNames= new LinkedList<>();
    int userNumber=1;

    public void initialize() {
        database.initializeConnection();

        tf_userNames.add(tf_userName1);
        lb_userNames.add(lb_user1);

        removeButton.setLayoutX(tf_userName1.getLayoutX()+tf_userName1.getPrefWidth()+10);
        removeButton.setLayoutY(tf_userName1.getLayoutY());
        removeButton.setText("-");
        removeButton.setPrefWidth(31);
        removeButton.setPrefHeight(30);
        removeButton.setOnAction(event -> {
            gp_background.getChildren().remove(tf_userNames.getLast());
            tf_userNames.remove(tf_userNames.getLast());
            gp_background.getChildren().remove(lb_userNames.getLast());
            lb_userNames.remove(lb_userNames.getLast());
            --userNumber;

            plusButton.setTranslateY(plusButton.getTranslateY()-30);
            removeButton.setTranslateY(removeButton.getTranslateY()-30);
            if(userNumber==1) {
                gp_background.getChildren().remove(removeButton);
            }
        });
    }
    @FXML
    public void onCancelButton(){
        try {
            String loginScene="login-view.fxml";
            database.dataSource.close();
            main.changeScene(loginScene);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOGIN-PAGE NOT FOUND");
        }
    }

    /**
     * Add labels and textFields for new usernames
     */
    @FXML
    void onPlusButton(){
        ++userNumber;
        plusButton.setTranslateY(plusButton.getTranslateY()+30);

        TextField tf_newUser= new TextField();
        tf_newUser.setLayoutX(tf_userName1.getLayoutX());
        tf_newUser.setLayoutY(tf_userNames.getLast().getLayoutY()+30);
        tf_newUser.setPrefSize(tf_userName1.getPrefWidth(), tf_userName1.getPrefHeight());
        tf_newUser.setStyle("-fx-background-color: #e7ebff; -fx-border-color: #022757; -fx-border-radius: 25px;");
        tf_newUser.setPromptText("Set your username " + userNumber);
        gp_background.getChildren().add(tf_newUser);
        tf_userNames.add(tf_newUser);

        removeButton.setTranslateY(removeButton.getTranslateY()+30);
        removeButton.setStyle("-fx-background-color: #eb7ccb; -fx-border-color: #d62090; -fx-border-radius: 25px; -fx-background-radius: 25px; -fx-font-size: 13px;");
        removeButton.setOpacity(0.78);
        if(userNumber==2) {
            gp_background.getChildren().add(removeButton);
        }

        Label lb_newUser= new Label("User "+userNumber);
        lb_newUser.setLayoutX(lb_user1.getLayoutX());
        lb_newUser.setLayoutY(lb_userNames.getLast().getLayoutY()+30);
        lb_newUser.setStyle("-fx-font-size: 13px;");
        lb_newUser.setTextFill(Paint.valueOf("#d63090"));
        lb_userNames.add(lb_newUser);
        gp_background.getChildren().add(lb_newUser);
    }

    /**
     * Check inserted data and eventually store in the database
     */
    @FXML
    void onSignUpButton() {
        String password= pf_password.getText();
        String groupID=tf_groupID.getText();

        if(checkEmptyFields())
            return;
        if(!checkLengthFields())
            return;

        if(!pf_passwordRepeated.getText().equals(password)){
            lb_message.setText("It seems like you entered two different passwords. Check it!");
            return;
        }

        try {
            if (checkGroupNameExists(groupID)) {
                lb_message.setText(groupID + " is already used, choose a different groupID");
                return;
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
        }

        try{
            database.storeAuthenticationData(groupID, password, tf_userNames);
            database.dataSource.close();
            main.changeScene("login-view.fxml");
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, "Database Error").showAndWait();
            removeChanges(groupID);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean checkLengthFields() {
        Boolean tooLongFiled=tf_userNames.stream()
                .map(user->user.getText().length())
                .anyMatch(length->length>20);
        if(tooLongFiled){
            lb_message.setText("Usernames must be at last 20 characters");
            return false;
        }

        if(tf_groupID.getText().length()>20){
            lb_message.setText("GroupID must be at last 20 characters");
            return false;
        }
        if(pf_password.getText().length()>20){
            lb_message.setText("Password must be at last 20 characters");
            return false;
        }
        return true;
    }

    private boolean checkEmptyFields(){
        long checkEmptyUserNames= tf_userNames.stream()
                .filter(user->user.getText().isEmpty())
                .count();

        long checkEqualUserNames= tf_userNames.stream()
                .map(element -> element.getText())
                .distinct()
                .count();

        if(checkEmptyUserNames!=0){
            lb_message.setText("Please enter the usernames of all members");
            return true;
        } else if (checkEqualUserNames!=tf_userNames.size()) {
            lb_message.setText("Please choose a distinct username for each member");
            return true;
        } else if(tf_groupID.getText().isEmpty() || pf_password.getText().isEmpty() || pf_passwordRepeated.getText().isEmpty()){
            lb_message.setText("Incomplete credentials");
            return true;
        }
        return false;
    }
    private boolean checkGroupNameExists(String groupID) throws SQLException {
        try (Connection connection = database.dataSource.getConnection();
             PreparedStatement checkData = connection.prepareStatement("SELECT * FROM authentication WHERE groupID = ?")) {
            checkData.setString(1, groupID);
            ResultSet accountFound = checkData.executeQuery();
            if (accountFound.isBeforeFirst()) {
                return true;
            }
            return false;
        }
    }

    /**
     * Remove changes to the database if storeAuthenticationData fails
     * @param groupID group to be created
     */
    private void removeChanges(String groupID) {
        try (Connection connection = database.dataSource.getConnection();
             PreparedStatement removeGroupAuthentication = connection.prepareStatement("DELETE FROM authentication WHERE groupID = ?")) {
            removeGroupAuthentication.setString(1, groupID);
            removeGroupAuthentication.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("FATAL ERROR, CHECK THE DATABASE authentication\nLAST GROUP ADDED IS "+groupID);
        }
        try (Connection connection = database.dataSource.getConnection();
             PreparedStatement removeGroupUsernames = connection.prepareStatement("DELETE FROM usernames WHERE groupID = ?")) {
            removeGroupUsernames.setString(1, groupID);
            removeGroupUsernames.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("FATAL ERROR, CHECK THE DATABASE usernames\nLAST GROUP ADDED IS "+groupID);
        }
    }
}
