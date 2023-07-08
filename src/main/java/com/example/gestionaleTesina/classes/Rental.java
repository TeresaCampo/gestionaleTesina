package com.example.gestionaleTesina.classes;

import com.example.gestionaleTesina.controllers.DBConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class Rental extends TravelOptionComponent{
    private Optional<String> kindOfRental;
    protected TextField tf_kindRental;
    public Rental(String componentName, String groupID, String travelName, String optionName, Integer posInTravelOption, Double price, String name, LocalDate checkInDate, LocalDate checkOutDate, LocalTime checkInTime, LocalTime checkOutTime, DBConnection database, String kindOfRental) {
        super(componentName, groupID, travelName, optionName, posInTravelOption, price, name, checkInDate, checkOutDate, checkInTime, checkOutTime, database);
        this.kindOfRental=Optional.ofNullable(kindOfRental);
        System.out.println("database set in rental"+ database);
    }

    @Override
    public void addEmptyGraphicComponent(int layoutX, int layoutY, AnchorPane background) {
        addRentalGraphicComponent(layoutX,layoutY,background);

        bindCommonGraphicElementToAttributes();
        tf_kindRental.textProperty().addListener((observable, oldValue, newValue) -> kindOfRental=Optional.ofNullable(newValue));
    }

    @Override
    public void addInitializedGraphicComponent(int layoutX, int layoutY, AnchorPane background) {
        addRentalGraphicComponent(layoutX,layoutY,background);

        initializeCommonGraphicComponent();
        kindOfRental.ifPresent((h->tf_kindRental.setText(kindOfRental.get())));

        bindCommonGraphicElementToAttributes();
        tf_kindRental.textProperty().addListener((observable, oldValue, newValue) -> kindOfRental=Optional.ofNullable(newValue));
    }

    @Override
    public void storeInDB() {
        try {
            database.storeRental(this);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database Error\nWhile storing rental"+name.orElse("")).showAndWait();
        }
    }

    public void addRentalGraphicComponent(int layoutX, int layoutY, AnchorPane background) {
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.background=background;

        //1st line
        lb_kindOfComponent=new javafx.scene.control.Label("Rental");
        lb_kindOfComponent.setLayoutX(layoutX); lb_kindOfComponent.setLayoutY(layoutY);
        lb_kindOfComponent.setPrefWidth(96);
        background.getChildren().add(lb_kindOfComponent);

        tf_name=new TextField("Rental Store Name/Url");
        tf_name.setPrefWidth(150);
        tf_name.setLayoutX(lb_kindOfComponent.getLayoutX()+lb_kindOfComponent.getPrefWidth()+10);   tf_name.setLayoutY(lb_kindOfComponent.getLayoutY());
        background.getChildren().add(tf_name);
        tf_name.setPrefWidth(200);

        tf_kindRental=new TextField("Type of Rental");
        tf_kindRental.setPrefWidth(160);
        tf_kindRental.setLayoutX(tf_name.getLayoutX()+tf_name.getPrefWidth()+10);   tf_kindRental.setLayoutY(tf_name.getLayoutY());
        background.getChildren().add(tf_kindRental);

        //2nd line
        lb_from=new Label("From");
        lb_from.setPrefWidth(40);
        lb_from.setLayoutX(tf_name.getLayoutX()); lb_from.setLayoutY(tf_kindRental.getLayoutY()+35);
        background.getChildren().add(lb_from);

        dp_from=new DatePicker();
        dp_from.setPrefWidth(114);
        dp_from.setLayoutX(tf_name.getLayoutX()+lb_from.getPrefWidth()+10);    dp_from.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(dp_from);

        lb_to=new Label("To");
        lb_to.setPrefWidth(15);
        lb_to.setLayoutX(dp_from.getLayoutX()+dp_from.getPrefWidth()+10);   lb_to.setLayoutY(lb_from.getLayoutY());
        background.getChildren().add(lb_to);

        dp_to=new DatePicker();
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
        lb_price=new Label("Price");
        lb_price.setPrefWidth(30);
        lb_price.setLayoutX(tf_name.getLayoutX());    lb_price.setLayoutY(lb_checkIn.getLayoutY()+35);
        background.getChildren().add(lb_price);

        tf_price=new TextField();
        tf_price.setPrefWidth(106);
        tf_price.setLayoutX(lb_price.getLayoutX()+lb_price.getPrefWidth()+10);    tf_price.setLayoutY(lb_price.getLayoutY());
        background.getChildren().add(tf_price);
    }

    //getter and setter
    public Optional<String> getKindOfRental() {
        return kindOfRental;
    }
}
