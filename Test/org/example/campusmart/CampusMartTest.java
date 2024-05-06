package org.example.campusmart;

import Cart.Cart;
import items.ItemDataStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DatabaseUtility;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CampusMartTest {


    @BeforeAll
    static void setUp() {
        DatabaseUtility db = new DatabaseUtility();
        ItemDataStructure itemData = ItemDataStructure.getInstance();
        itemData.setItemDataStructure(db.createHashMapItemClass());

    }

    @Test
    void testSubtotal() {
        Cart cart = Cart.getInstance();
        cart.createCart();
        cart.addToCart(1,2); //$2.99 * 2
        cart.addToCart(22,4); // $1.50 * 4
        cart.addToCart(23,5); // $1.50 * 5
        cart.addToCart(45,66); // $4.99 * 66
        // subtotal should be 348.82
        // getter is used to retrieve value to test updateSubtotal
        assertEquals(new BigDecimal("348.82"), cart.getSubtotal());
    }

    @Test
    void testRemoveFromCart() {
        Cart cart = Cart.getInstance();
        cart.createCart();
        cart.addToCart(1,2);
        cart.removeFromCart(1);
        assertTrue(cart.getCartItems().isEmpty());
    }

    @Test
    void addToCartTest1() {
        // first path of addToCart
        Cart cart = Cart.getInstance();
        cart.createCart();
        cart.addToCart(1,0);
        //cart should be empty since there is no 0
        assertTrue(cart.getCartItems().isEmpty());
    }
    @Test
    void addToCartTest2() {
        // second path of addToCart
        Cart cart = Cart.getInstance();
        cart.createCart();
        cart.addToCart(1,1);
        // check that cart is not empty
        assertFalse(cart.getCartItems().isEmpty());
    }
    @Test
    void addToCartTest3() {
        // test for update cart Item using addToCart
        Cart cart = Cart.getInstance();
        cart.createCart();
        cart.addToCart(1,3);
        cart.addToCart(1,5);
        // new value of item should be 5 not 3
        assertEquals(5,cart.getCartItems().get(1));
    }

    @Test
    void addToCartTest4() {
        // check for correct quantity
        Cart cart = Cart.getInstance();
        cart.createCart();
        cart.addToCart(1,3);
        cart.addToCart(2,32);
        cart.addToCart(3,54);
        cart.addToCart(4,23);
        cart.addToCart(5,10);
        // total should be 122
        assertEquals(122,cart.getCartQuantity());
    }



}