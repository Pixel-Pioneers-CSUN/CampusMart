package controllers;

import Cart.Cart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.campusmart.CampusMart;

import java.io.IOException;
import java.util.Map;

/**
 * @version 4/20/24
 * @author Erick Espinoza
 * Controller class for managing the display of items in the shopping cart page.
 */
public class CartPageController {


    @FXML
    private GridPane grid;


    @FXML
    void clickCheckout() {
        //load checkout

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/checkout.fxml"));
            Parent checkoutRoot = loader.load();
            Scene scene = new Scene(checkoutRoot);
            Stage stage = (Stage) grid.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Error with transition to checkout");
        }
    }

    @FXML
    void clickContinueShopping() {


        try {
            FXMLLoader itemDisplayLoader = new FXMLLoader((getClass().getResource("/view/ItemDisplay.fxml")));
            Parent itemDisplayRoot = itemDisplayLoader.load();
            ItemDisplayController itemDisplayController = itemDisplayLoader.getController();

            // display all the items (set by default) in the item grid
            itemDisplayController.createItemGridPage("default");

            // get the stage and set it to the new scene
            Stage stage = (Stage) grid.getScene().getWindow();
            Scene scene = new Scene(itemDisplayRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error with transition to continue shopping");
            e.printStackTrace();
        }
    }

    @FXML
    void clickedUpdate() {
        //initialize();
    }

    /**
     * Initializes the controller, setting up the display of items in the shopping cart.
     */
    @FXML
    public void initialize() {
        // Retrieves the singleton instance of the shopping cart
        Cart cart = Cart.getInstance();
        int row = 1;
        // Iterates over each item in the cart
        for (Map.Entry<Integer, Integer> entry : cart.getCartItems().entrySet()) {
            try {
                // Loads the FXML file for a single cart item
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/CartItem.fxml"));
                AnchorPane pane = fxmlLoader.load();

                // Sets the preferred height and width for the cart item pane
                pane.setPrefHeight(100);
                pane.setPrefWidth(400);

                // Retrieves the controller associated with the loaded FXML
                CartItemsController controller = fxmlLoader.getController();

                // Sets the data for the cart item using the entry from the cart
                controller.setData(entry.getKey(), entry.getValue());

                // Adds the cart item pane to the VBox for display
                grid.add(pane, 0, row++);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(pane, new Insets(10));

            } catch (IOException e) {
                // Throws a runtime exception if there is an error loading the FXML
                throw new RuntimeException(e);
            }
        }
    }
}
