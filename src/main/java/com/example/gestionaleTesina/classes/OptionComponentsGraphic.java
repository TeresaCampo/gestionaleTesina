package com.example.gestionaleTesina.classes;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class OptionComponentsGraphic extends Node {
    int layoutX;
    int layoutY;

    private AnchorPane background;

    private RadioButton bt_sharedWC;

    private DatePicker dp_from;

    private DatePicker dp_to;

    private Label lb_checkIn;

    private Label lb_checkOut;

    private Label lb_from;

    private Label lb_nOfRoom;

    private Label lb_payedBy;

    private Label lb_price;

    private Label lb_to;
    private TextField tf_name;

    private TextField tf_hourCheckIn;

    private TextField tf_hourCheckOut;

    private TextField tf_minuteCheckIn;
    private TextField tf_minuteCheckOut;
    private TextField tf_nOfRoom;

    private TextField tf_price;

    private Label tf_timeCheckIn;

    private Label tf_timeCheckOut;
    javafx.scene.control.Label lb_kindOfComponent;
    private TextField tf_kindRental;

    /**
     * constructor: accommodation not initialised
     */
    public OptionComponentsGraphic(int layoutX, int layoutY, AnchorPane background, String component) {
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.background=background;

        if(component.equals("accommodation")){
        addAccommodation();
        }
        if(component.equals("rental")){
            addRental();
        }
        if(component.equals("transport")){
            addTransport();
        }
    }

    void addAccommodation(){
        //first line
        lb_kindOfComponent=new javafx.scene.control.Label("Accommodation");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(96);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Name of the accommodation/url");
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);
        tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);

        lb_from=new Label("from");
        lb_from.setPrefWidth(25);
        lb_from.setLayoutX(tf_name.getLayoutX()); lb_from.setLayoutY(tf_name.getLayoutY()+35);
        background.getChildren().add(lb_from);

        dp_from=new DatePicker();
        dp_from.setPrefWidth(114);
        dp_from.setLayoutX(tf_name.getLayoutX()+lb_from.getPrefWidth()+10);    dp_from.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_from);

        lb_to=new Label("to");
        lb_to.setPrefWidth(15);
        lb_to.setLayoutX(dp_from.getLayoutX()+dp_from.getPrefWidth()+10);   lb_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(lb_to);

        dp_to=new DatePicker();
        dp_to.setPrefWidth(114);
        dp_to.setLayoutX(lb_to.getLayoutX()+lb_to.getPrefWidth()+10);    dp_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_to);

        //second line
        lb_checkIn=new Label("checkInTime");
        lb_checkIn.setPrefWidth(70);
        lb_checkIn.setLayoutX(tf_name.getLayoutX());    lb_checkIn.setLayoutY(lb_from.getLayoutY()+35);
        background.getChildren().add(lb_checkIn);

        tf_hourCheckIn=new TextField();
        tf_hourCheckIn.setPrefWidth(18);
        tf_hourCheckIn.setLayoutX(lb_checkIn.getLayoutX()+lb_checkIn.getPrefWidth()+34); tf_hourCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckIn);

        tf_timeCheckIn=new Label(":");
        tf_timeCheckIn.setPrefWidth(5);
        tf_timeCheckIn.setLayoutX(tf_hourCheckIn.getLayoutX()+tf_hourCheckIn.getPrefWidth()+5); tf_timeCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckIn);

        tf_minuteCheckIn=new TextField();
        tf_minuteCheckIn.setPrefWidth(18);
        tf_minuteCheckIn.setLayoutX(tf_timeCheckIn.getLayoutX()+tf_timeCheckIn.getPrefWidth()); tf_minuteCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckIn);

        lb_checkOut=new Label("checkOutTime");
        lb_checkOut.setPrefWidth(80);
        lb_checkOut.setLayoutX(tf_minuteCheckIn.getLayoutX()+tf_minuteCheckIn.getPrefWidth()+10);    lb_checkOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(lb_checkOut);

        tf_hourCheckOut=new TextField();
        tf_hourCheckOut.setPrefWidth(18);
        tf_hourCheckOut.setLayoutX(lb_checkOut.getLayoutX()+lb_checkOut.getPrefWidth()+10); tf_hourCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckOut);

        tf_timeCheckOut=new Label(":");
        tf_timeCheckOut.setPrefWidth(5);
        tf_timeCheckOut.setLayoutX(tf_hourCheckOut.getLayoutX()+tf_hourCheckOut.getPrefWidth()+5); tf_timeCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckOut);

        tf_minuteCheckOut=new TextField();
        tf_minuteCheckOut.setPrefWidth(18);
        tf_minuteCheckOut.setLayoutX(tf_timeCheckOut.getLayoutX()+tf_timeCheckOut.getPrefWidth()); tf_minuteCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckOut);

        //third line
        lb_nOfRoom=new Label("n° of room");
        lb_nOfRoom.setPrefWidth(60);
        lb_nOfRoom.setLayoutX(tf_name.getLayoutX());    lb_nOfRoom.setLayoutY(lb_checkIn.getLayoutY()+35);
        background.getChildren().add(lb_nOfRoom);

        tf_nOfRoom=new TextField();
        tf_nOfRoom.setPrefWidth(82);
        tf_nOfRoom.setLayoutX(lb_nOfRoom.getLayoutX()+lb_nOfRoom.getPrefWidth()+10);    tf_nOfRoom.setLayoutY(lb_nOfRoom.getLayoutY());
        background.getChildren().add(tf_nOfRoom);

        bt_sharedWC=new RadioButton("shared toilet");
        bt_sharedWC.setPrefWidth(100);
        bt_sharedWC.setLayoutX(tf_nOfRoom.getLayoutX()+tf_nOfRoom.getPrefWidth()+10);    bt_sharedWC.setLayoutY(lb_nOfRoom.getLayoutY()+5);
        background.getChildren().add(bt_sharedWC);

        //fourth line
        lb_price=new Label("price");
        lb_price.setPrefWidth(30);
        lb_price.setLayoutX(tf_name.getLayoutX());    lb_price.setLayoutY(lb_nOfRoom.getLayoutY()+35);
        background.getChildren().add(lb_price);

        tf_price=new TextField();
        tf_price.setPrefWidth(106);
        tf_price.setLayoutX(lb_price.getLayoutX()+lb_price.getPrefWidth()+10);    tf_price.setLayoutY(lb_nOfRoom.getLayoutY()+35);
        background.getChildren().add(tf_price);
    }

    void addRental(){
        //first line
        lb_kindOfComponent=new javafx.scene.control.Label("Rental");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(96);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Name of the rental store/url");
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);   tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);

        //second line
        tf_kindRental=new TextField("Kind of rental");
        tf_kindRental.setLayoutX(tf_name.getLayoutX());
        tf_kindRental.setLayoutY(tf_name.getLayoutY()+35);
        background.getChildren().add(tf_kindRental);

        //third line
        lb_from=new Label("from");
        lb_from.setPrefWidth(25);
        lb_from.setLayoutX(tf_name.getLayoutX()); lb_from.setLayoutY(tf_kindRental.getLayoutY()+35);
        background.getChildren().add(lb_from);

        dp_from=new DatePicker();
        dp_from.setPrefWidth(114);
        dp_from.setLayoutX(tf_name.getLayoutX()+lb_from.getPrefWidth()+10);    dp_from.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_from);

        lb_to=new Label("to");
        lb_to.setPrefWidth(15);
        lb_to.setLayoutX(dp_from.getLayoutX()+dp_from.getPrefWidth()+10);   lb_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(lb_to);

        dp_to=new DatePicker();
        dp_to.setPrefWidth(114);
        dp_to.setLayoutX(lb_to.getLayoutX()+lb_to.getPrefWidth()+10);    dp_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_to);

        //fourth line
        lb_checkIn=new Label("checkInTime");
        lb_checkIn.setPrefWidth(70);
        lb_checkIn.setLayoutX(tf_name.getLayoutX());    lb_checkIn.setLayoutY(lb_from.getLayoutY()+35);
        background.getChildren().add(lb_checkIn);

        tf_hourCheckIn=new TextField();
        tf_hourCheckIn.setPrefWidth(18);
        tf_hourCheckIn.setLayoutX(lb_checkIn.getLayoutX()+lb_checkIn.getPrefWidth()+34); tf_hourCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckIn);

        tf_timeCheckIn=new Label(":");
        tf_timeCheckIn.setPrefWidth(5);
        tf_timeCheckIn.setLayoutX(tf_hourCheckIn.getLayoutX()+tf_hourCheckIn.getPrefWidth()+5); tf_timeCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckIn);

        tf_minuteCheckIn=new TextField();
        tf_minuteCheckIn.setPrefWidth(18);
        tf_minuteCheckIn.setLayoutX(tf_timeCheckIn.getLayoutX()+tf_timeCheckIn.getPrefWidth()); tf_minuteCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckIn);

        lb_checkOut=new Label("checkOutTime");
        lb_checkOut.setPrefWidth(80);
        lb_checkOut.setLayoutX(tf_minuteCheckIn.getLayoutX()+tf_minuteCheckIn.getPrefWidth()+10);    lb_checkOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(lb_checkOut);

        tf_hourCheckOut=new TextField();
        tf_hourCheckOut.setPrefWidth(18);
        tf_hourCheckOut.setLayoutX(lb_checkOut.getLayoutX()+lb_checkOut.getPrefWidth()+10); tf_hourCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckOut);

        tf_timeCheckOut=new Label(":");
        tf_timeCheckOut.setPrefWidth(5);
        tf_timeCheckOut.setLayoutX(tf_hourCheckOut.getLayoutX()+tf_hourCheckOut.getPrefWidth()+5); tf_timeCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckOut);

        tf_minuteCheckOut=new TextField();
        tf_minuteCheckOut.setPrefWidth(18);
        tf_minuteCheckOut.setLayoutX(tf_timeCheckOut.getLayoutX()+tf_timeCheckOut.getPrefWidth()); tf_minuteCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckOut);

        //fifth line
        lb_price=new Label("price");
        lb_price.setPrefWidth(30);
        lb_price.setLayoutX(tf_name.getLayoutX());    lb_price.setLayoutY(lb_checkIn.getLayoutY()+35);
        background.getChildren().add(lb_price);

        tf_price=new TextField();
        tf_price.setPrefWidth(106);
        tf_price.setLayoutX(lb_price.getLayoutX()+lb_price.getPrefWidth()+10);    tf_price.setLayoutY(lb_price.getLayoutY());
        background.getChildren().add(tf_price);
    }

    void addTransport(){
        //first line
        lb_kindOfComponent=new javafx.scene.control.Label("Transport");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(96);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Name of the transport/url");
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);   tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);

        //second line
        tf_kindRental=new TextField("Kind of transport");
        tf_kindRental.setLayoutX(tf_name.getLayoutX());
        tf_kindRental.setLayoutY(tf_name.getLayoutY()+35);
        background.getChildren().add(tf_kindRental);

        //third line
        lb_from=new Label("from");
        lb_from.setPrefWidth(25);
        lb_from.setLayoutX(tf_name.getLayoutX()); lb_from.setLayoutY(tf_kindRental.getLayoutY()+35);
        background.getChildren().add(lb_from);

        dp_from=new DatePicker();
        dp_from.setPrefWidth(114);
        dp_from.setLayoutX(tf_name.getLayoutX()+lb_from.getPrefWidth()+10);    dp_from.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_from);

        lb_to=new Label("to");
        lb_to.setPrefWidth(15);
        lb_to.setLayoutX(dp_from.getLayoutX()+dp_from.getPrefWidth()+10);   lb_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(lb_to);

        dp_to=new DatePicker();
        dp_to.setPrefWidth(114);
        dp_to.setLayoutX(lb_to.getLayoutX()+lb_to.getPrefWidth()+10);    dp_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_to);

        //fourth line
        lb_checkIn=new Label("departure time");
        lb_checkIn.setPrefWidth(80);
        lb_checkIn.setLayoutX(tf_name.getLayoutX());    lb_checkIn.setLayoutY(lb_from.getLayoutY()+35);
        background.getChildren().add(lb_checkIn);

        tf_hourCheckIn=new TextField();
        tf_hourCheckIn.setPrefWidth(18);
        tf_hourCheckIn.setLayoutX(lb_checkIn.getLayoutX()+lb_checkIn.getPrefWidth()+34); tf_hourCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckIn);

        tf_timeCheckIn=new Label(":");
        tf_timeCheckIn.setPrefWidth(5);
        tf_timeCheckIn.setLayoutX(tf_hourCheckIn.getLayoutX()+tf_hourCheckIn.getPrefWidth()+5); tf_timeCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckIn);

        tf_minuteCheckIn=new TextField();
        tf_minuteCheckIn.setPrefWidth(18);
        tf_minuteCheckIn.setLayoutX(tf_timeCheckIn.getLayoutX()+tf_timeCheckIn.getPrefWidth()); tf_minuteCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckIn);

        lb_checkOut=new Label("arrival time");
        lb_checkOut.setPrefWidth(80);
        lb_checkOut.setLayoutX(tf_minuteCheckIn.getLayoutX()+tf_minuteCheckIn.getPrefWidth()+10);    lb_checkOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(lb_checkOut);

        tf_hourCheckOut=new TextField();
        tf_hourCheckOut.setPrefWidth(18);
        tf_hourCheckOut.setLayoutX(lb_checkOut.getLayoutX()+lb_checkOut.getPrefWidth()+10); tf_hourCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckOut);

        tf_timeCheckOut=new Label(":");
        tf_timeCheckOut.setPrefWidth(5);
        tf_timeCheckOut.setLayoutX(tf_hourCheckOut.getLayoutX()+tf_hourCheckOut.getPrefWidth()+5); tf_timeCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckOut);

        tf_minuteCheckOut=new TextField();
        tf_minuteCheckOut.setPrefWidth(18);
        tf_minuteCheckOut.setLayoutX(tf_timeCheckOut.getLayoutX()+tf_timeCheckOut.getPrefWidth()); tf_minuteCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckOut);

        //fifth line
        lb_price=new Label("price");
        lb_price.setPrefWidth(30);
        lb_price.setLayoutX(tf_name.getLayoutX());    lb_price.setLayoutY(lb_checkIn.getLayoutY()+35);
        background.getChildren().add(lb_price);

        tf_price=new TextField();
        tf_price.setPrefWidth(106);
        tf_price.setLayoutX(lb_price.getLayoutX()+lb_price.getPrefWidth()+10);    tf_price.setLayoutY(lb_price.getLayoutY());
        background.getChildren().add(tf_price);
    }


    /**
     * getter and setter
     */
    public TextField getTf_price() {
        return tf_price;
    }
}
