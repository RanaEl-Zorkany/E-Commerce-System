import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {

        if (quantity <= product.getQuantity()) {
            items.add(new CartItem(product, quantity));
        } else {
            System.out.println("Error: Cannot add " + quantity + " " + product.getName() +
                    "s. Only " + product.getQuantity() + " available.");
        }
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
