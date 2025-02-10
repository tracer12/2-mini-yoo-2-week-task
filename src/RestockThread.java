import java.util.Random;

public class RestockThread extends Thread {
    private final Item item;

    public RestockThread(Item item) {
        this.item = item;
    }

    public void run() {
        try {
            Random random = new Random();
            int restockAmount = random.nextInt(10) + 1;
            System.out.println(item.getName() + "의 개수가 " + restockAmount + "개 추가되었습니다.");
            item.decreaseQuantity(-restockAmount);
        }catch (Exception e){
            System.out.println("재고 추가 중 오류 발생" + e.getMessage());
        }
    }
}
