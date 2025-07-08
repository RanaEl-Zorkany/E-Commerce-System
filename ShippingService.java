import java.util.List;

public class ShippingService {
    private final double FEES = 30;

    public void ship(List<Product> items) {
        if (items.isEmpty())
            return;

        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;

        for (Product item : items) {
            double weight = item.getWeight();
            System.out.println(item.getName() + " " + (int) (weight * 1000) + "g");
            totalWeight += weight;
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }

    public double calculateShippingFee(List<Product> items) {
        if (items.isEmpty())
            return 0.0;

        double totalWeight = 0.0;
        for (Product item : items) {
            double weight = item.getWeight();
            System.out.println(item.getName() + " " + (int) (weight * 1000) + "g");
            totalWeight += weight;
        }

        return totalWeight * FEES;
    }
}
