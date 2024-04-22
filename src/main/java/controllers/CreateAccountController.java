package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

import static utils.DatabaseUtility.createAccount;

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



    @FXML
    private void clickSignUp(MouseEvent event) {
        System.out.println("SIGN UP WAS CLICKED!");
        if (checkIfTextFieldsEmpty()) {
            signUpErrorLabel.setText("* One or more required fields are empty.");
        } else if (!passwordMeetsCriteria()) {
            signUpErrorLabel.setText("* Password must be at least 6 characters long.");
        } else if (!checkIfPasswordsMatch()) {
            signUpErrorLabel.setText("* Entered passwords do not match.");
        } else {
            createAccount(createAccountName.getText(), createAccountUsername.getText(),createAccountPassword.getText());
        }
    }


    private boolean passwordMeetsCriteria() {
        return createAccountPassword.getText().length() >= 6; // password must be at least 6 characters long
    }

    private boolean checkIfPasswordsMatch() {
        return createAccountPassword.getText().equals(createAccountPasswordVerify.getText());
    }

    private boolean checkIfTextFieldsEmpty() {
        return createAccountName.getText().isEmpty() || createAccountUsername.getText().isEmpty() ||
                createAccountPassword.getText().isEmpty() || createAccountPasswordVerify.getText().isEmpty();
    }



}
