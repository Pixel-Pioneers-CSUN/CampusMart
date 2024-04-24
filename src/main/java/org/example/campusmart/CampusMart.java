package org.example.campusmart;

import Cart.Cart;
import controllers.HeaderBarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import items.*;
import utils.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CampusMart extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        //==============
        // start of app pull data and then make a data structure
        DatabaseUtility bd = new DatabaseUtility();
        bd.setTable("Item_Database");

        // create data structure for Items
        ItemDataStructure data = ItemDataStructure.getInstance();
        data.setItemDataStructure(bd.createHashMapItemClass());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String test = df.format(new Date());
        System.out.println("Date Test: " + test);

        /*
         * test for cart object
         * Using Singleton Obj of cart
         */
        Cart cart = Cart.getInstance();
        cart.createCart();

        cart.addToCart(1,4);
        cart.addToCart(4,7);
        cart.addToCart(23,5);
        cart.addToCart(34,2);
        cart.addToCart(2,1);
        System.out.println("Cart object test add to cart:\n" + cart);

        cart.removeFromCart(23);
        System.out.println("Cart object test remove from cart:\n" + cart);
        System.out.println("End of test");

        // load the HeaderBar.fxml file and set is as the headerBar
        FXMLLoader headerLoader = new FXMLLoader(getClass().getResource("/view/HeaderBar.fxml"));
        Parent headerRoot = headerLoader.load();

        // get the HeaderBarController instance and set the main stage
        HeaderBarController headerController = headerLoader.getController();
        headerController.setMainStage(primaryStage);

        // load the HomeScreen.fxml file
        //FXMLLoader homeLoader = new FXMLLoader(CampusMart.class.getResource("/view/HomeScreen.fxml"));
        //FXMLLoader homeLoader = new FXMLLoader(CampusMart.class.getResource("/view/OrderPage.fxml"));
        FXMLLoader homeLoader = new FXMLLoader(CampusMart.class.getResource("/view/AccountDashboard.fxml"));
        //FXMLLoader homeLoader = new FXMLLoader(CampusMart.class.getResource("/view/HomeScreen.fxml"));

        Parent homeRoot = homeLoader.load();

        // setting homeRoot as the root node of the scene
        Scene scene = new Scene(homeRoot, 1300, 800);   // also set the initial window size





        // get the HeaderBarController instance and set the main stage


        //scene.getStylesheets().add(getClass().getResource("/css/checkout-styles.css").toExternalForm());

//        Parent root = FXMLLoader.load(getClass().getResource("/view/ItemDisplay.fxml"));
//
//        Scene scene = new Scene(root);
//        String css = this.getClass().getResource("/css/items-styles.css").toExternalForm();
//        scene.getStylesheets().add(css);
        // load the HomeScreen.fxml file




        // setting the title of the stage
        primaryStage.setTitle("Welcome to Campus Mart");

        // setting the stage to not be resizable (fixed size)
        primaryStage.setResizable(false);

        // setting the scene on the stage
        primaryStage.setScene(scene);

        // center the scene on the display
        primaryStage.centerOnScreen();

        // show the stage
        primaryStage.show();
    }
}