import java.util.Random;

public class RestockThread extends Thread {
    private final Item item;

    public RestockThread(Item item) {
        this.item = item;
    }

    public void run() {
        try {
            System.out.println("상품을 추가중입니다...");
            Thread.sleep(2000);

            Random random = new Random();
            int restockAmount = random.nextInt(10) + 1;
            System.out.println(item.getName() + "의 개수가 " + restockAmount + "개 추가되었습니다.");
            item.decreaseQuantity(-restockAmount);
        }catch (Exception e){
            System.out.println(Constants.ADD_PRODUCT_ERROR_MESSAGE + e.getMessage());
        }
    }
}
