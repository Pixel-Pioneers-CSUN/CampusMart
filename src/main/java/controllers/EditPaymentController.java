package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPaymentController {

    @FXML
    private Button cancelEditBtn;

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

    @FXML
    void cancelEdit(ActionEvent event) {
        Stage stage = (Stage) cancelEditBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveEdit(ActionEvent event) {
    //saves payment to database

        //update card ending num

    }

    void setEditedInfo(){

    }

}

