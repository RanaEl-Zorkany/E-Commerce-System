import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private LocalDate expiryDate;
    private Double weight;

    public Product(String name, double price, int quantity, LocalDate expiryDate, Double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.weight = weight;
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

    public void reduceQuantity(int q) {
        this.quantity -= q;
    }

    public boolean isAvailable(int requested) {
        return quantity >= requested;
    }

    public boolean isExpired() {
        return expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }

    public boolean requiresShipping() {
        return weight != null;
    }

    public double getWeight() {
        return weight != null ? weight : 0.0;
    }

    public boolean isOutOfStock() {
        return quantity <= 0;
    }
}
