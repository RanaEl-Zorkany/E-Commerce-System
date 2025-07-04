public class Customer {
    private double balance;
    private Cart cart;

    public Customer(double balance) {
        this.balance = balance;
        this.cart = new Cart();
    }

    public double getBalance() {
        return balance;
    }

    public void setNewBalance(double amount) {
        this.balance -= amount;
    }

    public Cart getCart() {
        return cart;
    }
}
