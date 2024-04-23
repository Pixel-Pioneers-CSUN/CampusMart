package controllers;

import items.DatabaseUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import utils.textFieldHelper;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The LoginController class controls the login functionality of the application.
 */
public class LoginController implements Initializable {

    @FXML
    private TextField passwordTF;

    @FXML
    private Label SignUpLabel;

    @FXML
    private Label LoginErrorLabel;

    @FXML
    private TextField usernameTF;

    //Getters
    public String getPassword() {return passwordTF.getText();}
    public String getUsername() {return usernameTF.getText();}
    public String getLoggedInUsername() {return loggedInUsername;}

    List<TextField> textFields = new ArrayList<>();
    List<TextField> emptyFields = new ArrayList<>();
    utils.textFieldHelper textFieldHelper = new textFieldHelper();
    boolean isLoggedIn = false;
    String loggedInUsername;

    /**
     * Switches to the sign-up page.
     *
     * @param event The event triggering the action
     */
    @FXML
    void switchToSignUp(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) SignUpLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the login button click event.
     *
     * @param event The event triggering the action
     */
    @FXML
    void LoginClicked(ActionEvent event) {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-background-color: white;");
            LoginErrorLabel.setText("");
        }
        emptyFields = textFieldHelper.checkEmptyTextFields(textFields);
        if (!emptyFields.isEmpty()) {
            for (TextField textField : emptyFields) {
                textField.setStyle("-fx-background-color: pink;");
                LoginErrorLabel.setText("Fill Empty Fields");
            }
        } else {
            validateLogin();
        }
    }

    /**
     * Switches to the home screen when the user successfully logs in.
     */
    void switchToHomescreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homescreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) SignUpLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates the login credentials.
     */
    void validateLogin() {
        DatabaseUtility database = new DatabaseUtility();
        database.setQuery("SELECT * FROM users WHERE username = ?");
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(database.getQuery())) {
            statement.setString(1, getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    LoginErrorLabel.setText("Account doesn't exist");
                    return;
                }
                String dbPassword = resultSet.getString("password");
                if (!dbPassword.equals(getPassword())) {
                    LoginErrorLabel.setText("Invalid Password");
                    return;
                }
                loggedInUsername = resultSet.getString("username");
                isLoggedIn = true;
                switchToHomescreen();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFields = List.of(usernameTF, passwordTF);
    }
}
