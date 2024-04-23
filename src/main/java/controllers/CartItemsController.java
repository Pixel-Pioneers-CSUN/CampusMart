package controllers;

import items.ItemClass;
import items.ItemDataStructure;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartItemsController {

    @FXML
    private Label myLabel;

    @FXML
    private ImageView myImage;

    public void setData(Integer item, Integer amount) {

        ItemDataStructure data = ItemDataStructure.getInstance();
        ItemClass temp = data.getItemDataStructure().get(item);
        String text = "Item: " + temp.getItemName() +
                "\nQty: " + amount.toString() + "\t Price: " +
                temp.getPrice();
        myLabel.setText(text);
        Image image = new Image(getClass().getResourceAsStream("/images/" + temp.getItemPicture()));
        myImage.setImage(image);
    }

}
