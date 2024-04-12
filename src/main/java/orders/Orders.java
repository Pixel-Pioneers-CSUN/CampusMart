package orders;

import items.ItemClass;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.Date;

public class Orders {

    private int orderID;
    private int customerID;
    private Date date;
    private BigDecimal total;
    //private List<ItemClass> orderItems;


    // default constructor
    public Orders() {
        this.orderID = 0;
        this.customerID = 0;
        this.date = new Date();
        this.total = new BigDecimal(0);

    }

    // constructor
    public Orders(int orderID, int customerID, Date date, BigDecimal total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.date = date;
        this.total = total;

    }


    //setters
    public void setOrderID(int orderID) {this.orderID = orderID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}
    public void setDate(Date date) {this.date = date;}
    public void setTotal(BigDecimal total) {this.total = total;}

    //getters
    public int getOrderID() {return this.orderID;}
    public int getCustomerID() {return this.customerID;}
    public Date getDate() {return this.date;}
    public BigDecimal getTotal() {return this.total;}

    //print out order in terminal
    @Override
    public String toString() {
        return this.orderID + "," + this.customerID + "," + this.date + "," + this.total;
    }


}
