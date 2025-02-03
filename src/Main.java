import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int EXIT_OPTION = -1;
    private static final int MIN_QUANTITY = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = initializeProductList();
        int totalPrice = 0;
        int purchaseFlag = 0;
        int convenienceFlag = 0;
        //상품 추가 로직
        while (true) {
            if (purchaseFlag == 1) {
                break;
            }
            System.out.println("===== 편의점 상품 목록 =====");
            System.out.println("재고        가격        수량");
            for (int i = 0; i < items.size(); i++) {
                System.out.println(i + 1 + ". " + items.get(i));
            }
            System.out.println("0. 종료");
            System.out.print("무슨 상품을 구매하시겠습니까? (번호 입력): ");

            int productIndex = -1;
            try {
                productIndex = Integer.parseInt(scanner.nextLine()) - 1;

                if (productIndex == EXIT_OPTION) {
                    System.out.println("프로그램을 종료합니다.");
                    convenienceFlag = 1;
                    break;
                }

                if (productIndex < 0 || productIndex >= items.size()) {
                    throw new IllegalArgumentException("유효하지 않은 상품 번호입니다.");
                }

                Item selectedItem = items.get(productIndex);

                System.out.print(selectedItem.getName() + " 몇 개를 구매하시겠습니까? ");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity < MIN_QUANTITY || quantity > selectedItem.getQuantity()) {
                    throw new IllegalArgumentException("재고가 부족하거나 유효하지 않은 수량입니다.");
                }

                totalPrice += selectedItem.getPrice() * quantity;
                selectedItem.decreaseQuantity(quantity);
                System.out.println(selectedItem.getName() + " " + quantity + "개 추기되었습니다.");
                System.out.println("현재가격: " + totalPrice + "원");

                System.out.print("계산을 진행하시겠습니까? Y/N: ");
                while (true) {
                    try {
                        String input = scanner.nextLine().toUpperCase();

                        if (input.equals("N")) {
                            break;
                        } else if (input.equals("Y")) {
                            purchaseFlag = 1;
                            break;
                        } else {
                            throw new IllegalArgumentException("잘못된 입력입니다, 다시 입력해주세요.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.print("계산을 진행하시겠습니까? Y/N: ");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 입력입니다. 다시 시도해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if (convenienceFlag == 0) {
            while (true) {
                try {
                    System.out.print("금액을 입력하세요: ");
                    int amountPaid = Integer.parseInt(scanner.nextLine());

                    if (amountPaid < totalPrice) {
                        System.out.println("금액이 부족합니다. 다시 입력해 주세요.");
                    } else if (amountPaid == totalPrice) {
                        System.out.println("계산이 완료되었습니다. 감사합니다!");
                        break;
                    } else {
                        System.out.println("계산이 완료되었습니다. 거스름돈: " + (amountPaid - totalPrice) + "원");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("유효하지 않은 금액입니다. 숫자만 입력해 주세요.");
                }
            }

            scanner.close();
        }
    }

    public static List<Item> initializeProductList() {
        List<Item> productList = new ArrayList<>();
        productList.add(new Item("콜라", 1000, 10));
        productList.add(new Item("사이다", 1000, 8));
        productList.add(new Item("물", 500, 28));
        productList.add(new Item("감자칩", 1500, 7));
        productList.add(new Item("초코바", 1200, 15));
        productList.add(new Item("정식도시락", 6400, 8));
        productList.add(new Item("컵라면", 1700, 15));
        productList.add(new Item("삼각김밥", 2000, 12));
        productList.add(new Item("소주", 5000, 9));
        productList.add(new Item("맥주", 2500, 13));
        return productList;
    }
}
