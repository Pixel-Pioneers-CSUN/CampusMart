package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * The textFieldHelper class provides utility methods for handling text fields.
 */
public class textFieldHelper {
    public boolean isEmpty;

    /**
     * Initializes the textFieldHelper.
     */
    public textFieldHelper() {
        isEmpty = false;
    }

    /**
     * Checks if a list of text fields are empty.
     *
     * @param tfList The list of text fields to check
     * @return A list of empty text fields
     */
    public List<TextField> checkEmptyTextFields(List<TextField> tfList) {
        List<TextField> emptyFields = new ArrayList<>();
        for (TextField textField : tfList) {
            if (textField.getText().isEmpty()) {
                isEmpty = true;
                emptyFields.add(textField);
            } else {
                isEmpty = false;
                emptyFields.remove(textField); // remove textfield if not empty anymore
            }
        }
        return emptyFields;
    }

    /**
     * Provides a text filter for text fields.
     *
     * @param textfield    The text field to apply the filter to
     * @param errorLabel   The label to display error messages
     * @param regex        The regular expression pattern to match
     * @param errorMessage The error message to display
     * @return The text filter
     */
    public UnaryOperator<TextFormatter.Change> textFilter(TextField textfield, Label errorLabel, String regex, String errorMessage) {
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
