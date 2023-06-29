module com.example.tasvsstudents {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tasvsstudents to javafx.fxml;
    exports com.example.tasvsstudents;
}