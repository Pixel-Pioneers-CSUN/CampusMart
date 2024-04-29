package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.NavigationListener;
import controllers.HeaderBarController;

import java.io.IOException;

public class AccountSignInPopupController {

    private NavigationListener navigationListener;
    private Stage mainStage;

    @FXML
    private Button popupSignIn;
    @FXML
    private Label popupStartHere;

    public void setNavigationListener(NavigationListener navigationListener) {
        this.navigationListener = navigationListener;
    }

    @FXML
    private void clickSignIn(MouseEvent event) {

        // if Sign In in popup is clicked, notify the HeaderBarController to navigate to the login page
        if (navigationListener != null) {
            navigationListener.navigateToLogin();
        }
    }

    @FXML
    private void clickStartHere(MouseEvent event) {
        // if Start Here in popup is clicked, notify the HeaderBarController to navigate to the create account page
        if (navigationListener != null) {
            navigationListener.navigateToCreateAccount();
        }
    }



}
