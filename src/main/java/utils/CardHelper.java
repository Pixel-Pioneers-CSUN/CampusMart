package utils;

public class CardHelper {
    public static String hideNumbers(String number) {
        // Check if card num is null or empty
        if (number == null || number.isEmpty()) {
            return "";
        }
        // Hide all characters except last 4 digits
        StringBuilder hiddenNumber = new StringBuilder();
        int length = number.length();
        for (int i = 0; i < length - 4; i++) {
            hiddenNumber.append('*');
        }
        hiddenNumber.append(number.substring(length - 4));

        return hiddenNumber.toString();
    }
}
