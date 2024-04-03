module org.example.campusmart {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.campusmart to javafx.fxml;
    opens controllers to javafx.fxml;   // had to open up controllers to JavafX since it couldn't access the HomeScreenController

    exports org.example.campusmart;
    exports controllers;
}