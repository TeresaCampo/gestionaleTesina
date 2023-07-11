package com.example.gestionaleTesina.classes;

import com.example.gestionaleTesina.controllers.DBConnection;
import com.example.gestionaleTesina.controllers.MyTextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public abstract class TravelOptionComponent {
    //to store in database
    protected DBConnection database;
    //key
    private String componentName;
    private String groupID;
    private String travelName;
    private String optionName;
    private Integer posInTravelOption;

    //common one
    protected Optional<String> name;
    private Optional<LocalDate> checkInDate;
    private Optional<LocalDate> checkOutDate;
    private Optional<LocalTime> checkInTime;
    private Optional<LocalTime> checkOutTime;
    private Optional<Double> price;


    //graphicComponent
    int layoutX;
    int layoutY;
    protected AnchorPane background;
    protected DatePicker dp_from;
    protected DatePicker dp_to;
    protected Label lb_checkIn;
    protected Label lb_checkOut;
    protected Label lb_from;
    protected Label lb_nOfRoom;
    protected Label lb_price;
    protected Label lb_to;
    protected TextField tf_name;
    protected TextField tf_hourCheckIn;
    protected TextField tf_hourCheckOut;
    protected TextField tf_minuteCheckIn;
    protected TextField tf_minuteCheckOut;
    protected TextField tf_price;
    protected Label tf_timeCheckIn;
    protected Label tf_timeCheckOut;
    protected javafx.scene.control.Label lb_kindOfComponent;


    public TravelOptionComponent(String componentName, String groupID, String travelName, String optionName, Integer posInTravelOption, Double price, String name, LocalDate checkInDate, LocalDate checkOutDate, LocalTime checkInTime, LocalTime checkOutTime, DBConnection database) {
        this.componentName=componentName;
        this.groupID = groupID;
        this.travelName = travelName;
        this.optionName = optionName;
        this.posInTravelOption=posInTravelOption;
        this.price = Optional.ofNullable(price);
        this.name = Optional.ofNullable(name);
        this.checkInDate = Optional.ofNullable(checkInDate);
        this.checkOutDate = Optional.ofNullable(checkOutDate);
        this.checkInTime = Optional.ofNullable(checkInTime);
        this.checkOutTime = Optional.ofNullable(checkOutTime);
        this.database=database;
    }

    public abstract void addEmptyGraphicComponent(int layoutX, int layoutY, AnchorPane background);
    public abstract void addInitializedGraphicComponent(int layoutX, int layoutY, AnchorPane background);
    void initializeCommonGraphicComponent(){
        name.ifPresent(h->tf_name.setText(name.get()));
        checkInDate.ifPresent(h->dp_from.setValue(checkInDate.get()));
        checkOutDate.ifPresent(h->dp_to.setValue(checkOutDate.get()));
        checkInTime.ifPresent(h-> {
            tf_hourCheckIn.setText(String.valueOf(checkInTime.get().getHour()));
            tf_minuteCheckIn.setText(String.valueOf(checkInTime.get().getMinute()));
        });
        checkOutTime.ifPresent(h-> {
            tf_hourCheckOut.setText(String.valueOf(checkOutTime.get().getHour()));
            tf_minuteCheckOut.setText(String.valueOf(checkOutTime.get().getMinute()));
        });
        price.ifPresent(h->tf_price.setText(String.valueOf(price.get())));
    }


    void bindCommonGraphicElementToAttributes(){
        tf_name.textProperty().addListener((observable, oldValue, newValue) -> name=Optional.ofNullable(newValue));
        MyTextField.maxLen450(tf_name);
        tf_hourCheckIn.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                checkInTime = Optional.ofNullable(converterTF_Time(tf_hourCheckIn, tf_minuteCheckIn));
                tf_hourCheckIn.setStyle("-fx-background-color: #e7ebff; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            }
            catch (Exception e){
                tf_hourCheckIn.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            }
        });
        tf_minuteCheckIn.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                checkInTime=Optional.ofNullable(converterTF_Time(tf_hourCheckIn, tf_minuteCheckIn));
                tf_minuteCheckIn.setStyle("-fx-background-color: #e7ebff; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            }catch (Exception e){
                tf_minuteCheckIn.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            }
        });
        tf_hourCheckOut.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                checkOutTime=Optional.ofNullable(converterTF_Time(tf_hourCheckOut, tf_minuteCheckOut));
                tf_hourCheckOut.setStyle("-fx-background-color: #e7ebff; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            }
            catch (Exception e){
                tf_hourCheckOut.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            }
        });
        tf_minuteCheckOut.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                checkOutTime = Optional.ofNullable(converterTF_Time(tf_hourCheckOut, tf_minuteCheckOut));
                tf_minuteCheckOut.setStyle("-fx-background-color: #e7ebff; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            } catch (Exception e) {
                tf_minuteCheckOut.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            }
        });
        dp_from.valueProperty().addListener((observable, oldValue, newValue) -> checkInDate=Optional.ofNullable(newValue));
        dp_to.valueProperty().addListener((observable, oldValue, newValue) -> checkOutDate=Optional.ofNullable(newValue));
        tf_price.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                price = Optional.ofNullable(converterTF_Double(tf_price));
            }
            catch (Exception e){
                tf_price.setStyle("-fx-background-color: pink; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
            }
            tf_price.setStyle("-fx-background-color: #e7ebff; -fx-background-radius: 25px; -fx-border-color: #022757; -fx-border-radius: 25px;");
        });

    }

    public abstract void storeInDB();
    /**
     * to convert from graphic component to TravelOptionComponent
     */
    Double converterTF_Double(TextField tf) throws NumberFormatException{
        if(tf.getText().isEmpty()) return null;
        return parseDouble(tf.getText());

    }

    java.sql.Date converterDP_Date(DatePicker dp){
        if(dp.getValue()==null) return null;
        return java.sql.Date.valueOf( dp.getValue() );
    }

    LocalTime converterTF_Time(TextField tfHOur, TextField tfMinute) throws DateTimeException, NumberFormatException {
        if(tfHOur.getText().isEmpty() && tfMinute.getText().isEmpty()) return null;
        if(!tfHOur.getText().isEmpty() && tfMinute.getText().isEmpty()) return LocalTime.of(parseInt(tfHOur.getText()), 0, 0);
        if(tfHOur.getText().isEmpty() && !tfMinute.getText().isEmpty())  {
            parseInt(tfMinute.getText());
            return null;
        }
        return LocalTime.of(parseInt(tfHOur.getText()), parseInt(tfMinute.getText()), 0);
    }

    Integer converterTF_integer(TextField tf){
        if(tf.getText().isEmpty()) return null;
        return parseInt(tf.getText());
    }

    public void updateLayoutY(){
        layoutY=(int)lb_kindOfComponent.getLayoutY();
    }
    /**
     * getter and setter
     */
    public TextField getTf_name() {
        return tf_name;
    }

    public int getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(int layoutY) {
        this.layoutY = layoutY;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getTravelName() {
        return travelName;
    }

    public String getOptionName() {
        return optionName;
    }
    public Optional<String> getName() {
        return name;
    }
    public void setName(Optional<String> name) {
        this.name = name;
    }
    public Optional<LocalDate> getCheckInDate() {
        return checkInDate;
    }
    public Optional<LocalDate> getCheckOutDate() {
        return checkOutDate;
    }
    public Optional<LocalTime> getCheckInTime() {
        return checkInTime;
    }
    public Optional<LocalTime> getCheckOutTime() {
        return checkOutTime;
    }

    public Optional<Double> getPrice() {
        return price;
    }

    public Integer getPosInTravelOption() {
        return posInTravelOption;
    }

    public void setPosInTravelOption(Integer posInTravelOption) {
        this.posInTravelOption = posInTravelOption;
    }

    public Label getLb_kindOfComponent() {
        return lb_kindOfComponent;
    }

    @Override
    public String toString() {
        return "TravelOptionComponent{" +
                "componentName='" + componentName + '\'' +
                "layoutY="+layoutY+
                '}';
    }
}

