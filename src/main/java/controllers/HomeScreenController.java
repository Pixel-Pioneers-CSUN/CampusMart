package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Popup;
import utils.DatabaseUtility;
import utils.SearchHelper;
import items.*;

import utils.ImageCarousel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @version 3/15/24
 * @author Sevan Shahijanian
 * Controller class for the home screen and all of its functions.
 * The category VBox mouse events trigger navigation to their respective custom Item Display page.
 */
public class HomeScreenController {

    @FXML
    private Button shopNowButton;
    @FXML
    private VBox vboxHotFood;
    @FXML
    private VBox vboxColdFood;
    @FXML
    private VBox vboxSnacks;
    @FXML
    private VBox vboxFruits;
    @FXML
    private VBox vboxDrinks;
    @FXML
    private VBox vboxCoffee;
    @FXML
    private HBox itemCarouselContainer;

    @FXML
    private void initialize() {
        // create an instance of ImageCarousel and add it to the FlowPane container
        ImageCarousel imageCarousel = new ImageCarousel(10);
        itemCarouselContainer.getChildren().add(imageCarousel);
    }

    @FXML
    private void clickShopNow(ActionEvent event) {
            System.out.println("Shop Now button clicked!");

            FXMLLoader itemDisplayLoader = new FXMLLoader((getClass().getResource("/view/ItemDisplay.fxml")));
            Parent itemDisplayRoot;
            try {
                itemDisplayRoot = itemDisplayLoader.load();
                ItemDisplayController itemDisplayController = itemDisplayLoader.getController();

                // display all the items (set by default) in the item grid
                itemDisplayController.createItemGridPage("default");

                // get the stage and set it to the new scene
                Stage stage = (Stage) shopNowButton.getScene().getWindow();
                Scene scene = new Scene(itemDisplayRoot);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void clickHotFoods(MouseEvent event) {
        navigateToItemCategoryPage("hot foods", vboxHotFood);
    }

    @FXML
    private void clickColdFoods(MouseEvent event) {
        navigateToItemCategoryPage("cold foods", vboxColdFood);
    }

    @FXML
    private void clickSnacks(MouseEvent event) {
        navigateToItemCategoryPage("snacks", vboxSnacks);
    }

    @FXML
    private void clickFruits(MouseEvent event) {
        navigateToItemCategoryPage("fruits", vboxFruits);
    }

    @FXML
    private void clickDrinks(MouseEvent event) {
        navigateToItemCategoryPage("drinks", vboxDrinks);
    }

    @FXML
    private void clickCoffee(MouseEvent event) {
        navigateToItemCategoryPage("coffee", vboxCoffee);
    }

    /**
     * Navigates the user to the appropriate category page after a category vbox has been clicked.
     *
     * @param selectedCategory The name of the selected category
     * @param vbox The VBox on the home screen containing the category
     */
    private void navigateToItemCategoryPage(String selectedCategory, VBox vbox) {
        try {
            FXMLLoader itemDisplayLoader = new FXMLLoader((getClass().getResource("/view/ItemDisplay.fxml")));
            Parent itemDisplayRoot = itemDisplayLoader.load();
            ItemDisplayController itemDisplayController = itemDisplayLoader.getController();

            // pass the selected category to the ItemDisplayController
            itemDisplayController.createItemGridPage(selectedCategory);

            // set the scene of the current stage to the scene containing the modified ItemDisplayController
            Stage currentStage = (Stage) vbox.getScene().getWindow();
            Scene scene = new Scene(itemDisplayRoot);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add drop shadow effect to a VBox
    private void addDropShadow(VBox vbox) {
        vbox.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");
    }

    // Method to remove drop shadow effect from a VBox
    private void removeDropShadow(VBox vbox) {
        vbox.setStyle(null);
    }

    @FXML
    private void onMouseEntered(MouseEvent event) {
        VBox vbox = (VBox) event.getSource();
        addDropShadow(vbox);
    }

    @FXML
    private void onMouseExited(MouseEvent event) {
        VBox vbox = (VBox) event.getSource();
        removeDropShadow(vbox);
    }


}

