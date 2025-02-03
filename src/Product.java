public class Product extends BaseProduct {
    public Product(int order, String name, int price, int quantity) {
        super(order, name, price, quantity);
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }
}
