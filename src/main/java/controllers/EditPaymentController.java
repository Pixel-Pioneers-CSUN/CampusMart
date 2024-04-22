package controllers;

import items.DatabaseUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.DateHelper;
import utils.textFieldHelper;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditPaymentController implements Initializable {

    @FXML
    private Button cancelEditBtn;

    @FXML
    private Label editPaymentErrorLabel;


    @FXML
    private TextField editPaymentCVV;

    @FXML
    private TextField editPaymentCardNum;

    @FXML
    private TextField editPaymentNameOnCard;

    @FXML
    private DatePicker editPaymentValidThrough;

    @FXML
    private Button saveEditBtn;

    public String getEditPaymentCVV() {return editPaymentCVV.getText();}
    public LocalDate getEditPaymentValidThrough () {return editPaymentValidThrough.getValue();}
    public String getEditPaymentCardNum() {return editPaymentCardNum.getText();}
    public String getEditPaymentNameOnCard() {return editPaymentNameOnCard.getText();}
    LoginController login = new LoginController();
    String formattedDate;



    utils.textFieldHelper textFieldHelper = new textFieldHelper();
    List<TextField> textFields = new ArrayList<>();
    List<TextField> emptyFields = new ArrayList<>();
    DateHelper dateHelper = new DateHelper();
    DatabaseUtility db = new DatabaseUtility();

    @FXML
    void cancelEdit(ActionEvent event) {
        Stage stage = (Stage) cancelEditBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveEdit(ActionEvent event) {

        boolean dateValidation = dateHelper.dateValidation(getEditPaymentValidThrough(), editPaymentErrorLabel, "Invalid Date");
        // Check if user entered all info
        formattedDate = getEditPaymentValidThrough().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        emptyFields = textFieldHelper.checkEmptyTextFields(textFields);
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        if (!textFieldHelper.isEmpty && dateValidation) {
            //saves payment to database
            db.saveProfileInfoToDB("paymentName", getEditPaymentNameOnCard(), login.getLoggedInUsername());
            db.saveProfileInfoToDB("paymentNumber", getEditPaymentCardNum(), login.getLoggedInUsername());
            db.saveProfileInfoToDB("paymentCVV", getEditPaymentCVV(), login.getLoggedInUsername());
            db.saveProfileInfoToDB("paymentExpiration", formattedDate, login.getLoggedInUsername());

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
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Set filters for text fields during initialization
            editPaymentCardNum.setTextFormatter(new TextFormatter<>(textFieldHelper.textFilter
                    (editPaymentCardNum, editPaymentErrorLabel, "\\d{0,16}", "Enter a valid card number")));
            editPaymentNameOnCard.setTextFormatter(new TextFormatter<>(textFieldHelper.textFilter(
                    editPaymentNameOnCard, editPaymentErrorLabel, "^[a-zA-Z ]*$", "Enter a valid name for card")));
            editPaymentCVV.setTextFormatter(new TextFormatter<>(textFieldHelper.textFilter(
                    editPaymentCVV, editPaymentErrorLabel, "\\d{0,3}", "Enter a valid CVV")));

            textFields = List.of(editPaymentCardNum, editPaymentNameOnCard, editPaymentCVV);

        } catch (Exception e) {
            e.printStackTrace();
        }

        }
    }


