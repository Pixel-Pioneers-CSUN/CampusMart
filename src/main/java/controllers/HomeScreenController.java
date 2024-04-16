package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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

