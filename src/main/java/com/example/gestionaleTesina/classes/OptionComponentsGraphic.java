package com.example.gestionaleTesina.classes;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.ZoneId;


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
    private TextField tf_fromPlace;
    private TextField tf_toPlace;
    private Label lb_toPlace;

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

    /**
     * To show rental layout
     */
    void addAccommodation(){
        //1st line
        lb_kindOfComponent=new javafx.scene.control.Label("Accommodation");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(96);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Name of the accommodation/url");
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);
        tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);

        //2nd line
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
        dp_to.setPrefWidth(135);
        dp_to.setLayoutX(lb_to.getLayoutX()+lb_to.getPrefWidth()+10);    dp_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_to);

        //3rd line
        lb_checkIn=new Label("checkInTime");
        lb_checkIn.setPrefWidth(70);
        lb_checkIn.setLayoutX(tf_name.getLayoutX());    lb_checkIn.setLayoutY(lb_from.getLayoutY()+35);
        background.getChildren().add(lb_checkIn);

        tf_hourCheckIn=new TextField();
        tf_hourCheckIn.setPrefWidth(30);
        tf_hourCheckIn.setLayoutX(lb_checkIn.getLayoutX()+lb_checkIn.getPrefWidth()+10); tf_hourCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckIn);

        tf_timeCheckIn=new Label(":");
        tf_timeCheckIn.setPrefWidth(5);
        tf_timeCheckIn.setLayoutX(tf_hourCheckIn.getLayoutX()+tf_hourCheckIn.getPrefWidth()+5); tf_timeCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckIn);

        tf_minuteCheckIn=new TextField();
        tf_minuteCheckIn.setPrefWidth(30);
        tf_minuteCheckIn.setLayoutX(tf_timeCheckIn.getLayoutX()+tf_timeCheckIn.getPrefWidth()); tf_minuteCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckIn);

        lb_checkOut=new Label("checkOutTime");
        lb_checkOut.setPrefWidth(80);
        lb_checkOut.setLayoutX(tf_minuteCheckIn.getLayoutX()+tf_minuteCheckIn.getPrefWidth()+10);    lb_checkOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(lb_checkOut);

        tf_hourCheckOut=new TextField();
        tf_hourCheckOut.setPrefWidth(30);
        tf_hourCheckOut.setLayoutX(lb_checkOut.getLayoutX()+lb_checkOut.getPrefWidth()+10); tf_hourCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckOut);

        tf_timeCheckOut=new Label(":");
        tf_timeCheckOut.setPrefWidth(5);
        tf_timeCheckOut.setLayoutX(tf_hourCheckOut.getLayoutX()+tf_hourCheckOut.getPrefWidth()+5); tf_timeCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckOut);

        tf_minuteCheckOut=new TextField();
        tf_minuteCheckOut.setPrefWidth(30);
        tf_minuteCheckOut.setLayoutX(tf_timeCheckOut.getLayoutX()+tf_timeCheckOut.getPrefWidth()); tf_minuteCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckOut);

        //4th line
        lb_nOfRoom=new Label("n° of room");
        lb_nOfRoom.setPrefWidth(60);
        lb_nOfRoom.setLayoutX(tf_name.getLayoutX());    lb_nOfRoom.setLayoutY(lb_checkIn.getLayoutY()+35);
        background.getChildren().add(lb_nOfRoom);

        tf_nOfRoom=new TextField();
        tf_nOfRoom.setPrefWidth(82);
        tf_nOfRoom.setLayoutX(lb_nOfRoom.getLayoutX()+lb_nOfRoom.getPrefWidth()+10);    tf_nOfRoom.setLayoutY(lb_nOfRoom.getLayoutY());
        background.getChildren().add(tf_nOfRoom);

        bt_sharedWC=new RadioButton("private toilet");
        bt_sharedWC.setPrefWidth(100);
        bt_sharedWC.setLayoutX(tf_nOfRoom.getLayoutX()+tf_nOfRoom.getPrefWidth()+10);    bt_sharedWC.setLayoutY(lb_nOfRoom.getLayoutY()+5);
        background.getChildren().add(bt_sharedWC);

        //5th line
        lb_price=new Label("price");
        lb_price.setPrefWidth(30);
        lb_price.setLayoutX(tf_name.getLayoutX());    lb_price.setLayoutY(lb_nOfRoom.getLayoutY()+35);
        background.getChildren().add(lb_price);

        tf_price=new TextField();
        tf_price.setPrefWidth(106);
        tf_price.setLayoutX(lb_price.getLayoutX()+lb_price.getPrefWidth()+10);    tf_price.setLayoutY(lb_nOfRoom.getLayoutY()+35);
        background.getChildren().add(tf_price);
    }

    /**
     * To show rental layout
     */
    void addRental(){
        //1st line
        lb_kindOfComponent=new javafx.scene.control.Label("Rental");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(96);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Name of the rental store/url");
        tf_name.setPrefWidth(150);
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);   tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);

        tf_kindRental=new TextField("Kind of rental");
        tf_kindRental.setPrefWidth(160);
        tf_kindRental.setLayoutX(tf_name.getLayoutX()+tf_name.getPrefWidth()+10);   tf_kindRental.setLayoutY(tf_name.getLayoutY());
        background.getChildren().add(tf_kindRental);

        //2nd line
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
        dp_to.setPrefWidth(135);
        dp_to.setLayoutX(lb_to.getLayoutX()+lb_to.getPrefWidth()+10);    dp_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_to);

        //3rd line
        lb_checkIn=new Label("checkInTime");
        lb_checkIn.setPrefWidth(70);
        lb_checkIn.setLayoutX(tf_name.getLayoutX());    lb_checkIn.setLayoutY(lb_from.getLayoutY()+35);
        background.getChildren().add(lb_checkIn);

        tf_hourCheckIn=new TextField();
        tf_hourCheckIn.setPrefWidth(30);
        tf_hourCheckIn.setLayoutX(lb_checkIn.getLayoutX()+lb_checkIn.getPrefWidth()+10); tf_hourCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckIn);

        tf_timeCheckIn=new Label(":");
        tf_timeCheckIn.setPrefWidth(5);
        tf_timeCheckIn.setLayoutX(tf_hourCheckIn.getLayoutX()+tf_hourCheckIn.getPrefWidth()+5); tf_timeCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckIn);

        tf_minuteCheckIn=new TextField();
        tf_minuteCheckIn.setPrefWidth(30);
        tf_minuteCheckIn.setLayoutX(tf_timeCheckIn.getLayoutX()+tf_timeCheckIn.getPrefWidth()); tf_minuteCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckIn);

        lb_checkOut=new Label("checkOutTime");
        lb_checkOut.setPrefWidth(80);
        lb_checkOut.setLayoutX(tf_minuteCheckIn.getLayoutX()+tf_minuteCheckIn.getPrefWidth()+10);    lb_checkOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(lb_checkOut);

        tf_hourCheckOut=new TextField();
        tf_hourCheckOut.setPrefWidth(30);
        tf_hourCheckOut.setLayoutX(lb_checkOut.getLayoutX()+lb_checkOut.getPrefWidth()+10); tf_hourCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckOut);

        tf_timeCheckOut=new Label(":");
        tf_timeCheckOut.setPrefWidth(5);
        tf_timeCheckOut.setLayoutX(tf_hourCheckOut.getLayoutX()+tf_hourCheckOut.getPrefWidth()+5); tf_timeCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckOut);

        tf_minuteCheckOut=new TextField();
        tf_minuteCheckOut.setPrefWidth(30);
        tf_minuteCheckOut.setLayoutX(tf_timeCheckOut.getLayoutX()+tf_timeCheckOut.getPrefWidth()); tf_minuteCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckOut);

        //4th line
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
        //1st line
        lb_kindOfComponent=new javafx.scene.control.Label("Transport");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(96);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Name of the transport/url");
        tf_name.setPrefWidth(150);
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);   tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);

        tf_kindRental=new TextField("Kind of transport");
        tf_kindRental.setLayoutX(tf_name.getLayoutX()+tf_name.getPrefWidth()+10);   tf_kindRental.setLayoutY(tf_name.getLayoutY());
        background.getChildren().add(tf_kindRental);

        //2nd line
        tf_fromPlace=new TextField("from place");
        tf_fromPlace.setPrefWidth(140);
        tf_fromPlace.setLayoutX(tf_name.getLayoutX());  tf_fromPlace.setLayoutY(tf_name.getLayoutY()+35);
        background.getChildren().add(tf_fromPlace);

        lb_toPlace=new Label("->");
        lb_toPlace.setPrefWidth(15);
        lb_toPlace.setLayoutX(tf_fromPlace.getLayoutX()+tf_fromPlace.getPrefWidth()+5);  lb_toPlace.setLayoutY(tf_fromPlace.getLayoutY());
        background.getChildren().add(lb_toPlace);

        tf_toPlace=new TextField("to place");
        tf_toPlace.setPrefWidth(140);
        tf_toPlace.setLayoutX(lb_toPlace.getLayoutX()+lb_toPlace.getPrefWidth()+10);  tf_toPlace.setLayoutY(tf_fromPlace.getLayoutY());
        background.getChildren().add(tf_toPlace);

        //3rd line
        lb_from=new Label("from");
        lb_from.setPrefWidth(25);
        lb_from.setLayoutX(tf_name.getLayoutX()); lb_from.setLayoutY(tf_fromPlace.getLayoutY()+35);
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
        dp_to.setPrefWidth(125);
        dp_to.setLayoutX(lb_to.getLayoutX()+lb_to.getPrefWidth()+10);    dp_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_to);

        //4th line
        lb_checkIn=new Label("departure time");
        lb_checkIn.setPrefWidth(80);
        lb_checkIn.setLayoutX(tf_name.getLayoutX());    lb_checkIn.setLayoutY(lb_from.getLayoutY()+35);
        background.getChildren().add(lb_checkIn);

        tf_hourCheckIn=new TextField();
        tf_hourCheckIn.setPrefWidth(30);
        tf_hourCheckIn.setLayoutX(lb_checkIn.getLayoutX()+lb_checkIn.getPrefWidth()+10); tf_hourCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckIn);

        tf_timeCheckIn=new Label(":");
        tf_timeCheckIn.setPrefWidth(5);
        tf_timeCheckIn.setLayoutX(tf_hourCheckIn.getLayoutX()+tf_hourCheckIn.getPrefWidth()+5); tf_timeCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckIn);

        tf_minuteCheckIn=new TextField();
        tf_minuteCheckIn.setPrefWidth(30);
        tf_minuteCheckIn.setLayoutX(tf_timeCheckIn.getLayoutX()+tf_timeCheckIn.getPrefWidth()); tf_minuteCheckIn.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckIn);

        lb_checkOut=new Label("arrival time");
        lb_checkOut.setPrefWidth(60);
        lb_checkOut.setLayoutX(tf_minuteCheckIn.getLayoutX()+tf_minuteCheckIn.getPrefWidth()+10);    lb_checkOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(lb_checkOut);

        tf_hourCheckOut=new TextField();
        tf_hourCheckOut.setPrefWidth(30);
        tf_hourCheckOut.setLayoutX(lb_checkOut.getLayoutX()+lb_checkOut.getPrefWidth()+10); tf_hourCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_hourCheckOut);

        tf_timeCheckOut=new Label(":");
        tf_timeCheckOut.setPrefWidth(5);
        tf_timeCheckOut.setLayoutX(tf_hourCheckOut.getLayoutX()+tf_hourCheckOut.getPrefWidth()+5); tf_timeCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_timeCheckOut);

        tf_minuteCheckOut=new TextField();
        tf_minuteCheckOut.setPrefWidth(30);
        tf_minuteCheckOut.setLayoutX(tf_timeCheckOut.getLayoutX()+tf_timeCheckOut.getPrefWidth()); tf_minuteCheckOut.setLayoutY(lb_checkIn.getLayoutY());
        background.getChildren().add(tf_minuteCheckOut);

        //5th line
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
     * To initialize Accommodation layout
     * @param componentInfo contains information with which initialize the layout
     */
    public void initializeAccommodation(TravelOptionComponent componentInfo){
        //1st line
        componentInfo.getName().ifPresent(h->tf_name.setText(componentInfo.getName().get()));
        //2nd line
        componentInfo.getCheckInDate().ifPresent(h->dp_from.setValue(componentInfo.getCheckInDate().get().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        componentInfo.getCheckOutDate().ifPresent(h->dp_to.setValue(componentInfo.getCheckOutDate().get().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        //3rd
        componentInfo.getCheckInTime().ifPresent(h-> {
            tf_hourCheckIn.setText(String.valueOf(componentInfo.getCheckInTime().get().getHours()));
            tf_minuteCheckIn.setText(String.valueOf(componentInfo.getCheckInTime().get().getMinutes()));
        });
        componentInfo.getCheckOutTime().ifPresent(h-> {
            tf_hourCheckOut.setText(String.valueOf(componentInfo.getCheckOutTime().get().getHours()));
            tf_minuteCheckOut.setText(String.valueOf(componentInfo.getCheckOutTime().get().getMinutes()));
        });
        //4th line
        componentInfo.getNumberOfRooms().ifPresent(h->tf_nOfRoom.setText(String.valueOf(componentInfo.getNumberOfRooms().get())));
        componentInfo.isPrivateToilet().ifPresent(h->bt_sharedWC.setSelected(componentInfo.isPrivateToilet().get()));
        //5th line
        componentInfo.getPrice().ifPresent(h->tf_price.setText(String.valueOf(componentInfo.getPrice().get())));
    }
    public void initializeRental(TravelOptionComponent componentInfo){
        //1st line
        componentInfo.getName().ifPresent(h->tf_name.setText(componentInfo.getName().get()));
        componentInfo.getKindOfRental().ifPresent(h->tf_kindRental.setText(componentInfo.getKindOfRental().get()));
        //2nd line
        componentInfo.getCheckInDate().ifPresent(h->dp_from.setValue(componentInfo.getCheckInDate().get().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        componentInfo.getCheckOutDate().ifPresent(h->dp_to.setValue(componentInfo.getCheckOutDate().get().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        //3rd line
        componentInfo.getCheckInTime().ifPresent(h-> {
            tf_hourCheckIn.setText(String.valueOf(componentInfo.getCheckInTime().get().getHours()));
            tf_minuteCheckIn.setText(String.valueOf(componentInfo.getCheckInTime().get().getMinutes()));
        });
        componentInfo.getCheckOutTime().ifPresent(h-> {
            tf_hourCheckOut.setText(String.valueOf(componentInfo.getCheckOutTime().get().getHours()));
            tf_minuteCheckOut.setText(String.valueOf(componentInfo.getCheckOutTime().get().getMinutes()));
        });
        //4th line
        componentInfo.getPrice().ifPresent(h->tf_price.setText(String.valueOf(componentInfo.getPrice().get())));
    }
    public void initializeTransport(TravelOptionComponent componentInfo){
        //1st line
        componentInfo.getName().ifPresent(h->tf_name.setText(componentInfo.getName().get()));
        componentInfo.getKindOfTransport().ifPresent(h->tf_kindRental.setText(componentInfo.getKindOfTransport().get()));
        //2nd line
        componentInfo.getFrom().ifPresent(h->tf_fromPlace.setText(componentInfo.getFrom().get()));
        componentInfo.getTo().ifPresent(h->tf_toPlace.setText(componentInfo.getTo().get()));
        //3rd line
        componentInfo.getCheckInDate().ifPresent(h->dp_from.setValue(componentInfo.getCheckInDate().get().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        componentInfo.getCheckOutDate().ifPresent(h->dp_to.setValue(componentInfo.getCheckOutDate().get().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        //4rd line
        componentInfo.getCheckInTime().ifPresent(h-> {
            tf_hourCheckIn.setText(String.valueOf(componentInfo.getCheckInTime().get().getHours()));
            tf_minuteCheckIn.setText(String.valueOf(componentInfo.getCheckInTime().get().getMinutes()));
        });
        componentInfo.getCheckOutTime().ifPresent(h-> {
            tf_hourCheckOut.setText(String.valueOf(componentInfo.getCheckOutTime().get().getHours()));
            tf_minuteCheckOut.setText(String.valueOf(componentInfo.getCheckOutTime().get().getMinutes()));
        });
        //5ht line
        componentInfo.getPrice().ifPresent(h->tf_price.setText(String.valueOf(componentInfo.getPrice().get())));
    }

    /**
     * getter and setter
     */
    public TextField getTf_price() {
        return tf_price;
    }
}
