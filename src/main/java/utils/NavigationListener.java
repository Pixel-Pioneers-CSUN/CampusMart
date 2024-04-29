package utils;

/**
 * @author Sevan Shahijanian
 * An interface that allows for navigating from the header bar's popups to relevant pages.
 *
 */
public interface NavigationListener {
    void navigateToCreateAccount();
    void navigateToAccountDashboard();
    void navigateToLogin();
    void navigateToHome();
}
