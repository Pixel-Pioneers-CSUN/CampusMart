package controllers;

import items.ItemClass;
import items.ItemDataStructure;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import orders.Orders;

import java.io.IOException;

/**
 * @version 4/13/24
 * @author Erick Espinoza
 * Controller class for managing the display of order history.
 */
public class OrderController {

    @FXML
    private GridPane grid;

    @FXML
    private Label myDateOrderPlaced;

    /**
     * Creates the order history display based on the provided order details.
     *
     * @param order The order for which to display history.
     */
    @FXML
    public void createOrderHistory(Orders order) {
        myDateOrderPlaced.setText("Order Placed: " + order.getDate() + "\nTotal: $" + order.getTotal().toString());

        int row = 1;
        try {
            for (Integer itemNumber : order.getOrderItems().keySet()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/OrderItems.fxml"));
                AnchorPane pane = fxmlLoader.load();
                OrderItemController itemController = fxmlLoader.getController();

                ItemDataStructure data = ItemDataStructure.getInstance();
                itemController.setData(data.getItemDataStructure().get(itemNumber),
                        order.getOrderItems().get(itemNumber));

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
            e.printStackTrace();
        }
    }
}
