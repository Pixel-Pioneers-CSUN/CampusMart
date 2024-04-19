package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccountDashboardController {

    @FXML
    private Button myOrderHistoryButton;
    @FXML
    private AnchorPane myDisplayAnchorPane;

    @FXML
    void loadOrderPage(ActionEvent event) {

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