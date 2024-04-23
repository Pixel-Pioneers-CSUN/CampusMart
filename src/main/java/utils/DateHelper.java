package utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

/**
 * The DateHelper class provides utility methods for date operations.
 */
public class DateHelper {

    /**
     * Validates a date against the current date.
     *
     * @param date         The date to validate
     * @param errorLabel   The label to display error messages
     * @param errorMessage The error message to display
     * @return True if the date is valid, otherwise false
     */
    public boolean dateValidation(LocalDate date, Label errorLabel, String errorMessage) {
        LocalDate currentDate = LocalDate.now();
        boolean isValid = false;

        if (date != null && date.isAfter(currentDate)) {
            errorLabel.setText("");
            isValid = true;
        } else {
            errorLabel.setText(errorMessage);
            errorLabel.setVisible(true);
        }
        return isValid;
    }
}
