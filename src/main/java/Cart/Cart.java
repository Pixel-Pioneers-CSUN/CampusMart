package Cart;

import items.ItemClass;
import items.ItemDataStructure;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    BigDecimal subtotal;
    HashMap<Integer, Integer> cartItems;

    // default constructor
    public Cart() {
        subtotal = new BigDecimal(0);
        cartItems = new HashMap<>();
    }

    // constructor with parameters
    public Cart(BigDecimal subtotal, HashMap<Integer, Integer> cartItems) {
        this.subtotal = subtotal;
        this.cartItems = cartItems;
    }

    // getters
    public HashMap<Integer, Integer> getCartItems() {
        return cartItems;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    // setters
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setCartItems(HashMap<Integer, Integer> cartItems) {
        this.cartItems = cartItems;
    }


    // adds to cart by taking item number and quantity
    // and places into a cartItems hashmap
    public void addToCart(int itemNumber, int quantity) {
        cartItems.put(itemNumber, quantity);
        updateSubtotal();

    }
    // removes from the hashmap
    public void removeFromCart(int itemNunber) {
        cartItems.remove(itemNunber);
        updateSubtotal();
    }


    // updates the subtotal by going through the cartItems hashmap
    // and adding their price multiplied by quantity
    public void updateSubtotal() {
        ItemDataStructure list = ItemDataStructure.getInstance();
        BigDecimal updatedSubtotal = new BigDecimal("0.00");

        for(Map.Entry<Integer,Integer> entry : cartItems.entrySet()) {
            //get Item price * amount
            ItemClass temp = list.getItemDataStructure().get(entry.getKey());
            updatedSubtotal = updatedSubtotal.add(temp.getPrice().multiply(new BigDecimal(entry.getValue())));

        }
        setSubtotal(updatedSubtotal);
    }

    // able to print using System.out.print()
    @Override
    public String toString() {
        return "Cart Items: " + this.cartItems+ "\nTotal: $" + this.subtotal.toString();
    }
}
