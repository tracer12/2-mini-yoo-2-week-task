public class BaseProduct {
    private int order;
    private String name;

    public BaseProduct(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }
}
