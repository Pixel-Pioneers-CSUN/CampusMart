package controllers;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import items.ItemClass;
import items.ItemDataStructure;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    public void initialize() {
        DatabaseUtility db = new DatabaseUtility();
        OrderDataStructure data = OrderDataStructure.getInstance();
        data.setOrderList(db.createOrderList(0));
        for(Orders order : data.getOrderList()) {
            System.out.println(order);
        }
        OrderDataStructure orderMap = OrderDataStructure.getInstance();
        Orders order = orderMap.getOrderList().getFirst();
        createOrderHistory(order);
    }

    @FXML
    public void createOrderHistory(Orders order) {
        //create OrderNumber fxml with list
        myDateOrderPlaced.setText(order.getDate().toString());
        int column = 0;
        int row = 1;
        // order will populate a scrollpane of items ordered
        for(Integer itemNumber: order.getOrderItems().keySet()) {
            // create an OrderItem obj fxml
            OrderItemController itemController = new OrderItemController();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/OrderItems.fxml"));
            AnchorPane anchorPane = null;
            try {
                 anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                System.out.println("Error loading OrderItems.fxml");
                throw new RuntimeException(e);
            }
            ItemDataStructure data = ItemDataStructure.getInstance();


            itemController.setData(data.getItemDataStructure().get(itemNumber),
                    order.getOrderItems().get(itemNumber));

            if (column == 3) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row); //(child,column,row)
            // Set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            // Set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));

        }
    }

}
