package controllers;

import Cart.Cart;
import items.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * @version 3/20/24
 * @author Erick Espinoza
 * Controller class for managing the display of item information.
 */
public class ItemDisplayController implements Initializable {

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;

    @FXML
    private GridPane grid;

    @FXML
    private TextField myQuantityField;

    private MyListener myListener;
    private int selectedItemNumber =-1;
    private int itemQuantity = 0;

    /**
     * Displays information about the selected item.
     *
     * @param item The item to display information for.
     */
    @FXML
    public void displayItemInformation(ItemClass item) {
        itemName.setText(item.getItemName());
        itemPrice.setText("$" + item.getPrice().toString());
        selectedItemNumber = item.getItemNumber();
        Image image = new Image(getClass().getResourceAsStream("/images/" + item.getItemPicture()));
        itemImage.setImage(image);
        myQuantityField.setText("0");
        itemQuantity = 0;
    }

    /**
     * Handles the click event on the "Add to Cart" button.
     *
     * @param event The action event.
     */
    @FXML
    public void clickAddToCart(ActionEvent event) {
        if(selectedItemNumber == -1) {
            return;
        }
        Cart cart = Cart.getInstance();
        cart.addToCart(selectedItemNumber,itemQuantity);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Added to Cart");
        alert.show();
    }

    /**
     * Increases the quantity of the selected item.
     *
     * @param event The action event.
     */
    @FXML
    public void increaseQuantity(ActionEvent event) {
        itemQuantity++;
        myQuantityField.setText(String.valueOf(itemQuantity));
    }

    /**
     * Decreases the quantity of the selected item.
     *
     * @param event The action event.
     */
    @FXML
    public void decreaseQuantity(ActionEvent event) {
        if (itemQuantity > 0) {
            itemQuantity--;
            myQuantityField.setText(String.valueOf(itemQuantity));
        }
    }

    /**
     * Creates a grid page displaying items of a specific category.
     *
     * @param category The category of items to display.
     */
    public void createItemGridPage(String category) {
        ItemDataStructure data = ItemDataStructure.getInstance();
        int column = 0;
        int row = 1;
        //
        try {
            for (ItemClass item : data.getItemDataStructure().values()) {
                ItemClass entry = new ItemClass();
                if (item.getCategory().equals(category) || category.equals("default")) {
                    entry = item;
                } else {
                    continue;
                }

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(entry, myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            System.out.println("Failed to create item page!");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myListener = new MyListener() {
            @Override
            public void onClickListener(ItemClass item) {
                displayItemInformation(item);
            }
        };

        myQuantityField.setText("0");
    }
}
