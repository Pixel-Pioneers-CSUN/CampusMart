package controllers;

import items.ItemClass;
import items.ItemDataStructure;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @version 4/13/24
 * @author Erick Espinoza
 * Controller class for managing the display of items in an order.
 */
public class OrderItemController {

    @FXML
    private Label myItemName;

    @FXML
    private ImageView myOrderItemImage;

    /**
     * Sets the data for an order item.
     *
     * @param item   The item to display.
     * @param amount The quantity of the item.
     */
    @FXML
    public void setData(ItemClass item, Integer amount) {

        String amountText = String.valueOf(amount);
        myItemName.setText("Item: " + item.getItemName() + "\nQty: " + amountText);
        Image image = new Image(getClass().getResourceAsStream("/images/" + item.getItemPicture()));
        myOrderItemImage.setImage(image);
    }
}
