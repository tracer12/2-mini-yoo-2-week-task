public class Item extends Product {
    private int quantity;

    public Item(int order, String name, int price, int quantity) {
        super(order, name, price);
        this.quantity = quantity;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

    public int getQuantity() {
        return quantity;
    }
}
