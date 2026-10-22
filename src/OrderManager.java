import java.util.ArrayList;

public class OrderManager {

    private ArrayList<FoodOrder> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void addOrder(FoodOrder order) {
        orders.add(order);
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No active orders.");
            return;
        }

        System.out.println("\n=== ALL ACTIVE ORDERS ===");

        for (FoodOrder order : orders) {
            System.out.println("-------------------------");
            System.out.println(
                    "Order Code   : " + order.getOrderCode()
            );
            System.out.println(
                    "Customer Name: " + order.getCustomer().getName()
            );
            System.out.println(
                    "Customer ID  : " + order.getCustomer().getCustomerId()
            );
            System.out.println("Ordered Items:");

            for (OrderItem item : order.getItems()) {
                System.out.println(
                        "- " + item.getMenuName()
                                + " x" + item.getQuantity()
                                + " (" + item.getCategory() + ")"
                );
            }
        }

        System.out.println("-------------------------");
    }

    public FoodOrder searchOrder(String orderCode) {
        for (FoodOrder order : orders) {
            if (order.getOrderCode().equalsIgnoreCase(orderCode)) {
                return order;
            }
        }

        return null;
    }

    public boolean removeOrder(String orderCode) {
        FoodOrder order = searchOrder(orderCode);

        if (order != null) {
            orders.remove(order);
            return true;
        }

        return false;
    }
}
