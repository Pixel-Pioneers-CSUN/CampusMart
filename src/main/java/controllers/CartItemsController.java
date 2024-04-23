package controllers;

import items.ItemClass;
import items.ItemDataStructure;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 4/20/24
 * Erick Espinoza
 * Controller class for managing the display of items in a shopping cart.
 *
 */
public class CartItemsController {

    @FXML
    private Label myLabel;

    @FXML
    private ImageView myImage;

    /**
     * Sets the data of the item to be displayed in the cart.
     *
     * @param item   The index of the item in the data structure.
     * @param amount The quantity of the item.
     */
    public void setData(Integer item, Integer amount) {
        // Retrieves the instance of the item data structure
        ItemDataStructure data = ItemDataStructure.getInstance();

        // Retrieves the item object corresponding to the given index
        ItemClass temp = data.getItemDataStructure().get(item);

        // Constructs the text to be displayed, including item name, quantity, and price
        String text = "Item: " + temp.getItemName() +
                "\nQty: " + amount.toString() + "\t Price: " +
                temp.getPrice();

        // Sets the text of the label to display the item information
        myLabel.setText(text);

        // Loads the image of the item and sets it to the image view
        Image image = new Image(getClass().getResourceAsStream("/images/" + temp.getItemPicture()));
        myImage.setImage(image);
    }
}