open module com.example.gestionaletesina {
    requires java.desktop;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.fxmisc.richtext;
    requires org.slf4j;
    requires com.zaxxer.hikari;
    requires net.mahdilamb.colormap;
    requires org.controlsfx.controls;

    exports com.example.gestionaleTesina;
    exports com.example.gestionaleTesina.classes;
}