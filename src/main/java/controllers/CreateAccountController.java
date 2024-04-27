package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utils.*;

/**
 * @version 4/21/24
 * @author Sevan Shahijanian
 * Controller class for creating a new user account using the Create Account screen's GUI
 */
public class CreateAccountController {

    @FXML
    private TextField createAccountName;
    @FXML
    private TextField createAccountUsername;
    @FXML
    private TextField createAccountPassword;
    @FXML
    private TextField createAccountPasswordVerify;
    @FXML
    private Label signUpErrorLabel;
    @FXML
    private Button signUpButton;
    @FXML
    private Label alreadyHaveAccount;

    /**
     * Handles the sign up button click event and initiates account creation in the database.
     *
     * @param event The mouse event triggered if the Sign Up button is clicked.
     */
    @FXML
    private void clickSignUp(ActionEvent event) {
        if (checkIfTextFieldsEmpty()) {
            signUpErrorLabel.setText("* One or more required fields are empty.");
        } else if (!passwordMeetsCriteria()) {
            signUpErrorLabel.setText("* Password must be at least 6 characters long.");
        } else if (!checkIfPasswordsMatch()) {
            signUpErrorLabel.setText("* Entered passwords do not match.");
        } else {
            DatabaseUtility db = new DatabaseUtility();
            db.createAccount(event, createAccountName.getText(), createAccountUsername.getText(),createAccountPassword.getText());
        }
    }

    /**
     * Checks if the entered password meets the required criteria.
     *
     * @return True if the password meets the criteria, false if it doesn't.
     */
    private boolean passwordMeetsCriteria() {
        return createAccountPassword.getText().length() >= 6; // password must be at least 6 characters long
    }

    /**
     * Checks if the entered passwords match.
     *
     * @return True if the passwords match, false if they don't.
     */
    private boolean checkIfPasswordsMatch() {
        return createAccountPassword.getText().equals(createAccountPasswordVerify.getText());
    }

    /**
     * Checks if any of the required text fields are empty.
     *
     * @return True if any of the text fields are empty, false if they all contain text.
     */
    private boolean checkIfTextFieldsEmpty() {
        return createAccountName.getText().isEmpty() || createAccountUsername.getText().isEmpty() ||
                createAccountPassword.getText().isEmpty() || createAccountPasswordVerify.getText().isEmpty();
    }
}
