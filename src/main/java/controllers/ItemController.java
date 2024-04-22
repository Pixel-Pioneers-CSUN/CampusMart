package controllers;

import items.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemController {

    @FXML
    private ImageView itemImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    private ItemClass item;
    private MyListener myListener;

    @FXML
    void click(MouseEvent event) {
        myListener.onClickListener(item);
    }
    /**
    * Sets data of an item and updates corresponding UI components.
    *
    * @param item       The item to set data for.
    * @param myListener The listener for item events.
    */
    public void setData(ItemClass item, MyListener myListener) {

        this.item = item;
        this.myListener = myListener;
        nameLabel.setText(item.getItemName());
        priceLabel.setText("$" + item.getPrice().toString());
        Image image = new Image(getClass().getResourceAsStream("/images/" + item.getItemPicture()));
        itemImage.setImage(image);

    }



}
