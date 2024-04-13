package orders;

import items.ItemClass;

import java.math.BigDecimal;
import java.util.*;

public class Orders {

    private int orderID;
    private int customerID;
    private Date date;
    private BigDecimal total;
    private HashMap<Integer,Integer> orderItems;


    // default constructor
    public Orders() {
        this.orderID = 0;
        this.customerID = 0;
        this.date = new Date();
        this.total = new BigDecimal(0);
        this.orderItems = new HashMap<>();

    }

    // constructor
    public Orders(int orderID, int customerID, Date date, BigDecimal total, HashMap<Integer,Integer> map) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.date = date;
        this.total = total;
        this.orderItems = map;

    }


    //setters
    public void setOrderID(int orderID) {this.orderID = orderID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}
    public void setDate(Date date) {this.date = date;}
    public void setTotal(BigDecimal total) {this.total = total;}
    public void setOrderItems(HashMap<Integer,Integer> orderItems) {this.orderItems = orderItems;}

    //getters
    public int getOrderID() {return this.orderID;}
    public int getCustomerID() {return this.customerID;}
    public Date getDate() {return this.date;}
    public BigDecimal getTotal() {return this.total;}
    public HashMap<Integer,Integer> getOrderItems() {return this.orderItems;}

    //print out order in terminal
    @Override
    public String toString() {
        return this.orderID + "," + this.customerID + "," + this.date + "," + this.total +
                "," + this.orderItems;

    }


}
