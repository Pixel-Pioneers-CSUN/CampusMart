package controllers;

import Cart.Cart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Map;

/**
 * @version 4/20/24
 * @author Erick Espinoza
 * Controller class for managing the display of items in the shopping cart page.
 */
public class CartPageController {

    @FXML
    private VBox myCartItemsVBox;

    /**
     * Initializes the controller, setting up the display of items in the shopping cart.
     */
    @FXML
    public void initialize() {
        // Retrieves the singleton instance of the shopping cart
        Cart cart = Cart.getInstance();

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
                myCartItemsVBox.getChildren().add(pane);

            } catch (IOException e) {
                // Throws a runtime exception if there is an error loading the FXML
                throw new RuntimeException(e);
            }
        }
    }
}
