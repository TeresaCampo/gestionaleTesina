module com.example.gestionaletesina {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens com.example.gestionaleTesina to javafx.fxml;
    exports com.example.gestionaleTesina;
}