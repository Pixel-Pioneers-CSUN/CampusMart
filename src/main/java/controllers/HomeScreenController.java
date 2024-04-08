package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Popup;
import org.example.campusmart.CampusMart;
import utils.SearchHelper;
import items.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HomeScreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // creating a Popup for displaying search results
    private Popup searchPopup = new Popup();

    // creating a ListView for search results
    private ListView<String> searchResultsListView = new ListView<>();

    @FXML
    private ImageView headerBarLogoImage;

    @FXML
    private Button shopNowButton;

    @FXML
    private TextField headerBarSearchBar;

    @FXML
    public void initialize() {
        // set the content of the Popup to a VBox containing the ListView
        VBox popupContent = new VBox(searchResultsListView);
        searchPopup.getContent().add(popupContent);

        // set the style of the search popup and hide its default scroll bar
        searchResultsListView.setStyle("-fx-background-color: transparent; -fx-padding: 0;");


        // hide the popup initially (only show it during a valid search)
        searchPopup.setAutoHide(true);

        //==============
        // start of app pull data and then make a data structure
        DatabaseUtility bd = new DatabaseUtility();
        bd.setTable("Item_Database");

        // create data structure for Items
        ItemDataStructure data = ItemDataStructure.getInstance();
        data.setItemDataStructure(bd.createHasMapItemClass());

    }

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
        } else {
            // hide the Popup when not conducting a search
            searchPopup.hide();
        }
    }

    //position the search popup below the search bar
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
    private void clickShopNow(ActionEvent event) {
        try {
            System.out.println("Shop Now button clicked!");
            // load the fxml file of the item display screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemDisplay.fxml"));
            Parent root = loader.load();
            // create a new scene and load the fxml of item display screen
            Scene scene = new Scene(root);
            // get the stage and set it to the new scene
            Stage stage = (Stage) shopNowButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
