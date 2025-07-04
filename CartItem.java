public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public boolean isExpired() {
        return product.isExpired();
    }

    public boolean isOutOfStock() {
        return product.isOutOfStock() || product.getQuantity() < quantity;
    }

    public boolean requiresShipping() {
        return product.requiresShipping();
    }
}
