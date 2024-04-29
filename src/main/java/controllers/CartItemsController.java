package controllers;

import Cart.Cart;
import items.ItemClass;
import items.ItemDataStructure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @version 4/20/24
 * @author Erick Espinoza
 * Controller class for managing the display of items in a shopping cart.
 *
 */
public class CartItemsController {

    @FXML
    private Label myLabel;

    @FXML
    private ImageView myImage;

    @FXML
    private TextField myQuantityField;

    int itemNumber;

    int itemQuantity;

    @FXML
    public void increaseQuantity(ActionEvent event) {

        Cart cart = Cart.getInstance();
        cart.addToCart(itemNumber,++itemQuantity);
        myQuantityField.setText(String.valueOf(itemQuantity));
    }

    @FXML
    public void decreaseQuantity(ActionEvent event) {
        Cart cart = Cart.getInstance();
        if(--itemQuantity >= 0) {
            cart.addToCart(itemNumber,itemQuantity);
            myQuantityField.setText(String.valueOf(itemQuantity));
        }
        else {
            itemQuantity = 0;

        }
    }

    /**
     * Sets the data of the item to be displayed in the cart.
     *
     * @param item   The index of the item in the data structure.
     * @param amount The quantity of the item.
     */
    public void setData(Integer item, Integer amount) {

        itemNumber = item;
        // Retrieves the instance of the item data structure
        ItemDataStructure data = ItemDataStructure.getInstance();

        // Retrieves the item object corresponding to the given index
        ItemClass temp = data.getItemDataStructure().get(item);
        itemQuantity = amount;

        // Constructs the text to be displayed, including item name, quantity, and price
        String text = temp.getItemName() + "\n Price: " +
                temp.getPrice();
        myQuantityField.setText(amount.toString());

        // Sets the text of the label to display the item information
        myLabel.setText(text);

        // Loads the image of the item and sets it to the image view
        Image image = new Image(getClass().getResourceAsStream("/images/" + temp.getItemPicture()));
        myImage.setImage(image);
    }
}