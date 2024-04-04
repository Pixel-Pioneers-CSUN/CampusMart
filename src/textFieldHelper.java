import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

public class textFieldHelper {
    boolean isEmpty;

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
}

    