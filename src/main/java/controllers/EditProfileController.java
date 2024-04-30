package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.CardHelper;
import utils.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The EditProfileController class controls the user profile editing functionality.
 */
public class EditProfileController implements Initializable {

    LoginController login = new LoginController();
    DatabaseUtility db = new DatabaseUtility();

    // FXML elements
    @FXML
    private TextField editAddressTF; // Text field for editing address

    @FXML
    private PasswordField editPasswordTF; // Password field for editing password

    @FXML
    private Label cardEndingLabel; // Label showing last four digits of card number

    @FXML
    private PasswordField confirmPwTF; // Password field for confirming password changes

    @FXML
    private Button cancelBtn; // Button for canceling changes

    @FXML
    private Label editProfileErrorLabel;

    @FXML
    private Label goBackLabel;


    //Getters
    public String getEditAddress() {return editAddressTF.getText();}
    public String getEditPassword() {return editPasswordTF.getText();}
    public String getConfirmPassword() { return confirmPwTF.getText();}

    /**
     * Saves changes to the database.
     *
     * @param event The event triggering the action
     */
    @FXML
    void saveChangesToDB(ActionEvent event) {
        int addressUpdated = -1; // Flag for address update
        int passwordUpdated = -1; // Flag for password update

        // Check if address is provided and update the database
        if (!editAddressTF.getText().isEmpty() && validatePassword()) {
            addressUpdated = db.saveProfileInfoToDB("address", editAddressTF.getText(), login.loggedInUsername);
        }

        // Check if password is provided and update the database
        if (!editPasswordTF.getText().isEmpty() && !confirmPwTF.getText().isEmpty() && validatePassword()) {
            passwordUpdated = db.saveProfileInfoToDB("password", editPasswordTF.getText(), login.loggedInUsername);
        }
        // Show confirmation message if any update was successful
        Alert saveConfirmation = new Alert(Alert.AlertType.INFORMATION);
        if (addressUpdated != -1 || passwordUpdated != -1) {
            saveConfirmation.setContentText("Saved Successfully");
            saveConfirmation.showAndWait();
        } else {
            saveConfirmation.setContentText("Something went wrong");
            saveConfirmation.showAndWait();
        }
    }

    /**
     * Validates the password.
     *
     * @return True if the password is valid, otherwise false
     */
    public boolean validatePassword() {
        boolean validPW = false;
        if (!(editPasswordTF.getText().equals(confirmPwTF.getText()))) {
            editProfileErrorLabel.setText("Passwords do not match");
        } else {
            validPW = true;
        }
        return validPW;
    }

    /**
     * Edits payment information.
     *
     * @param event The event triggering the action
     */
    @FXML
    void editPayment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditPayment.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Edit Payment Information");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles going back to the account dashboard.
     *
     * @param event The event triggering the action
     */
    @FXML
    void backToEditProfile(ActionEvent event) {
        checkIfSafeToGoBack();
    }

    /**
     * Handles going back to the edit profile page.
     *
     * @param event The event triggering the action
//     */
    @FXML
    public void backToAccount(javafx.scene.input.MouseEvent mouseEvent) {
        checkIfSafeToGoBack();
    }

    /**
     * Checks if the user has typed anything in the textfields and alerts them before going back.
     */
    public void checkIfSafeToGoBack() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to go back to the previous window?");
        ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesBtn, noBtn);
        // If fields are empty, go back directly
        if (getEditAddress().isEmpty() && getEditPassword().isEmpty() && getConfirmPassword().isEmpty()) {
            goBack();
        } else {
            alert.showAndWait(); // Otherwise, show confirmation alert
        }
        // If user clicks yes, go back
        if (alert.getResult() == yesBtn) {
            goBack();
        }
    }

    /**
     * Navigates back to the account dashboard window.
     */
    public void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AccountDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // show original address so user can edit it
        editProfileErrorLabel.setText("");
        if (login.isLoggedIn) {
            db.setTable("users");
            editAddressTF.setText(db.getLoggedInUserInfo(login.getLoggedInUsername(), "address"));
            String cardnum = db.getLoggedInUserInfo(login.getLoggedInUsername(), "paymentNumber");
            String hiddenNumber = CardHelper.hideNumbers(cardnum);
            cardEndingLabel.setText(hiddenNumber);
            cardEndingLabel.setVisible(true);
        }
    }



}
