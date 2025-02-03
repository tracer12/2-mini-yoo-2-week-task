import java.util.ArrayList;
import java.util.List;

public class ProductList {
    public static List<Item> initializeProductList() {
        List<Item> productList = new ArrayList<>();
        productList.add(new Item(1,"콜라", 1000, 10));
        productList.add(new Item(2,"사이다", 1000, 8));
        productList.add(new Item(3,"물", 500, 28));
        productList.add(new Item(4,"감자칩", 1500, 7));
        productList.add(new Item(5,"초코바", 1200, 15));
        productList.add(new Item(6,"정식도시락", 6400, 8));
        productList.add(new Item(7,"컵라면", 1700, 15));
        productList.add(new Item(8,"삼각김밥", 2000, 12));
        productList.add(new Item(9,"소주", 5000, 9));
        productList.add(new Item(10,"맥주", 2500, 13));
        return productList;
    }
}
