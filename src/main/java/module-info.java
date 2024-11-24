module com.example.apibrawlstars {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    //requires jdk.unsupported.desktop;
    requires java.sql;
    requires org.json;

    opens com.example.apibrawlstars to javafx.fxml;
    exports com.example.apibrawlstars;
    exports com.example.apibrawlstars.Controller;
    opens com.example.apibrawlstars.Controller to javafx.fxml;
}