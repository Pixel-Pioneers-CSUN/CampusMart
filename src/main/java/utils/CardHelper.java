package utils;

/**
 * The CardHelper class provides methods for handling card numbers.
 */
public class CardHelper {

    /**
     * Hides all characters except the last 4 digits of the card number.
     *
     * @param number The card number
     * @return The hidden card number
     */
    public static String hideNumbers(String number) {
        // Check if card number is null or empty
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
