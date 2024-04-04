package controllers;
import items.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ItemDisplayController implements Initializable {


    // These three are for the
    @FXML
    private BorderPane myBorderPane;
    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;

    @FXML
    private HBox headerPane;

    @FXML
    private TextArea myTextArea;

    @FXML
    private ImageView myImageView;

    @FXML
    private GridPane grid;

    @FXML
    private Button myAddToCart;

    @FXML
    private ChoiceBox<Integer> myChoiceBox;

    // might have to if this will work or not
    @FXML
    private ImageView headerBarLogoImage;

    private Integer[] choices;

    private MyListener myListener;
    private Image image;


    //Image myImage = new Image(getClass().getResourceAsStream("projectPhotos/soda.png"));
    //private ItemDataStructure tempData = ItemDataStructure.getInstance();


    // create a way to display all info and item page
    // basic to just work
    // Need to make it so it can be done for every item in database
    //  Will need a loop to make all full display objects
    @FXML
    private void displayItemInformation(ItemClass item) {
        itemName.setText(item.getItemName());
        itemPrice.setText(item.getPrice().toString());
        image = new Image(getClass().getResourceAsStream("/images/" + item.getItemPicture()));
        itemImage.setImage(image);

    }

    @FXML
    public void clickAddToCart(ActionEvent event) {
        Integer amount = myChoiceBox.getValue();
        // value has to be passed to shopping cart
        // maybe update picture of cart with a new value on the side
        // confirmation of added to cart??

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // start of app pull data and then make a data structure
        DatabaseUtility bd = new DatabaseUtility();
        bd.setTable("itemtable");

        // create data structure for Items
        ItemDataStructure data = ItemDataStructure.getInstance();
        data.setItemDataStructure(bd.createHasMapItemClass());

        Iterator<HashMap.Entry<Integer, ItemClass>> it = data.getItemDataStructure().entrySet().iterator();
        if (it.hasNext()) {
            // get the first entry in the iterator
            HashMap.Entry<Integer, ItemClass> entry = it.next();
            // display item information
            displayItemInformation(entry.getValue());
            // override the myListener to pass data between
            myListener = new MyListener() {
                @Override
                public void onClickListener(ItemClass item) {
                    displayItemInformation(item);
                }
            };
        }

        // reset Iterator
        it = data.getIterator();

        int column = 0;
        int row = 1;
        try {
            while (it.hasNext()) {
                // Get the next entry in the iterator
                HashMap.Entry<Integer, ItemClass> entry = it.next();

                // Create a grid pane that has all items information on a
                // page. Take the fxml that holds the item product display
                // and populate a grind pane with it.
                // This action will eventually have to be made each time going to item page

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();

                // Set the information for item view
                itemController.setData(entry.getValue(), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                // Set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                // Set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        choices = new Integer[25];
        for (int i = 0; i < choices.length; i++) {
            choices[i] = i;

        }
        myChoiceBox.getItems().addAll(choices);

        System.out.println("Finish initialize");
    }
}







