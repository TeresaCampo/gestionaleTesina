package com.example.gestionaleTesina;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

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

    public void onPlusButton(){
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
            lb_message.setText("Please enter the usernames of all members");
       } else if (checkEqualUserNames!=tf_userNames.size()) {
            lb_message.setText("Please choose a distinct username for each member");

       } else if(tf_groupID.getText().isEmpty() || pf_password.getText().isEmpty() || pf_passwordRepeated.getText().isEmpty()){
            lb_message.setText("Incomplete credentials");
       }
       else{
            String password= pf_password.getText();
            if(!pf_passwordRepeated.getText().equals(password)){
                lb_message.setText("It seems like you entered two different passwords. Check it!");
            }
            //check if the user id is already present in the DB
            //success, insert data in the DB and come back to login page
           else {
                try {
                    AddressApplication main = new AddressApplication();
                    main.changeScene("login-view.fxml");

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
