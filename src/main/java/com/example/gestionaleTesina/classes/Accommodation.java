package com.example.gestionaleTesina.classes;

import com.example.gestionaleTesina.controllers.DBConnection;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class Accommodation extends TravelOptionComponent{
    private Optional<Integer> numberOfRooms;
    private Optional<Boolean> privateToilet;

    private TextField tf_nOfRoom;
    private RadioButton bt_sharedWC;

    public Accommodation(String componentName, String groupID, String travelName, String optionName, Integer posInTravelOption, Double price, String name, LocalDate checkInDate, LocalDate checkOutDate, LocalTime checkInTime, LocalTime checkOutTime, DBConnection database, Integer numberOfRooms, Boolean privateToilet) {
        super(componentName, groupID, travelName, optionName, posInTravelOption, price, name, checkInDate, checkOutDate, checkInTime, checkOutTime, database);
        this.numberOfRooms=Optional.ofNullable(numberOfRooms);
        this.privateToilet=Optional.ofNullable(privateToilet);
        System.out.println("database set in accommodation"+ database);
    }

    @Override
    public void addEmptyGraphicComponent(int layoutX, int layoutY, AnchorPane background) {
        addAccommodationGraphicComponent(layoutX,layoutY,background);

        bindCommonGraphicElementToAttributes();
        tf_nOfRoom.textProperty().addListener((observable, oldValue, newValue) -> numberOfRooms=Optional.ofNullable(converterTF_integer(tf_nOfRoom)));
        bt_sharedWC.armedProperty().addListener((observable, oldValue, newValue) -> privateToilet=Optional.ofNullable(newValue));
    }

    @Override
    public void addInitializedGraphicComponent(int layoutX, int layoutY, AnchorPane background) {
        addAccommodationGraphicComponent(layoutX,layoutY,background);

        initializeCommonGraphicComponent();
        numberOfRooms.ifPresent(h->tf_nOfRoom.setText(String.valueOf(numberOfRooms.get())));
        privateToilet.ifPresent(h->bt_sharedWC.setSelected(privateToilet.get()));

        bindCommonGraphicElementToAttributes();
        tf_nOfRoom.textProperty().addListener((observable, oldValue, newValue) -> numberOfRooms=Optional.ofNullable(converterTF_integer(tf_nOfRoom)));
        bt_sharedWC.armedProperty().addListener((observable, oldValue, newValue) -> privateToilet=Optional.ofNullable(newValue));
    }

    @Override
    public void storeInDB() {
        try {
            database.storeAccommodation(this);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing accommodation"+name.orElse("")).showAndWait();
        }
    }

    void addAccommodationGraphicComponent(int layoutX, int layoutY, AnchorPane background){
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.background=background;

        //1st line
        lb_kindOfComponent=new javafx.scene.control.Label("Accommodation");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(96);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Accommodation Name/Url");
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);
        tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);
        tf_name.setPrefWidth(200);

        //2nd line
        lb_from=new Label("From");
        lb_from.setPrefWidth(35);
        lb_from.setLayoutX(tf_name.getLayoutX()); lb_from.setLayoutY(tf_name.getLayoutY()+35);
        background.getChildren().add(lb_from);

        dp_from=new DatePicker();
        dp_from.setStyle("-fx-background-color: #E7EBFF");
        dp_from.setPrefWidth(114);
        dp_from.setLayoutX(tf_name.getLayoutX()+lb_from.getPrefWidth()+10);    dp_from.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_from);

        lb_to=new Label("To");
        lb_to.setPrefWidth(15);
        lb_to.setLayoutX(dp_from.getLayoutX()+dp_from.getPrefWidth()+10);   lb_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(lb_to);

        dp_to=new DatePicker();
        dp_to.setStyle("-fx-background-color: #E7EBFF");
        dp_to.setPrefWidth(135);
        dp_to.setLayoutX(lb_to.getLayoutX()+lb_to.getPrefWidth()+10);    dp_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_to);

        //3rd line
        lb_checkIn=new Label("Check in");
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

        lb_checkOut=new Label("Check out");
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
        lb_nOfRoom=new Label("N° of rooms");
        lb_nOfRoom.setPrefWidth(75);
        lb_nOfRoom.setLayoutX(tf_name.getLayoutX());    lb_nOfRoom.setLayoutY(lb_checkIn.getLayoutY()+35);
        background.getChildren().add(lb_nOfRoom);

        tf_nOfRoom=new TextField();
        tf_nOfRoom.setPrefWidth(82);
        tf_nOfRoom.setLayoutX(lb_nOfRoom.getLayoutX()+lb_nOfRoom.getPrefWidth()+10);    tf_nOfRoom.setLayoutY(lb_nOfRoom.getLayoutY());
        background.getChildren().add(tf_nOfRoom);

        bt_sharedWC=new RadioButton("Private Toilet");
        bt_sharedWC.setPrefWidth(100);
        bt_sharedWC.setLayoutX(tf_nOfRoom.getLayoutX()+tf_nOfRoom.getPrefWidth()+10);    bt_sharedWC.setLayoutY(lb_nOfRoom.getLayoutY()+5);
        background.getChildren().add(bt_sharedWC);

        //5th line
        lb_price=new Label("Price");
        lb_price.setPrefWidth(30);
        lb_price.setLayoutX(tf_name.getLayoutX());    lb_price.setLayoutY(lb_nOfRoom.getLayoutY()+35);
        background.getChildren().add(lb_price);

        tf_price=new TextField();
        tf_price.setPrefWidth(106);
        tf_price.setLayoutX(lb_price.getLayoutX()+lb_price.getPrefWidth()+10);    tf_price.setLayoutY(lb_nOfRoom.getLayoutY()+35);
        background.getChildren().add(tf_price);
    }

    /**
     * getter and setter
     */
    public Optional<Integer> getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Optional<Integer> numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Optional<Boolean> getPrivateToilet() {
        return privateToilet;
    }

    public void setPrivateToilet(Optional<Boolean> privateToilet) {
        this.privateToilet = privateToilet;
    }
}
