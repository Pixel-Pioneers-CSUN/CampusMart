package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import orders.OrderDataStructure;
import orders.Orders;
import utils.DatabaseUtility;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OrderPageController {

    @FXML
    private GridPane grid;

    @FXML
    public void createOrderGrid() {

        int row = 1;
        OrderDataStructure data = OrderDataStructure.getInstance();


        try {
            for(Orders orders : data.getOrderList()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/Order.fxml"));
                AnchorPane pane = fxmlLoader.load();
                OrderController controller = fxmlLoader.getController();
                controller.createOrderHistory(orders);
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


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    public void initialize() {
        // create Orders DataStructure
        DatabaseUtility db = new DatabaseUtility();
        OrderDataStructure data = OrderDataStructure.getInstance();
        data.setOrderList(db.createOrderList(0));
        for(Orders order : data.getOrderList()) {
            System.out.println(order);
        }
        createOrderGrid();
        //populate grid with order info


    }


}
