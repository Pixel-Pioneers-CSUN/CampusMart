package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.NavigationListener;
import Account.*;

import static java.sql.Types.NULL;

public class AlreadyLoggedInPopupController {

    private NavigationListener navigationListener;
    @FXML
    private Button popupAccountDashboard;
    @FXML
    private Button popupLogOut;

    public void setNavigationListener(NavigationListener navigationListener) {
        this.navigationListener = navigationListener;
    }

    @FXML
    private void clickAccountDashboard(MouseEvent event) {

        // if Account Dashboard button in popup is clicked, notify the HeaderBarController to navigate to the Account Dashboard page
        if (navigationListener != null) {
            navigationListener.navigateToAccountDashboard();
        }
    }

    @FXML
    private void clickLogOut(MouseEvent event) {
        // if Log Out button in popup is clicked, notify the HeaderBarController to navigate to the Home page
        if (navigationListener != null) {
            // set all logged in credentials and data to empty values and loggedInStatus to false
            Account account = Account.getInstance();
            account.setAccountID(0);
            account.setUsername("");
            account.setPassword("");
            account.setName("");
            account.setAddress("");
            account.setPaymentName("");
            account.setPaymentCVV(0);
            account.setPaymentExpiration("");
            account.setPaymentNumber("");
            account.setLoggedInStatus(false);
            navigationListener.navigateToHome();
        }
    }

}
