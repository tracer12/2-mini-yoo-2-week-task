public class Product extends BaseProduct {
    private final int price;

    public Product(int order, String name, int price) {
        super(order, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
