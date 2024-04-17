package controllers;

import items.DatabaseUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    LoginController login = new LoginController();

    @FXML
    private TextField editAddressTF;

    @FXML
    private TextField editCVVTF;

    @FXML
    private TextField editCardNumTF;

    @FXML
    private TextField editNameOnCardTF;

    @FXML
    private PasswordField editPasswordTF;

    @FXML
    private DatePicker editValidThroughTF;

    @FXML
    void saveChangesToDB(ActionEvent event) {


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseUtility db = new DatabaseUtility();
        db.setTable("account");
        editAddressTF.setText(db.getLoggedInUserAddress(login.getLoggedInUsername()));

    }
}
