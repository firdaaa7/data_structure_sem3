public class Customer<T> {

    private String name;
    private T customerId;

    public Customer(String name, T customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public T getCustomerId() {
        return customerId;
    }
}