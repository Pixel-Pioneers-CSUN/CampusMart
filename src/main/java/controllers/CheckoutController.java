package controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import utils.textFieldHelper;

public class CheckoutController implements Initializable {

    // FXML
    @FXML
    private Label ErrorMessageLabel;
    @FXML
    private TextField addressTF;
    @FXML
    private Label backToCartLabel;
    @FXML
    private TextField cardNumTF;
    @FXML
    private TextField cityTF;
    @FXML
    private Pane contactInfoPane;
    @FXML
    private Pane creditCardInfoPane;
    @FXML
    private ImageView creditcardImg;
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
    private Button payButton;
    @FXML
    private TextField phoneNumTF;
    @FXML
    private Label taxAmount;
    @FXML
    private Label totalPrice;
    @FXML
    private TextField zipCodeTF;
    @FXML
    private Label checkoutLabel;
    @FXML
    private DatePicker validThroughTF;

    // Constant
    final int CREDIT_CARD_MAX = 16;

    List<TextField> textFields = new ArrayList<>();
    List<TextField> emptyFields = new ArrayList<>();

    textFieldHelper textFieldHelper = new textFieldHelper();

    // Getter methods to access text fields info
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

    public DatePicker getValidThrough() {
        return validThroughTF;
    }

    @FXML
    void confirmPayment(ActionEvent event) {
        try {
            // Check if user entered all info
            emptyFields = textFieldHelper.checkEmptyTextFields(textFields);
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            if (!textFieldHelper.isEmpty) {
                confirmAlert.setTitle("Payment Confirmation");
                confirmAlert.setHeaderText("Confirm Payment");
                confirmAlert.setContentText("Are you sure you want to proceed with the payment?");
                ButtonType yesBtn = new ButtonType("Yes", ButtonData.YES);
                ButtonType noBtn = new ButtonType("No", ButtonData.NO);
                confirmAlert.getButtonTypes().setAll(yesBtn, noBtn);
                confirmAlert.showAndWait();
                // If not show error
            } 
            else {
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

    @FXML
    void showCreditCard(ActionEvent event) {
        creditCardInfoPane.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Set filters for text fields during initialization
            cardNumTF.setTextFormatter(new TextFormatter<>(numbersOnlyFilter()));
            nameOnCardTF.setTextFormatter(new TextFormatter<>(nameFilter()));
            cvvTF.setTextFormatter(new TextFormatter<>(cvvFilter()));
            textFields = List.of(addressTF, cardNumTF, cityTF, cvvTF, emailTF, firstNameTF, lastNameTF, nameOnCardTF,
                    phoneNumTF, zipCodeTF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check if input is a numerical value only and within length limits
    private UnaryOperator<TextFormatter.Change> numbersOnlyFilter() {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^[0-9]*$") && (newText.length() <= CREDIT_CARD_MAX)) {
                cardNumTF.setStyle(""); // Reset style if input is valid
                ErrorMessageLabel.setText(""); // Clear error message
                return change; 
            } else {
                cardNumTF.setStyle("-fx-background-color: pink;");
                ErrorMessageLabel.setVisible(true);
                ErrorMessageLabel.setText("Enter a valid card number");
                return null;
            }
        };
    }

    // Filter for name validation
    private UnaryOperator<TextFormatter.Change> nameFilter() {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^[a-zA-Z ]*$")) {
                nameOnCardTF.setStyle("");
                ErrorMessageLabel.setText("");
                return change; 
            } else {
                nameOnCardTF.setStyle("-fx-background-color: pink;");
                ErrorMessageLabel.setVisible(true);
                ErrorMessageLabel.setText("Enter a valid name");
                return null;
            }
        };
    }

    // Filter for CVV validation
    private UnaryOperator<TextFormatter.Change> cvvFilter() {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^[0-9]*$") && newText.length() <= 3) {
                cvvTF.setStyle("");
                ErrorMessageLabel.setText("");
                return change;
            } else {
                cvvTF.setStyle("-fx-background-color: pink;");
                ErrorMessageLabel.setVisible(true);
                ErrorMessageLabel.setText("Enter a valid CVV number");
                return null;
            }
        };
    }

    // Method to reduce the inventory count for items that were bought
    public int reduceInventory() {
        // Iterate over each item in cart
        // Access each cart item's inventory count
        // Reduce each cart item's inventory count by 1
        // Returns a list of the new inventory count number for each cart item
        // Is called when the user presses on pay button
        return 0;
    }

    // Method to display the items that are in cart when the user goes to the checkout page
    public void updateOrderSummary() {
        // Iterate over the items in cart
        // Display each item's name, price, quantity
        // Must be updated every time the customer presses on checkout
    }

}
