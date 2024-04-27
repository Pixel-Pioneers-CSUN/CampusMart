package orders;

import items.ItemClass;
import utils.DatabaseUtility;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 4/13/24
 * @author Erick Espinoza
 * Class representing an order.
 * A HashMap is chosen as a data structure for its speed to access entries
 */
public class Orders {

    private int orderID;
    private int customerID;
    private String date;
    private BigDecimal total;
    private HashMap<Integer, Integer> orderItems;

    /**
     * Default constructor for Orders.
     */
    public Orders() {
        this.orderID = 0;
        this.customerID = 0;
        this.date = "";
        this.total = BigDecimal.ZERO;
        this.orderItems = new HashMap<>();
    }

    /**
     * Constructor for Orders.
     *
     * @param orderID    The order ID.
     * @param customerID The customer ID.
     * @param date       The date of the order.
     * @param total      The total cost of the order.
     * @param orderItems The items in the order and their quantities.
     */
    public Orders(int orderID, int customerID, String date, BigDecimal total, HashMap<Integer, Integer> orderItems) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.date = date;
        this.total = total;
        this.orderItems = orderItems;
    }

    // Setters
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setOrderItems(HashMap<Integer, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    // Getters
    public int getOrderID() {
        return this.orderID;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public String getDate() {
        return this.date;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public HashMap<Integer, Integer> getOrderItems() {
        return this.orderItems;
    }

    /**
     * Adds the order to the database.
     */
    public void addToDatabase() {
        String orderQuery = "INSERT INTO Orders_Database (customerID, orderID, orderDate, orderTotal) VALUES ("
                + getCustomerID() + ", " + getOrderID() + ", '" + getDate() + "', " + getTotal() + ")";
        String orderItemsQuery;
        DatabaseUtility db = new DatabaseUtility();
        try {
            db.updateOrderDatabase(orderQuery);
            for (Map.Entry<Integer, Integer> entry : getOrderItems().entrySet()) {
                orderItemsQuery = "INSERT INTO OrderItems_Database (orderNumber, itemNumber, itemCount) VALUES ("
                        + getOrderID() + ", " + entry.getKey() + ", " + entry.getValue() + ")";
                db.updateOrderItemsDatabase(orderItemsQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints out the order details.
     *
     * @return The string representation of the order.
     */
    @Override
    public String toString() {
        return orderID + "," + customerID + "," + date + "," + total + "," + orderItems;
    }
}
