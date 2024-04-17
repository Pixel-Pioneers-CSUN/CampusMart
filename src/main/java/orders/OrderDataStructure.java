package orders;


import java.util.List;

public class OrderDataStructure {
    private static final OrderDataStructure singleton = new OrderDataStructure();
    private List<Orders> OrderList;
    private OrderDataStructure() {}

    public static OrderDataStructure getInstance() {return singleton;}
    public List<Orders> getOrderList() {return OrderList;}
    public void setOrderList(List<Orders> OrderList) {this.OrderList = OrderList;}
}
