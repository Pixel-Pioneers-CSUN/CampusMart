package controllers;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.campusmart.CampusMart;

import java.io.IOException;

public class HomeScreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView headerBarLogoImage;

    @FXML
    private Button shopNowButton;

    @FXML
    public void initialize() {
        // if logo is clicked, return user to home screen
        headerBarLogoImage.setOnMouseClicked(event -> {
            try {
                System.out.println("Logo image clicked!");
                // load the fxml file of the home screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
                Parent root = loader.load();
                // create a new scene and load the fxml of home screen
                Scene scene = new Scene(root);
                // get the stage and set it to the new scene
                Stage stage = (Stage) headerBarLogoImage.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // if shop now button is clicked, return user to home screen
        shopNowButton.setOnMouseClicked(event -> {
            try {
                System.out.println("Shop Now button clicked!");
                // load the fxml file of the home screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemDisplay.fxml"));
                Parent root = loader.load();
                // create a new scene and load the fxml of home screen
                Scene scene = new Scene(root);
                // get the stage and set it to the new scene
                Stage stage = (Stage) headerBarLogoImage.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
