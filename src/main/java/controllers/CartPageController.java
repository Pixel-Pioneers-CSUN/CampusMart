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

public class CartPageController {

    @FXML
    private VBox myCartItemsVBox;

    @FXML
    public void initialize() {
        Cart cart = Cart.getInstance();
        for(Map.Entry<Integer,Integer> entry : cart.getCartItems().entrySet()) {
            //create a CartItem object with passed info.

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/CartItem.fxml"));
                AnchorPane pane = fxmlLoader.load();
                pane.setPrefHeight(100);
                pane.setPrefWidth(400);
                CartItemsController controller = fxmlLoader.getController();
                controller.setData(entry.getKey(),entry.getValue());
                myCartItemsVBox.getChildren().add(pane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
