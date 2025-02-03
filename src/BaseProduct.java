public class BaseProduct {
    protected int order;
    protected String name;
    protected int price;
    protected int quantity;

    public BaseProduct(int order, String name, int price, int quantity) {
        this.order = order;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public int getOrder(){
        return order;
    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
