package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import utils.DateHelper;
import utils.textFieldHelper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Controller for the Checkout view.
 */
public class CheckoutController implements Initializable {

    // FXML
    @FXML
    private TextField addressTF;
    @FXML
    private TextField cardNumTF;
    @FXML
    private TextField cityTF;
    @FXML
    private Label creditcardErrorLabel;
    @FXML
    private Label contactInfoErrorLabel;
    @FXML
    private Pane creditCardInfoPane;
    @FXML
    private TextField cvvTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField nameOnCardTF;
    @FXML
    private TextField phoneNumTF;
    @FXML
    private Label taxAmount;
    @FXML
    private Label totalPrice;
    @FXML
    private TextField zipCodeTF;
    @FXML
    private DatePicker validThroughDP;

    List<TextField> textFields = new ArrayList<>();
    List<TextField> emptyFields = new ArrayList<>();

    textFieldHelper textFieldHelper = new textFieldHelper();
    DateHelper dateHelper = new DateHelper();
    String formattedDate;

    public String getCardNumber() {
        return cardNumTF.getText();
    }

    public String getCVV() {
        return cvvTF.getText();
    }

    public String getNameOnCard() {
        return nameOnCardTF.getText();
    }

    public String getFirstName() {
        return firstNameTF.getText();
    }

    public String getLastName() {
        return lastNameTF.getText();
    }

    public String getZipCode() {
        return zipCodeTF.getText();
    }

    public String getPhoneNumber() {
        return phoneNumTF.getText();
    }

    public String getEmail() {
        return emailTF.getText();
    }

    public String getAddress() {
        return addressTF.getText();
    }

    public String getCity() {
        return cityTF.getText();
    }

    public LocalDate getValidThrough() {
        return validThroughDP.getValue();
    }

    /**
     * Handles the payment confirmation.
     *
     * @param event The action event
     */
    @FXML
    void confirmPayment(ActionEvent event) {
        try {
            boolean dateValidation = dateHelper.dateValidation(getValidThrough(), creditcardErrorLabel, "Invalid Date");
            // Check if user entered all info
            emptyFields = textFieldHelper.checkEmptyTextFields(textFields);
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            if (!textFieldHelper.isEmpty && dateValidation) {
                formattedDate = getValidThrough().format(DateTimeFormatter.ofPattern("MM/dd/yy"));
                confirmAlert.setTitle("Payment Confirmation");
                confirmAlert.setHeaderText("Confirm Payment");
                confirmAlert.setContentText("Are you sure you want to proceed with the payment?");
                ButtonType yesBtn = new ButtonType("Yes", ButtonData.YES);
                ButtonType noBtn = new ButtonType("No", ButtonData.NO);
                confirmAlert.getButtonTypes().setAll(yesBtn, noBtn);
                confirmAlert.showAndWait();
                //System.out.println(formattedDate);
                // If not show error
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Empty Fields");
                errorAlert.setContentText("Please Fill All Fields ");
                errorAlert.show();

                for (TextField textField : emptyFields) {
                    textField.setStyle("-fx-background-color: pink;");
                }
            }
            if (confirmAlert.getResult() != null && confirmAlert.getResult().getButtonData() == ButtonData.YES) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Payment Successful");
                successAlert.setContentText("Order Placed");
                successAlert.show();
                // If user presses yes, call reduce inventory method
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Switches to the cart screen.
     *
     * @param event The mouse event
     */
    @FXML
    void switchToCart(MouseEvent event) {
        // Switch scene to cart when user clicks on "back to cart" label
        /*
         * try{ FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
         * Parent root = loader.load(); Scene scene = new Scene(root); Stage stage =
         * (Stage) backToCartLabel.getScene().getWindow(); stage.setScene(scene);
         * stage.show(); } catch (IOException e) { e.printStackTrace(); }
         */
    }

    /**
     * Shows the credit card information box.
     *
     * @param event The action event
     */
    @FXML
    void showCreditCard(ActionEvent event) {
        creditCardInfoPane.setVisible(true);
    }

    /**
     * Checks if the provided email address is valid.
     *
     * @param email The email address to check
     * @return True if the email is valid, false otherwise
     */
    public boolean isValidEmailAddress(String email) {
        boolean isValid = true;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException e) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Set filters for text fields during initialization
            cardNumTF.setTextFormatter(new TextFormatter<>(textFieldHelper
                    .textFilter(cardNumTF, creditcardErrorLabel, "\\d{0,16}", "Enter a valid card number")));
            nameOnCardTF.setTextFormatter(new TextFormatter<>(textFieldHelper.
                    textFilter(nameOnCardTF, creditcardErrorLabel, "^[a-zA-Z ]*$", "Enter a valid name for card")));
            cvvTF.setTextFormatter(new TextFormatter<>(textFieldHelper
                    .textFilter(cvvTF, creditcardErrorLabel, "\\d{0,3}", "Enter a valid CVV")));
            firstNameTF.setTextFormatter(new TextFormatter<>(textFieldHelper
                    .textFilter(firstNameTF, contactInfoErrorLabel, "^[a-zA-Z ]*$", "Enter a valid first name")));
            lastNameTF.setTextFormatter(new TextFormatter<>(textFieldHelper
                    .textFilter(lastNameTF, contactInfoErrorLabel, "^[a-zA-Z ]*$", "Enter a valid last name")));
            cityTF.setTextFormatter(new TextFormatter<>(textFieldHelper
                    .textFilter(cityTF, contactInfoErrorLabel, "^[a-zA-Z ]*$", "Enter a valid city")));
            zipCodeTF.setTextFormatter(new TextFormatter<>(textFieldHelper
                    .textFilter(zipCodeTF, contactInfoErrorLabel, "\\d{0,5}", "Enter a valid zip code")));
            phoneNumTF.setTextFormatter(new TextFormatter<>(textFieldHelper
                    .textFilter(phoneNumTF, contactInfoErrorLabel, "\\d{0,10}", "Enter a valid phone number")));
            addressTF.setTextFormatter(new TextFormatter<>(textFieldHelper
                    .textFilter(addressTF, contactInfoErrorLabel, "^[a-zA-Z0-9 ]*$", "Enter a valid address")));

            // Email validation
            emailTF.textProperty().addListener((observable, oldValue, newValue) -> {
                if (isValidEmailAddress(newValue) || emailTF.isVisible()) {
                    emailTF.setStyle("");
                    contactInfoErrorLabel.setText("");
                    System.out.println("right");
                } else {
                    emailTF.setStyle("-fx-background-color: pink;");
                    contactInfoErrorLabel.setText("Enter a valid email address");
                    contactInfoErrorLabel.setVisible(true);
                    System.out.println("wrong");
                }
            });

            textFields = List.of(addressTF, cardNumTF, cityTF, cvvTF, emailTF, firstNameTF, lastNameTF, nameOnCardTF, phoneNumTF, zipCodeTF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reduces the inventory count for items that were bought.
     *
     * @return The new inventory count
     */
    public int reduceInventory() {
        // Iterate over each item in cart
        // Access each cart item's inventory count
        // Reduce each cart item's inventory count by 1
        // Returns a list of the new inventory count number for each cart item
        // Is called when the user presses on pay button
        return 0;
    }

    /**
     * Updates the order summary.
     */
    public void updateOrderSummary() {
        // Iterate over the items in cart
        // Display each item's name, price, quantity
        // Must be updated every time the customer presses on checkout
    }
}
