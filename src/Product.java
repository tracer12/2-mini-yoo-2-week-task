public class Product extends BaseProduct {
    public Product(String name, int price, int quantity) {
        super(name, price, quantity);
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }
}
