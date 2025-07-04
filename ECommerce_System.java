import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ECommerce_System {
    private static ShippingService shippingService = new ShippingService();

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double subtotal = 0;
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (item.isExpired()) {
                System.out.println("Your cart has one or more expired products");
                System.out.println("Expired product: " + product.getName());
                return;
            }
            if (item.isOutOfStock()) {
                System.out.println("Your cart has one or more out of stock products");
                System.out.println("Product out of stock: " + product.getName());
                return;
            }
            subtotal += item.getSubtotal();

        }

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().requiresShipping()) {
                Shippable shippableItem = new Shippable() {
                    @Override
                    public String getName() {
                        return item.getQuantity() + "x " + item.getProduct().getName();
                    }

                    @Override
                    public double getWeight() {
                        return item.getProduct().getWeight() * item.getQuantity();
                    }
                };
                shippableItems.add(shippableItem);
            }
        }

        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            System.out.println("Insufficient balance.");
            System.out.println("Available: " + customer.getBalance() + "$, Required: " + totalAmount + "$");
            return;
        }

        customer.setNewBalance(totalAmount);

        if (!shippableItems.isEmpty()) {
            shippingService.ship(shippableItems);
        }

        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " +
                    item.getProduct().getName() + "\t" +
                    item.getSubtotal());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t" + subtotal + "$");
        System.out.println("Shipping\t" + shippingFee + "$");
        System.out.println("Amount\t\t" + totalAmount + "$");
        System.out.println("Current Balance\t" + customer.getBalance() + "$");

        cart.clear();
    }

    public static void main(String[] args) {
        Product cheese = new Product("Cheese", 100.0, 10, LocalDate.of(2030, 1, 1), 0.2);
        Product biscuit = new Product("Biscuits", 150.0, 5, LocalDate.of(2030, 1, 1), 0.7);
        Product tv = new Product("TV", 2000.0, 3, null, 20.0);
        Product scratchCard = new Product("Scratch Card", 10.0, 100, null, null);

        // CASE 1: SUCCESSFULL CHECKOUT
        Customer customer = new Customer(10000.0);

        Cart cart = customer.getCart();
        cart.addItem(cheese, 2);
        cart.addItem(biscuit, 1);
        cart.addItem(scratchCard, 1);

        System.out.println("CASE 1: SUCCESSFULL CHECKOUT");
        checkout(customer, cart);

        // CASE 2: INSUFFICIENT BALANCE
        Customer customer2 = new Customer(100.0);

        Cart cart2 = customer2.getCart();
        cart2.addItem(tv, 2);

        System.out.println("\nCASE 2: INSUFFICIENT BALANCE");
        checkout(customer2, cart2);

        // CASE 3: EMPTY CART
        Customer customer3 = new Customer(10000.0);

        System.out.println("\nCASE 3: EMPTY CART");
        checkout(customer3, customer3.getCart());

        // CASE 4: OUT OF STOCK
        Customer customer4 = new Customer(10000.0);

        Cart cart4 = customer4.getCart();
        System.out.println("\nCASE 4: OUT OF STOCK");
        cart4.addItem(tv, 4);

        // CASE 5: EXPIRED PRODUCT
        Customer customer5 = new Customer(10000.0);
        Product expiredCheese = new Product("Cheese", 100.0, 10, LocalDate.of(2020, 1, 1), 0.2);
        Cart cart5 = customer5.getCart();
        cart5.addItem(expiredCheese, 4);

        System.out.println("\nCASE 5: EXPIRED PRODUCT");
        checkout(customer5, cart5);

        // CASE 6: NO SHIPPABLE ITEMS
        Customer customer6 = new Customer(10000.0);
        Cart cart6 = customer6.getCart();
        cart6.addItem(scratchCard, 4);

        System.out.println("\nCASE 6: NO SHIPPABLE ITEMS");
        checkout(customer6, cart6);
    }
}
