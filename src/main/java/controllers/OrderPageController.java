package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Order.fxml"));
        try {
            AnchorPane pane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OrderController orderController = fxmlLoader.getController();



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
        //populate grid with order info


    }


}
