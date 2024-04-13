package controllers;

import items.ItemClass;
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
    public void setData(ItemClass item, int amount){
        this.item = item;
        myAmount.setText(String.valueOf(amount));
        myItemName.setText(item.getItemName());
        Image image = new Image(getClass().getResourceAsStream("/images/" + item.getItemPicture()));
        myOrderItemImage.setImage(image);


    }
}
