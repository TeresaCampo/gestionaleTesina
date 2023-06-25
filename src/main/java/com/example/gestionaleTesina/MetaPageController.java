package com.example.gestionaleTesina;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class MetaPageController {
    DBConnection connector = new DBConnection();
    AddressApplication main = new AddressApplication();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button groupMembersButton;
    @FXML
    private Label lb_date;
    @FXML
    private Label lb_meta;
    @FXML
    private Label lb_option;
    @FXML
    private Label lb_price;
    @FXML
    private Label lb_pricePerson;
    @FXML
    private Button logoutButton;
    @FXML
    private Button newButton;
    @FXML
    private TableView<?> optionTable;
    @FXML
    private ScrollBar sb_options;
    @FXML
    private Button showDetailsButton;
    @FXML
    private TextArea ta_info;
    @FXML
    private TextField tf_comment;
    @FXML
    private TableColumn<?, ?> tv_tabOptions;
    @FXML
    void onLogoutButton(ActionEvent event) {}

    @FXML
    void onShowDetailsButton(ActionEvent event) {
        try {
            main.changeScene("editPage-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EDIT-PAGE NOT FOUND");
        }
    }
    @FXML
    void onNewButton(ActionEvent event) {
        try {
            main.changeScene("editPage-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EDIT-PAGE NOT FOUND");
        }
    }
    @FXML
    void onLogoutButton(){
        try {
            main.changeScene("login-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOGIN-PAGE NOT FOUND");
        }
    }

}
