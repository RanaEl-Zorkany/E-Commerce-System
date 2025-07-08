public class ShippableProduct extends Product implements Shippable {
    private Double weight;

    public ShippableProduct(String name, double price, int quantity, Double weight) {
        super(name, price, quantity);
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