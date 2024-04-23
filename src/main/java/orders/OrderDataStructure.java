package orders;

import java.util.List;

/**
 * 4/13/24
 * Erick Espinoza
 * Singleton class representing the data structure for orders.
 * A List attribute is chosen for its simplicity
 */
public class OrderDataStructure {
    private static final OrderDataStructure singleton = new OrderDataStructure();
    private List<Orders> orderList;

    private OrderDataStructure() {}

    /**
     * Returns the singleton instance of OrderDataStructure.
     *
     * @return The singleton instance.
     */
    public static OrderDataStructure getInstance() {
        return singleton;
    }

    /**
     * Returns the list of orders.
     *
     * @return The list of orders.
     */
    public List<Orders> getOrderList() {
        return orderList;
    }

    /**
     * Sets the list of orders.
     *
     * @param orderList The list of orders to set.
     */
    public void setOrderList(List<Orders> orderList) {
        this.orderList = orderList;
    }
}
