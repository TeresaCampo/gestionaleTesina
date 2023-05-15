package com.example.gestionaleTesina;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    LinkedList<Label> lb_userNames= new LinkedList<>();
    LinkedList<TextField> tf_userNames= new LinkedList<>();
    int userNumber=1;

    void initialize() {
        tf_userNames.add(tf_userName1);
        lb_userNames.add(lb_user1);

        removeButton.setLayoutX(tf_userName1.getLayoutX()+tf_userName1.getPrefWidth()+10);
        removeButton.setLayoutY(tf_userName1.getLayoutY());
        removeButton.setText("-");
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

    public void onPlusButton(){
        ++userNumber;
        plusButton.setTranslateY(plusButton.getTranslateY()+30);

        TextField tf_newUser= new TextField();
        tf_newUser.setLayoutX(tf_userName1.getLayoutX());
        tf_newUser.setLayoutY(tf_userNames.getLast().getLayoutY()+30);
        tf_newUser.setPrefSize(tf_userName1.getPrefWidth(), tf_userName1.getPrefHeight());
        gp_background.getChildren().add(tf_newUser);
        tf_userNames.add(tf_newUser);

        removeButton.setTranslateY(removeButton.getTranslateY()+30);
        if(userNumber==2) {
            gp_background.getChildren().add(removeButton);
        }


        Label lb_newUser= new Label("User "+userNumber);
        lb_newUser.setLayoutX(lb_user1.getLayoutX());
        lb_newUser.setLayoutY(lb_userNames.getLast().getLayoutY()+30);
        lb_userNames.add(lb_newUser);
        gp_background.getChildren().add(lb_newUser);
    }

    public void onSignUpButton(){
        //check if there is at least one userName and if all the userName are compiled and if all the userNames are different
        //check if all the data are insert
        //check if password and repeated password are equal

        long checkEmptyUserNames= tf_userNames.stream()
                .filter(user->user.getText().isEmpty())
                .count();

       long checkEqualUserNames= tf_userNames.stream()
               .map(element -> element.getText())
               .distinct()
               .count();

       if(checkEmptyUserNames!=0){
            lb_message.setText("Give a username to all the group members");
       } else if (checkEqualUserNames!=tf_userNames.size()) {
            lb_message.setText("Select a distinct username for each group member");

       } else if(tf_groupID.getText().isEmpty() || pf_password.getText().isEmpty() || pf_passwordRepeated.getText().isEmpty()){
            lb_message.setText("Insert all your authentication data");
       }
       else{
            String password= pf_password.getText();
            if(!pf_passwordRepeated.getText().equals(password)){
                lb_message.setText("Ops...two different password, type it again");
            }
            //check if the user id is already present in the DB
            //success, insert data in the DB and come back to login page
           try {
               String signUpScene="login-view.fxml";
               AddressApplication main= new AddressApplication();
               main.changeScene(signUpScene);

           } catch (Exception e) {
               e.printStackTrace();
           }
       }

    }

    public void onCancelButton(){
        try {
            String loginScene="login-view.fxml";
            AddressApplication main= new AddressApplication();
            main.changeScene(loginScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
