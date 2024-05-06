package controllers;

import Cart.Cart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField mySubTotal;

    @FXML
    void updateSubtotal() {
        Cart cart = Cart.getInstance();
        mySubTotal.setText("Subtotal: $" + cart.getSubtotal().toString());

    }


    @FXML
    void clickCheckout() {
        //load checkout
        Cart cart = Cart.getInstance();
        if (!cart.getSubtotal().toString().equals("0")){
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
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Empty Cart");
            alert.showAndWait();
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

        try {
            // load the fxml file of the cart screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CartPage.fxml"));
            Parent root = loader.load();
            // get the stage and set it to the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) grid.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error with updating cart");
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller, setting up the display of items in the shopping cart.
     */
    @FXML
    public void initialize() {
        // Retrieves the singleton instance of the shopping cart
        Cart cart = Cart.getInstance();
        updateSubtotal();
        int row = 1;
        // Iterates over each item in the cart
        for (Map.Entry<Integer, Integer> entry : cart.getCartItems().entrySet()) {
            try {
                // Loads the FXML file for a single cart item
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/CartItem.fxml"));
                AnchorPane pane = fxmlLoader.load();

                // Sets the preferred height and width for the cart item pane
                //pane.setPrefHeight(100);
                //pane.setPrefWidth(400);

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
