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
import org.controlsfx.control.PropertySheet;

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
    private MyListener myListener;
    private int selectedItemNumber;

    @FXML
    public void displayItemInformation(ItemClass item) {
        itemName.setText(item.getItemName());
        itemPrice.setText("$" + item.getPrice().toString());
        selectedItemNumber = item.getItemNumber();
        Image image;
        image = new Image(getClass().getResourceAsStream("/images/" + item.getItemPicture()));
        itemImage.setImage(image);

    }

    @FXML
    public void clickAddToCart(ActionEvent event) {
        Integer amount = myChoiceBox.getValue();
        // pass into function item number and amount they want
        // value has to be passed to shopping cart
        // maybe update picture of cart with a new value on the side
        // confirmation of added to cart??

    }
    public  void createItemGridPage(String category) {
        // create data structure for Items
        ItemDataStructure data = ItemDataStructure.getInstance();

        Iterator<HashMap.Entry<Integer, ItemClass>> it = data.getItemDataStructure().entrySet().iterator();


        // possibly a switch case for what category to make?
        int column = 0;
        int row = 1;
        try {
            for(ItemClass item : data.getItemDataStructure().values()) {
            //while (it.hasNext()) {
                // Get the next entry in the iterator
                ItemClass entry = new ItemClass();
                if(item.getCategory().equals(category)) {
                    entry = item;
                }
                else {
                    continue;
                }




                //HashMap.Entry<Integer, ItemClass> entry = it.next();

                // Create a grid pane that has all items information on a
                // page. Take the fxml that holds the item product display
                // and populate a grind pane with it.
                // This action will eventually have to be made each time going to item page

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();

                // Set the information for item view
                //System.out.println(entry);
                itemController.setData(entry, myListener);


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
            System.out.println("Failed to create item page!");
            //e.printStackTrace();
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

        //switch case??? to pick between.
        String category = " ";
        switch (category) {
            case "snacks" :
                createItemGridPage(category);
                break;
                case "drinks" :
                    createItemGridPage(category);
        }
        createItemGridPage("drinks");



        Integer[] choices;
        choices = new Integer[10];
        for (int i = 0; i < choices.length; i++) {
            choices[i] = i;

        }
        myChoiceBox.getItems().addAll(choices);

    }
}







