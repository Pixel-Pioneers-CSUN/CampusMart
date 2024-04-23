package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import orders.OrderDataStructure;
import orders.Orders;
import utils.DatabaseUtility;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

public class AccountDashboardController {

    @FXML
    private Button myOrderHistoryButton;
    @FXML
    private AnchorPane myDisplayAnchorPane;

    @FXML
    void loadOrderPage(ActionEvent event) {
        // create Orders DataStructure
        DatabaseUtility db = new DatabaseUtility();
        OrderDataStructure data = OrderDataStructure.getInstance();
        // There will be a check for which account is being used before
        // creating the database aka make sure a user is logged in

//        HashMap<Integer,Integer> tempMap = new HashMap<>();
//        Random rand = new Random();
//        tempMap.put(4,2);
//        tempMap.put(34,2);
//        tempMap.put(5,1);
//        tempMap.put(45,2);
//
//        BigDecimal tempTotal = new BigDecimal("33.66");
//
//        int rand_int = rand.nextInt(100);
//
//        Orders tempOrder = new Orders(rand_int,0, "7/13/24", tempTotal, tempMap);
//
//        System.out.println(tempOrder);
//
//        tempOrder.addToDataBase();
        data.setOrderList(db.createOrderList(0));


        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/OrderPage.fxml"));
            AnchorPane pane = fxmlLoader.load();

            myDisplayAnchorPane.getChildren().add(pane);
        }
        catch (IOException e) {
            System.out.println("Failed to load OrderPage.fxml in AccountDashboardController");
            e.printStackTrace();
        }

    }

}