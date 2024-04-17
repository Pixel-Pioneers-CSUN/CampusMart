package utils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class textFieldHelper {
    public boolean isEmpty;


    public textFieldHelper(){
        isEmpty = false;
    }

    //Checks if list of text fields are empty
    public List<TextField>  checkEmptyTextFields(List<TextField> tfList){
        List<TextField> emptyFields = new ArrayList<>();
        for (TextField textField : tfList) {
            if (textField.getText().isEmpty()) {
                isEmpty = true;
                emptyFields.add(textField);
            }
            else {
                isEmpty = false; 
                emptyFields.remove(textField); // remove textfield if not empty anymore
            }
            

        }
        return emptyFields;
    }

    public UnaryOperator<TextFormatter.Change> textFilter (TextField textfield,  Label errorLabel, String regex, String errorMessage) {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.matches(regex) || newText.isEmpty()) {
                textfield.setStyle("");
                errorLabel.setText("");
                return change;
            } else {
                textfield.setStyle("-fx-background-color: pink;");
                errorLabel.setVisible(true);
                errorLabel.setText(errorMessage);
                return null;
            }
        };
    }
}

    