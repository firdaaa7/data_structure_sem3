
import java.util.Scanner;

public class Main {

    public static void displayCustomer(Customer<?> customer) {
        System.out.println("Customer Name : " + customer.getName());
        System.out.println("Customer ID   : " + customer.getCustomerId());
        System.out.println(
                "Customer ID Type : "
                        + customer.getCustomerId().getClass().getSimpleName()
        );
    }

    public static FoodCategory selectCategory(Scanner input) {
        while (true) {
            System.out.println("\nSelect Food Category:");
            System.out.println("1. FOOD");
            System.out.println("2. BEVERAGE");
            System.out.println("3. DESSERT");
            System.out.print("Enter choice: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    return FoodCategory.FOOD;
                case "2":
                    return FoodCategory.BEVERAGE;
                case "3":
                    return FoodCategory.DESSERT;
                default:
                    System.out.println("Invalid category. Try again.");
            }
        }
    }

    public static void addNewOrder(Scanner input, OrderManager manager) {
        System.out.print("Enter Customer Name: ");
        String name = input.nextLine();

        System.out.print("Enter Customer ID: ");
        String customerId = input.nextLine();

        System.out.print("Enter Order Code: ");
        String orderCode = input.nextLine();

        if (manager.searchOrder(orderCode) != null) {
            System.out.println("Order code already exists.");
            return;
        }

        Customer<String> customer = new Customer<>(name, customerId);
        FoodOrder order = new FoodOrder(orderCode, customer);

        boolean addAnotherItem = true;

        while (addAnotherItem) {
            System.out.print("Enter Menu Name: ");
            String menuName = input.nextLine();

            FoodCategory category = selectCategory(input);
            int quantity;

            while (true) {
                System.out.print("Enter Quantity: ");
                String quantityInput = input.nextLine();

                try {
                    quantity = Integer.parseInt(quantityInput);

                    if (quantity > 0) {
                        break;
                    }

                    System.out.println("Quantity must be positive.");
                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid whole number.");
                }
            }

            OrderItem item = new OrderItem(menuName, category, quantity);
            order.addItem(item);

            System.out.print("Add another item to this order? (y/n): ");
            addAnotherItem = input.nextLine().equalsIgnoreCase("y");
        }

        manager.addOrder(order);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        OrderManager manager = new OrderManager();

        boolean running = true;

        while (running) {
            System.out.println("\n=== Food Ordering System ===");
            System.out.println("1. Add Customer Order");
            System.out.println("2. Display Active Orders");
            System.out.println("3. Display Order Queue");
            System.out.println("4. Process Next Order");
            System.out.println("5. Undo Last Transaction");
            System.out.println("6. Display Transaction History");
            System.out.println("7. Exit");
            System.out.print("Choose menu: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    addNewOrder(input, manager);
                    break;

                case "2":
                    manager.displayOrders();
                    break;

                case "3":
                    manager.displayQueue();
                    break;

                case "4":
                    manager.processNextOrder();
                    break;

                case "5":
                    manager.undoLastTransaction();
                    break;

                case "6":
                    manager.displayTransactionHistory();
                    break;

                case "7":
                    running = false;
                    System.out.println("Thank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid menu choice.");
            }
        }

        input.close();
    }
}
