package utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class DateHelper
{

    public boolean dateValidation(LocalDate date , Label errorLabel, String errorMessage){
        LocalDate currentDate = LocalDate.now();
        //LocalDate selectedDate = datePicker.getValue();
        boolean isValid = false;

        if (date != null && date.isAfter(currentDate)) {
            errorLabel.setText("");
            isValid = true;
        } else {
            errorLabel.setText(errorMessage);
            errorLabel.setVisible(true);
        }
        return  isValid;
    }

}
