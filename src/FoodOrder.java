import java.util.LinkedList;

public class FoodOrder {

    private String orderCode;
    private Customer<?> customer;
    private LinkedList<OrderItem> items;

    public FoodOrder(String orderCode,
                     Customer<?> customer) {
        this.orderCode = orderCode;
        this.customer = customer;
        this.items = new LinkedList<>();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public Customer<?> getCustomer() {
        return customer;
    }

    public LinkedList<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }
}
