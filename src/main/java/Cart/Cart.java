package Cart;

import java.util.HashMap;

public class Cart {

    double subtotal;
    HashMap<Integer,Integer> cartItems;


    public void addToCart(int item, int quantity) {
        cartItems.put(item, quantity);
    }



}
