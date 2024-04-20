package controllers;

import items.DatabaseUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    LoginController login = new LoginController();

    @FXML
    private TextField editAddressTF;

    @FXML
    private PasswordField editPasswordTF;

    @FXML
    private Label cardEndingLabel;

    @FXML
    void saveChangesToDB(ActionEvent event) {

    }
    @FXML
    void editPayment(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditPayment.fxml"));
            Parent root = loader.load();
            //EditPaymentController controller = (EditPaymentController) loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("My Window");
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        DatabaseUtility db = new DatabaseUtility();
//        db.setTable("account");
//        editAddressTF.setText(db.getLoggedInUserInfo(login.getLoggedInUsername(), "address"));
//        String cardnum = db.getLoggedInUserInfo(login.getLoggedInUsername(), "cardNumber");
//        String hiddenNumber = CardHelper.hideNumbers(cardnum);
//        cardEndingLabel.setText(hiddenNumber);



    }
}
