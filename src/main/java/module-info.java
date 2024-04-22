module org.example.campusmart {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;

    opens org.example.campusmart to javafx.fxml;
    opens controllers to javafx.fxml;   // had to open up controllers to JavafX since it couldn't access the HomeScreenController
    opens items to javafx.fxml;

    exports org.example.campusmart;
    exports controllers;
    exports items;
    exports utils;
    opens utils to javafx.fxml;
    opens orders to javafx.fxml;
    exports orders;

    opens Cart to javafx.fxml;
    exports Cart;
}