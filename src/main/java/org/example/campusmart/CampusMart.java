package org.example.campusmart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CampusMart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CampusMart.class.getResource("/view/HomeScreen.fxml"));

        // Setting the default window size of the scene
        Scene scene = new Scene(fxmlLoader.load(), 1100, 650);   // also set the initial window size

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