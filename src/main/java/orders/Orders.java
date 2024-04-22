package orders;

import items.ItemClass;
import utils.DatabaseUtility;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

public class Orders {

    private int orderID;
    private int customerID;
    private String date;
    private BigDecimal total;
    private HashMap<Integer,Integer> orderItems;


    // default constructor
    public Orders() {
        this.orderID = 0;
        this.customerID = 0;
        this.date = new String();
        this.total = new BigDecimal(0);
        this.orderItems = new HashMap<>();

    }

    // constructor
    public Orders(int orderID, int customerID, String date, BigDecimal total, HashMap<Integer,Integer> map) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.date = date;
        this.total = total;
        this.orderItems = map;

    }


    //setters
    public void setOrderID(int orderID) {this.orderID = orderID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}
    public void setDate(String date) {this.date = date;}
    public void setTotal(BigDecimal total) {this.total = total;}
    public void setOrderItems(HashMap<Integer,Integer> orderItems) {this.orderItems = orderItems;}

    //getters
    public int getOrderID() {return this.orderID;}
    public int getCustomerID() {return this.customerID;}
    public String getDate() {return this.date;}
    public BigDecimal getTotal() {return this.total;}
    public HashMap<Integer,Integer> getOrderItems() {return this.orderItems;}

    public void addToDataBase(){
        // will use the database structure to update the values.
        // need to add the order information into Orders_Database
        // then need to add the orderItems HashMap into OrderItems_Database

        // so need to create a functions in DB Utility
        String query = "insert into Orders_Database (customerID, orderID, orderDate, orderTotal) values ("
                + getCustomerID() + ", " + getOrderID() + ", '" + getDate() + "', " + getTotal().toString() +" )";

        DatabaseUtility db = new DatabaseUtility();
        try {
            db.updateOrderDatabase(query);
            for(Map.Entry<Integer,Integer> entry : orderItems.entrySet()) {
                String secondQuery =  "insert into OrderItems_Database (orderNumber, itemNumber, itemCount) VALUES (" +
                        getOrderID() + ", " + entry.getKey() + ", " + entry.getValue() + ")";
                db.updateOrderItemsDatabase(secondQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //print out order in terminal
    @Override
    public String toString() {
        return this.orderID + "," + this.customerID + "," + this.date + "," + this.total +
                "," + this.orderItems;

    }


}
