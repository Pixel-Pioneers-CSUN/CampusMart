package Account;


/**
 * 04/26/24
 * @author Sevan Shahijanian
 * A singleton class representing account and all of its relevant functions
 *
 */
public class Account {

    // singleton instance
    public static final Account account = new Account();

    // private constructor
    private Account() { }

    // method to access the singleton instance
    public static Account getInstance() {
        return account;
    }

    private int accountID;
    private String username;
    private String password;
    private String name;
    private String address;
    private String paymentName;
    private String paymentCVV;
    private String paymentExpiration;
    private String paymentNumber;
    private boolean isLoggedIn = false;

    // Get and set accountID
    public int getAccountID() {
        return accountID;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    // Get and set username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Get and set name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Get and set address
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    // Get and set name on payment method
    public String getPaymentName() {
        return paymentName;
    }
    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    // Get and set payment CVV
    public String getPaymentCVV() {
        return paymentCVV;
    }
    public void setPaymentCVV(String paymentCVV) {
        this.paymentCVV = paymentCVV;
    }

    // Get and set payment expiration
    public String getPaymentExpiration() {
        return paymentExpiration;
    }
    public void setPaymentExpiration(String paymentExpiration) {
        this.paymentExpiration = paymentExpiration;
    }

    // Get and set payment number
    public String getPaymentNumber() {
        return paymentNumber;
    }
    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    // Get and set logged in status
    public boolean getLoggedInStatus() {
        return isLoggedIn;
    }
    public void setLoggedInStatus(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    // Get and set password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
