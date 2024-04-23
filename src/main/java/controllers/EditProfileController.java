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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    // Instance variables
    LoginController login = new LoginController(); // Controller for login functionality
    DatabaseUtility db = new DatabaseUtility(); // Utility for database operations

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
    private VBox buttonContainer; // Container for buttons

    // method to save changes to the database
    @FXML
    void saveChangesToDB(ActionEvent event) {
        int addressUpdated = -1; // Flag for address update
        int passwordUpdated = -1; // Flag for password update

        // Check if address is provided and update the database
        if (!editAddressTF.getText().isEmpty()) {
            addressUpdated = db.saveProfileInfoToDB("address", editAddressTF.getText(), login.loggedInUsername);
        }

        // Check if password is provided and update the database
        if (!editPasswordTF.getText().isEmpty()) {
            passwordUpdated = db.saveProfileInfoToDB("password", editPasswordTF.getText(), login.loggedInUsername);
        }

        // Show confirmation message if any update was successful
        Alert saveConfirmation = new Alert(Alert.AlertType.INFORMATION);
        if (addressUpdated != -1 || passwordUpdated != -1) {
            saveConfirmation.setContentText("Saved Successfully");
            saveConfirmation.showAndWait();
        }
    }

    // method to edit payment information
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

    // method to go back to the profile page
    @FXML
    void backToEditProfile(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to go back to the previous window?");
        ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesBtn, noBtn);

        // If fields are empty, go back directly
        if (editAddressTF.getText().isEmpty() && editPasswordTF.getText().isEmpty() && confirmPwTF.getText().isEmpty()) {
            goBack();
        } else {
            alert.showAndWait(); // Otherwise, show confirmation alert
        }

        // ff user click yes, go back
        if (alert.getResult() == yesBtn) {
            goBack();
        }
    }

    // method to navigate back to the previous window
    public void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
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
        if (login.isLoggedIn) {
            db.setTable("users");
            editAddressTF.setText(db.getLoggedInUserInfo(login.getLoggedInUsername(), "address"));
            String cardnum = db.getLoggedInUserInfo(login.getLoggedInUsername(), "cardNumber");
            String hiddenNumber = CardHelper.hideNumbers(cardnum);
            cardEndingLabel.setText(hiddenNumber);
            cardEndingLabel.setVisible(true);
        }
    }
}
