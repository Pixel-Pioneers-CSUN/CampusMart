package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import orders.OrderDataStructure;
import orders.Orders;

import java.io.IOException;

/**
 * @version 4/13/24
 * @author Erick Espinoza
 * Controller class for managing the display of order history on the order page.
 */
public class OrderPageController {

    @FXML
    private GridPane grid;

    /**
     * Creates a grid of orders to display on the order page.
     */
    @FXML
    public void createOrderGrid() {
        int row = 1;

        OrderDataStructure data = OrderDataStructure.getInstance();

        try {
            for (Orders orders : data.getOrderList()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/Order.fxml"));
                AnchorPane pane = fxmlLoader.load();
                OrderController controller = fxmlLoader.getController();
                controller.createOrderHistory(orders);
                grid.add(pane, 0, row++);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(pane, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the order page by creating the order grid.
     */
    @FXML
    public void initialize() {
        createOrderGrid();
    }
}
