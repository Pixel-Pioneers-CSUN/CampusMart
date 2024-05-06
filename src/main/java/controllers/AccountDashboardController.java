package controllers;


import Account.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import orders.OrderDataStructure;
import utils.DatabaseUtility;
import java.io.IOException;


public class AccountDashboardController {

    @FXML
    private AnchorPane myDisplayAnchorPane;

    @FXML
    void loadOrderPage(ActionEvent event) {

        myDisplayAnchorPane.getChildren().clear();
        // create Orders DataStructure
        DatabaseUtility db = new DatabaseUtility();
        OrderDataStructure orderData = OrderDataStructure.getInstance();
        Account account = Account.getInstance();
        // check if user is logged in.
        if(!account.getLoggedInStatus()) {
            return;
        }
        // create data structure for orders for the account.
        orderData.setOrderList(db.createOrderList(account.getAccountID()));
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/OrderPage.fxml"));
            AnchorPane pane = fxmlLoader.load();
            pane.setMaxWidth(1121);
            pane.setMaxWidth(714);
            myDisplayAnchorPane.getChildren().add(pane);
        }
        catch (IOException e) {
            System.out.println("Failed to load OrderPage.fxml in AccountDashboardController");
            e.printStackTrace();
        }

    }

    public void editAccount() {

        myDisplayAnchorPane.getChildren().clear();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/EditProfile.fxml"));
            AnchorPane pane = fxmlLoader.load();
            pane.setMaxWidth(1121);
            pane.setMaxWidth(714);

            myDisplayAnchorPane.getChildren().add(pane);
        }
        catch (IOException e) {
            System.out.println("Failed to load OrderPage.fxml in AccountDashboardController");
            e.printStackTrace();
        }
    }

}