package controllers;


import Account.Account;
import Cart.Cart;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        OrderDataStructure orderData = OrderDataStructure.getInstance();
        Account account = Account.getInstance();

        // There will be a check for which account is being used before
        // creating the database aka make sure a user is logged in

//        Cart cart = Cart.getInstance();
//        cart.addToCart(2,3);
//        cart.addToCart(3,6);
//        cart.addToCart(34,1);
//        Random rand = new Random();
//        int randOrderID = rand.nextInt(999);
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        String date = df.format(new Date());
//        Orders order = new Orders(randOrderID,account.getAccountID(),date,cart.getSubtotal(),cart.getCartItems());
//        order.addToDatabase();

        orderData.setOrderList(db.createOrderList(account.getAccountID()));


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