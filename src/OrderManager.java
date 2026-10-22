
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class OrderManager {

    private ArrayList<FoodOrder> orders;
    private Queue<FoodOrder> processingQueue;
    private Stack<FoodOrder> transactionHistory;

    public OrderManager() {
        orders = new ArrayList<>();
        processingQueue = new LinkedList<>();
        transactionHistory = new Stack<>();
    }

    public void addOrder(FoodOrder order) {
        orders.add(order);
        processingQueue.offer(order);
        System.out.println("Order added successfully.");
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No active orders.");
            return;
        }

        System.out.println("\n=== ALL ACTIVE ORDERS ===");

        for (FoodOrder order : orders) {
            displayOrderDetails(order);
        }
    }

    public void displayOrderDetails(FoodOrder order) {
        System.out.println("-------------------------");
        System.out.println("Order Code   : " + order.getOrderCode());
        System.out.println("Customer Name: " + order.getCustomer().getName());
        System.out.println("Customer ID  : " + order.getCustomer().getCustomerId());
        System.out.println("Ordered Items:");

        for (OrderItem item : order.getItems()) {
            System.out.println(
                    "- " + item.getMenuName()
                            + " x" + item.getQuantity()
                            + " (" + item.getCategory() + ")"
            );
        }

        System.out.println("-------------------------");
    }

    public void displayQueue() {
        if (processingQueue.isEmpty()) {
            System.out.println("Order queue is empty.");
            return;
        }

        System.out.println("\n=== ORDER PROCESSING QUEUE ===");

        int position = 1;

        for (FoodOrder order : processingQueue) {
            System.out.println(
                    position++ + ". "
                            + order.getOrderCode()
                            + " - " + order.getCustomer().getName()
            );
        }
    }

    public void processNextOrder() {
        FoodOrder order = processingQueue.poll();

        if (order == null) {
            System.out.println("No orders to process.");
            return;
        }

        orders.remove(order);
        transactionHistory.push(order);

        System.out.println("\n=== PROCESSING ORDER ===");
        System.out.println("Processing order: " + order.getOrderCode());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Order processed successfully.");
    }

    public void undoLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction to undo.");
            return;
        }

        FoodOrder order = transactionHistory.pop();

        if (!orders.contains(order)) {
            orders.add(order);
        }

        if (!processingQueue.contains(order)) {
            processingQueue.offer(order);
        }

        System.out.println("\nLast transaction undone successfully.");
        System.out.println("Restored order: " + order.getOrderCode());
    }

    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
            return;
        }

        System.out.println("\n=== TRANSACTION HISTORY ===");

        for (int i = transactionHistory.size() - 1; i >= 0; i--) {
            FoodOrder order = transactionHistory.get(i);

            System.out.println(
                    (transactionHistory.size() - i)
                            + ". " + order.getOrderCode()
                            + " - " + order.getCustomer().getName()
            );
        }
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
            processingQueue.remove(order);
            return true;
        }

        return false;
    }
}
