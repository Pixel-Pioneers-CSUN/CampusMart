package controllers;

import items.ItemDataStructure;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import orders.OrderDataStructure;
import orders.Orders;
import utils.DatabaseUtility;

import java.io.IOException;

public class OrderController {

    @FXML
    private GridPane grid;

    @FXML
    private Label myDateOrderPlaced;

    @FXML
    private Label myViewInvoice;

    @FXML
    private Label myViewOrder;

    /**
     * -Need to pull all OrderItems from the Order to
     * populate the grid to show what was purchased by the
     * user(Account)
     * -Need a function that populates the grid per item in
     * the list
     * -Need to pull the Order from the BD to place into a
     * datastructure or a quick List
     * -Flow of creating the order page
     *
     */


    @FXML
    public void createOrderHistory(Orders order) {
        //create OrderNumber fxml with list
        myDateOrderPlaced.setText(order.getDate().toString());

        int row = 1;
        try {
            // order will populate a scrollpane of items ordered
            for (Integer itemNumber : order.getOrderItems().keySet()) {
                // create an OrderItem obj fxml


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/OrderItems.fxml"));
                AnchorPane pane = fxmlLoader.load();
                OrderItemController itemController = fxmlLoader.getController();

                ItemDataStructure data = ItemDataStructure.getInstance();

                itemController.setData(data.getItemDataStructure().get(itemNumber),
                       order.getOrderItems().get(itemNumber));



                grid.add(pane, 0, row++); //(child,column,row)
                // Set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                // Set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(pane, new Insets(10));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    public void initialize() {
//
//        DatabaseUtility db = new DatabaseUtility();
//        OrderDataStructure data = OrderDataStructure.getInstance();
//        data.setOrderList(db.createOrderList(0));
//        for(Orders order : data.getOrderList()) {
//            System.out.println(order);
//        }
//        OrderDataStructure orderMap = OrderDataStructure.getInstance();
//        Orders order = orderMap.getOrderList().getFirst();
//
//        createOrderHistory(orderMap.getOrderList().get(4));
//    }

}
