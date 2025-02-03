public class Item extends Product {
    public Item(String name, int price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public String toString() {
        return String.format("%-7s %-10d %-10d", name, price, quantity);
    }
}
