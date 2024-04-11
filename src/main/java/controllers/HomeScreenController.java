package controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;


public class HomeScreenController {

    @FXML
    private Button shopNowButton;

    @FXML
    private void clickShopNow(ActionEvent event) {
        try {
            System.out.println("Shop Now button clicked!");
            // load the fxml file of the item display screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemDisplay.fxml"));
            Parent root = loader.load();
            // create a new scene and load the fxml of item display screen
            Scene scene = new Scene(root);
            // get the stage and set it to the new scene
            Stage stage = (Stage) shopNowButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    public void initialize() {
//        try {
//            // load the header bar FXML to the top of the borderpane
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HeaderBar.fxml"));
//            BorderPane headerBar = loader.load();
//
//            rootPane.setTop(headerBar);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


}
