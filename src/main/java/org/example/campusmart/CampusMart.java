package org.example.campusmart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import items.*;
import utils.DatabaseUtility;

import java.io.IOException;

public class CampusMart extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) throws IOException {
        //==============
        // start of app pull data and then make a data structure
        DatabaseUtility bd = new DatabaseUtility();
        bd.setTable("Item_Database");

        // create data structure for Items
        ItemDataStructure data = ItemDataStructure.getInstance();
        data.setItemDataStructure(bd.createHasMapItemClass());
        FXMLLoader fxmlLoader = new FXMLLoader(CampusMart.class.getResource("/view/HomeScreen.fxml"));

        // Setting the default window size of the scene
        Scene scene = new Scene(fxmlLoader.load(), 1300, 800);   // also set the initial window size

//        Parent root = FXMLLoader.load(getClass().getResource("/view/ItemDisplay.fxml"));
//
//        Scene scene = new Scene(root);
//        String css = this.getClass().getResource("/css/items-styles.css").toExternalForm();
//        scene.getStylesheets().add(css);

        // Setting the title of the stage
        stage.setTitle("Welcome to Campus Mart");

        // Setting the stage to not be resizable (fixed size)
        stage.setResizable(false);

        // Setting the scene on the stage
        stage.setScene(scene);

        // Center the scene on the display
        stage.centerOnScreen();

        stage.show();
    }
}