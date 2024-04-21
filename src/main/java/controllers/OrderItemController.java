package controllers;

import items.ItemClass;
import items.ItemDataStructure;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import orders.Orders;

public class OrderItemController {

    @FXML
    private Label myAmount;

    @FXML
    private Label myItemName;

    @FXML
    private ImageView myOrderItemImage;

    private ItemClass item;

    //this will have set the order objects
    @FXML
    public void setData(ItemClass item, Integer amount){
        //ItemDataStructure data = ItemDataStructure.getInstance();

        this.item = item;

        String amountText = String.valueOf(amount);
        //myAmount.setText(amountText);
        myItemName.setText("Item: " + item.getItemName() + "\nQty: " + amountText);
        Image image = new Image(getClass().getResourceAsStream("/images/" + item.getItemPicture()));
        myOrderItemImage.setImage(image);



    }
}
