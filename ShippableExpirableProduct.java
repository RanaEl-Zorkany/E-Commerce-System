import java.time.LocalDate;

public class ShippableExpirableProduct extends ExpirableProduct implements Shippable {
    private Double weight;

    public ShippableExpirableProduct(String name, double price, int quantity, LocalDate expiryDate, Double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;

    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
