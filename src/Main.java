import java.util.Scanner;

public class Main {

    public static void displayCustomer(Customer<?> customer) {
        System.out.println("Customer Name : " + customer.getName());
        System.out.println("Customer ID   : " + customer.getCustomerId());
        System.out.println("Customer ID Type : "
                + customer.getCustomerId().getClass().getSimpleName());
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

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        OrderManager manager = new OrderManager();

        System.out.println("=== Food Ordering System ===");

        boolean addAnotherOrder = true;

        while (addAnotherOrder) {

            System.out.print("\nEnter Customer Name: ");
            String name = input.nextLine();

            System.out.print("Enter Customer ID: ");
            String customerId = input.nextLine();

            System.out.print("Enter Order Code: ");
            String orderCode = input.nextLine();

            Customer<String> customer =
                    new Customer<>(name, customerId);

            FoodOrder order =
                    new FoodOrder(orderCode, customer);

            boolean addAnotherItem = true;

            while (addAnotherItem) {

                System.out.print("\nEnter Menu Name: ");
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

                        System.out.println(
                                "Quantity must be positive."
                        );

                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Enter a valid whole number."
                        );
                    }
                }

                OrderItem item =
                        new OrderItem(menuName, category, quantity);

                order.addItem(item);

                System.out.print("Add another item? (y/n): ");
                addAnotherItem =
                        input.nextLine().equalsIgnoreCase("y");
            }

            manager.addOrder(order);

            System.out.print("Add another order? (y/n): ");
            addAnotherOrder =
                    input.nextLine().equalsIgnoreCase("y");
        }

        manager.displayOrders();

        System.out.print("\nSearch order code: ");
        String searchCode = input.nextLine();

        FoodOrder foundOrder = manager.searchOrder(searchCode);

        if (foundOrder != null) {
            System.out.println("\n=== SEARCH RESULT ===");
            System.out.println(
                    "Order Code: " + foundOrder.getOrderCode()
            );
            displayCustomer(foundOrder.getCustomer());
        } else {
            System.out.println("Order not found.");
        }

        System.out.print("\nRemove an order? (y/n): ");

        if (input.nextLine().equalsIgnoreCase("y")) {

            System.out.print("Enter order code to remove: ");
            String removeCode = input.nextLine();

            if (manager.removeOrder(removeCode)) {
                System.out.println(
                        "Order removed successfully."
                );
            } else {
                System.out.println("Order not found.");
            }
        }

        System.out.println("\n=== FINAL ORDER LIST ===");
        manager.displayOrders();

        input.close();
    }
}
