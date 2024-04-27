package controllers;

import items.ItemClass;
import items.ItemDataStructure;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import utils.NavigationListener;
import utils.SearchHelper;
import java.io.IOException;
import java.util.*;

/**
 * @version 3/20/24
 * @author Sevan Shahijanian
 * Controller class for the header bar, it's UI elements, and their functionalities.
 * Header Bar acts as the main stage for any popups. This is needed to allow for navigating to other pages from popups.
 */
public class HeaderBarController implements NavigationListener {

    private ListView<String> searchResultsListView = new ListView<>();
    @FXML
    private ImageView headerBarLogoImage;
    @FXML
    private TextField headerBarSearchBar;
    @FXML
    private ImageView headerBarCartImage;
    @FXML
    private ImageView headerBarAccountImage;
    @FXML
    private ComboBox<String> headerBarCategoryDropdown;

    public StackPane headerBar;
    private Stage mainStage;
    private Scene scene;
    // creating a Popup for displaying search results
    private Popup searchPopup = new Popup();
    // creating a Popup for sign-in/create account options when clicking account icon
    private Popup accountPopup;
    // creating a ListView for search results

    @FXML
    public void initialize() {

        // populate the ComboBox (category drop down) with product categories
        populateCategoryDropdown();

        // set up the event handler to navigate user to selected category page
        setupCategorySelectionHandler();

        // set the content of the Popup to a VBox containing the ListView
        VBox popupContent = new VBox(searchResultsListView);
        searchPopup.getContent().add(popupContent);

        // set the style of the search popup and hide its default scroll bar
        searchResultsListView.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        // set the maximum height of the search results popup so it doesn't go off screen
        searchResultsListView.setMaxHeight(240);    // 24 * 10 = 240, so max size is 10 items, but list will be scrollable

        // hide the popup initially (only show it during a valid search)
        searchPopup.setAutoHide(true);

        // Initialize the account popup and pass in a reference to the current stage, mainStage
        loadAccountPopup(mainStage);

    }

    /**
     * Navigates the user to the Create Account screen.
     */
    @Override
    public void navigateToCreateAccount() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateAccount.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) headerBarSearchBar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates the user to the Account Dashboard screen.
     */
    @Override
    public void navigateToAccountDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AccountDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) headerBarSearchBar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates the user to the Login screen.
     */
    @Override
    public void navigateToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) headerBarSearchBar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the stage of the current window to main stage.
     * Note: This function is needed to allow navigating the user to new scenes from popups
     * @param mainStage The main stage of the application.
     */
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void populateCategoryDropdown() {
        // defining the product categories
        List<String> categories = Arrays.asList("Hot Foods", "Cold Foods", "Snacks", "Fruits", "Drinks", "Coffee");

        // adding the categories to the ComboBox
        headerBarCategoryDropdown.getItems().addAll(categories);
    }

    /**
     * Handles category selection and navigation for the ComboBox dropdown menu
     */
    private void setupCategorySelectionHandler() {
        headerBarCategoryDropdown.setOnAction(event -> {
            // store the selected category name into selectedCategory
            String selectedCategory = headerBarCategoryDropdown.getSelectionModel().getSelectedItem().toLowerCase();
            if (selectedCategory != null) {
                // get reference to the ItemDisplayController
                FXMLLoader itemDisplayLoader = new FXMLLoader((getClass().getResource("/view/ItemDisplay.fxml")));
                Parent itemDisplayRoot;
                try {
                    itemDisplayRoot = itemDisplayLoader.load();
                    ItemDisplayController itemDisplayController = itemDisplayLoader.getController();

                    // pass the selected category to the ItemDisplayController
                    itemDisplayController.createItemGridPage(selectedCategory);

                    // set the scene of the current stage to the scene containing the modified ItemDisplayController
                    Stage currentStage = (Stage) headerBarCategoryDropdown.getScene().getWindow();
                    Scene scene = new Scene(itemDisplayRoot);
                    currentStage.setScene(scene);
                    currentStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Performs an item search based on the entered search term and navigates the user to the respective item page after click event.
     */
    @FXML
    private void searchItems() {
        String searchTerm = headerBarSearchBar.getText();
        if (!searchTerm.isEmpty()) {
            // perform search through ItemDataStructure based on searchTerm
            Map<Integer, ItemClass> searchResults = SearchHelper.searchItems(ItemDataStructure.getInstance().getItemDataStructure(), searchTerm);

            // clear the current items in the ListView
            searchResultsListView.getItems().clear();

            // populate the ListView with search results
            searchResults.forEach((id, item) -> searchResultsListView.getItems().add(item.getItemName()));

            // set the height of the ListView depending on the number of items
            searchResultsListView.setPrefHeight(searchResults.size() * 24); // 24 is the pixel height of each result

            // show the Popup below the search bar
            positionSearchResults();

            // add a click event handler to the search results ListView
            searchResultsListView.setOnMouseClicked(event -> {
                String selectedName = searchResultsListView.getSelectionModel().getSelectedItem();
                if (selectedName != null) {
                    ItemClass selectedItem = SearchHelper.getItemByName(selectedName, ItemDataStructure.getInstance().getItemDataStructure());
                    if (selectedItem != null) {
                        loadItemPage(selectedItem);
                    }
                }
            });

        } else {
            // hide the Popup when not conducting a search
            searchPopup.hide();
        }
    }

    /**
     * Loads the sign-in/create account popup.
     *
     * @param mainStage The main stage of the application.
     */
    private void loadAccountPopup(Stage mainStage) {
        this.mainStage = mainStage;
        accountPopup = new Popup();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AccountSignInPopup.fxml"));
            BorderPane popupContent = loader.load();
            AccountSignInPopupController popupController = loader.getController();
            popupController.setNavigationListener(this);    // pass the NavigationListener to AccountSignInPopupController
            accountPopup.getContent().add(popupContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // hide the popup if the user clicks anywhere outside of it
        accountPopup.setAutoHide(true);
    }

    /**
     * Loads the respective item page when an item's name in the search results is clicked on.
     *
     * @param item The clicked item in the search results.
     */
    private void loadItemPage(ItemClass item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemDisplay.fxml"));
            Parent root = loader.load();
            // now pass the selected item to the ItemDisplayController so it can display it
            ItemDisplayController controller = loader.getController();
            //display the selected item
            controller.displayItemInformation(item);
            // display the grid with the rest of the items
            controller.createItemGridPage("default");

            Scene scene = new Scene(root);
            Stage stage = (Stage) headerBarSearchBar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Positions the search results popup correctly below the search bar.
     */
    private void positionSearchResults() {
        // get the width of the search bar
        double searchBarWidth = headerBarSearchBar.getWidth();

        // set the preferred width of the popup search results to match the width of the search bar
        searchResultsListView.setPrefWidth(searchBarWidth);

        // set the position of the popup relative to the parent container
        Bounds searchBarBounds = headerBarSearchBar.localToScreen(headerBarSearchBar.getBoundsInLocal());
        // calculate the location below the search bar
        double popupX = searchBarBounds.getMinX();
        double popupY = searchBarBounds.getMaxY();

        searchPopup.show(headerBarSearchBar, popupX, popupY);
    }

    @FXML
    private void clickHomeLogo(MouseEvent event) {
        // if header bar logo image is clicked, return user to home screen
        try {
            System.out.println("Logo image clicked!");
            // load the fxml file of the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
            Parent root = loader.load();
            // create a new scene and load the fxml of home screen
            Scene scene = new Scene(root);
            // get the stage and set it to the new scene
            Stage stage = (Stage) headerBarLogoImage.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickAccountImage(MouseEvent event) {

        // Show the account popup below to the account image
        Bounds accountImageBounds = headerBarAccountImage.localToScreen(headerBarAccountImage.getBoundsInLocal());
        double popupX = accountImageBounds.getMinX() - 185;
        double popupY = accountImageBounds.getMinY() + 25;

        System.out.println("Account image clicked!");
        accountPopup.show(headerBarAccountImage, popupX, popupY);
    }

    @FXML
    private void clickCartImage(MouseEvent event) {
        // if header bar cart icon is clicked, navigate user to the cart
        try {
            System.out.println("Cart image clicked!");
            // load the fxml file of the cart screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CartPage.fxml"));
            Parent root = loader.load();
            // get the stage and set it to the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) headerBarSearchBar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
