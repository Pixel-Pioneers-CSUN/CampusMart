package controllers;


import items.ItemClass;
import items.ItemDataStructure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.SearchHelper;

import java.io.IOException;

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
    private ImageView popFavSandwich;
    @FXML
    private ImageView popFavIceCream;
    @FXML
    private ImageView popFavBurrito;
    @FXML
    private ImageView popFavPizza;
    @FXML
    private ImageView popFavMuffin;

    @FXML
    private void initialize() {

    }

    @FXML
    private void clickShopNow(ActionEvent event) {

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

    @FXML
    private void clickPopFavSandwich(MouseEvent event) {
        clickFav("Chicken Sandwich");
    }

    @FXML
    private void clickPopFavIceCream(MouseEvent event) {
        clickFav("Ice Cream Bar");
    }

    @FXML
    private void clickPopFavBurrito(MouseEvent event) {
        clickFav("Burrito");
    }

    @FXML
    private void clickPopFavPizza(MouseEvent event) {
        clickFav("Pizza");
    }

    @FXML
    private void clickPopFavMuffin(MouseEvent event) {
        clickFav("Blueberry Muffin");
    }

    private void clickFav(String itemName) {
        ItemClass item = SearchHelper.getItemByName(itemName, ItemDataStructure.getInstance().getItemDataStructure());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemDisplay.fxml"));
            Parent root = loader.load();
            // now pass the selected item to the ItemDisplayController so it can display it
            ItemDisplayController controller = loader.getController();
            //display the selected item
            controller.displayItemInformation(item);
            // display the grid with the rest of the items
            controller.createItemGridPage("default");
            Scene scene = new Scene(root);
            Stage stage = (Stage) popFavSandwich.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

