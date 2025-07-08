
public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        quantity -= amount;
    }

    public boolean isExpired() {
        return false;
    }

    public boolean expirable() {
        return false;
    }

    public boolean requiresShipping() {
        return false;
    }

    public double getWeight() {
        return 0.0;
    }

    public boolean isAvailable(int requested) {
        return quantity >= requested;
    }

    public boolean isOutOfStock() {
        return quantity <= 0;
    }
}
