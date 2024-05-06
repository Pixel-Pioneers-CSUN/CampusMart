package Cart;

import items.ItemClass;
import items.ItemDataStructure;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 4/20/24
 * @author Erick Espinoza
 * Singleton class representing a shopping cart.
 * A HashMap is chosen as a data structure for its speed to access entries
 */
public class Cart {

    private static final Cart cart = new Cart();

    private BigDecimal subtotal;
    private HashMap<Integer, Integer> cartItems;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private Cart() {}

    /**
     * Returns the singleton instance of the Cart.
     *
     * @return The singleton instance of Cart.
     */
    public static Cart getInstance() {
        return cart;
    }

    /**
     * Initializes the shopping cart by setting subtotal to zero and creating an empty cartItems HashMap.
     */
    public void createCart() {
        this.subtotal = new BigDecimal("0.00");
        this.cartItems = new HashMap<>();
    }

    /**
     * Retrieves the items in the cart.
     *
     * @return The HashMap containing items in the cart.
     */
    public HashMap<Integer, Integer> getCartItems() {
        return cartItems;
    }

    /**
     * Retrieves the subtotal of the cart.
     *
     * @return The subtotal of the cart.
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Sets the subtotal of the cart.
     *
     * @param subtotal The new subtotal to be set.
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Sets the items in the cart.
     *
     * @param cartItems The HashMap containing items to be set in the cart.
     */
    public void setCartItems(HashMap<Integer, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Adds an item to the cart with the specified quantity.
     *
     * @param itemNumber The number of the item to be added.
     * @param quantity   The quantity of the item to be added.
     */
    public void addToCart(int itemNumber, int quantity) {
        if(quantity == 0) {
            removeFromCart(itemNumber);
            return;
        }
        cartItems.put(itemNumber, quantity);
        updateSubtotal();
    }

    /**
     * Removes an item from the cart.
     *
     * @param itemNumber The number of the item to be removed.
     */
    public void removeFromCart(int itemNumber) {
        cartItems.remove(itemNumber);
        updateSubtotal();
    }

    /**
     * Recalculates the subtotal of the cart based on the items and their quantities.
     */
    public void updateSubtotal() {
        //get the item data
        ItemDataStructure list = ItemDataStructure.getInstance();
        BigDecimal updatedSubtotal = new BigDecimal("0.00");

        for (Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
            ItemClass temp = list.getItemDataStructure().get(entry.getKey());
            updatedSubtotal = updatedSubtotal.add(temp.getPrice().multiply(new BigDecimal(entry.getValue())));
        }
        setSubtotal(updatedSubtotal);
    }

    public void clearCart(){
        cartItems.clear();
        setSubtotal(BigDecimal.ZERO);
    }

    /**
     * Provides a string representation of the shopping cart.
     *
     * @return A string representation of the shopping cart including cart items and subtotal.
     */
    @Override
    public String toString() {
        return "Cart Items: " + this.cartItems + "\nTotal: $" + this.subtotal.toString();
    }
}
