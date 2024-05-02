package org.example.campusmart;

import Cart.Cart;
import controllers.CartItemsController;
import items.ItemDataStructure;
import org.junit.jupiter.api.Test;
import utils.DatabaseUtility;

import javax.xml.crypto.Data;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CampusMartTest {

    @Test
    void main() {
    }

    @Test
    void start() {

        DatabaseUtility db = new DatabaseUtility();
        ItemDataStructure itemData = ItemDataStructure.getInstance();
        itemData.setItemDataStructure(db.createHashMapItemClass());


        // cart test of subtotal calculation
        Cart cart = Cart.getInstance();
        cart.createCart();
        cart.addToCart(1,2); //$2.99 * 2
        cart.addToCart(22,4); // $1.50 * 4
        cart.addToCart(23,5); // $1.50 * 5
        cart.addToCart(45,66); // $4.99 * 66
        // subtotal should be 348.82
        // getter is used to retrieve value to test updateSubtotal
        System.out.print("Cart Subtotal test: ");
        assertEquals(new BigDecimal("348.82"), cart.getSubtotal());
        System.out.println("Passed");

        // reset cart
        cart.createCart();

        // test of add to cart
        cart.addToCart(1,0);

        System.out.print("Add to Cart test add item with 0 quantity: ");
        //cart should be empty since there is no 0
        assertTrue(cart.getCartItems().isEmpty());
        System.out.println("Passed");

        System.out.print("Add to Cart test, update quantity of item in cart: ");
        cart.addToCart(1,3);
        cart.addToCart(1,5);
        // new value of item should be 5 not 3
        assertEquals(5,cart.getCartItems().get(1));
        System.out.println("Passed");

        System.out.print("Add to Cart test, remove an existing item when 0 passed as new quantity: ");
        cart.addToCart(1,0);
        // new value of item should be 5 not 3
        assertTrue(cart.getCartItems().isEmpty());
        System.out.println("Passed");

        System.out.print("Add to Cart test, total items are correct: ");
        cart.addToCart(1,3);
        cart.addToCart(2,32);
        cart.addToCart(3,54);
        cart.addToCart(4,23);
        cart.addToCart(5,10);
        // total should be 122
        assertEquals(122,cart.getCartQuantity());
        System.out.println("Passed");










    }
}