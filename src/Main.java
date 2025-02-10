import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int EXIT_CODE = -1;
    private static final int MIN_QUANTITY = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = ProductList.initializeProductList();
        int totalPrice = 0;
        int purchaseFlag = 0;
        int runConvenienceFlag = 0;
        //상품 추가 로직
        while (true) {
            if (purchaseFlag == 1) {
                break;
            }
            System.out.println(Constants.PRODUCT_LIST_HEADER);
            System.out.println(Constants.PRODUCT_LIST_COLUMNS);
            for (int i = 0; i < items.size(); i++) {
                System.out.println(String.format("%-7d %-11s %-9d %d", items.get(i).getOrder(), items.get(i).getName(), items.get(i).getPrice(), items.get(i).getQuantity()));
            }
            System.out.println(Constants.EXIT_OPTION);
            System.out.print(Constants.ENTER_PRODUCT_NUMBER);

            int productIndex = -1;
            try {
                productIndex = Integer.parseInt(scanner.nextLine()) - 1;

                if (productIndex == EXIT_CODE) {
                    System.out.println(Constants.PROGRAM_EXIT_MESSAGE);
                    runConvenienceFlag = 1;
                    break;
                }

                if (productIndex < 0 || productIndex >= items.size()) {
                    throw new IllegalArgumentException(Constants.INVALID_PRODUCT_NUMBER);
                }

                Item selectedItem = items.get(productIndex);

                if(selectedItem.getQuantity() == 0){
                    System.out.println("재고가 부족합니다, 재고를 추가합니다.");
                    RestockThread restockThread = new RestockThread(selectedItem);
                    restockThread.start();
                }


                System.out.print(selectedItem.getName() + Constants.ENTER_PRODUCT_QUANTITY);
                int quantity = Integer.parseInt(scanner.nextLine());


                if (quantity < MIN_QUANTITY) {
                    throw new IllegalArgumentException(Constants.INVALID_QUANTITY);
                }
                if(quantity > selectedItem.getQuantity()){
                        throw new IllegalArgumentException(selectedItem.getName() + "는 " + selectedItem.getQuantity() + "개 만큼 구매하실 수 있습니다.\n");
                }

                totalPrice += selectedItem.getPrice() * quantity;
                selectedItem.decreaseQuantity(quantity);
                System.out.println("\n" + selectedItem.getName() + " " + quantity + "개 추기되었습니다.");
                System.out.println("현재가격: " + totalPrice + "원");
                System.out.print(Constants.PROCEED_PURCHASE);
                
                //재구매 로직
                while (true) {
                    try {
                        String input = scanner.nextLine().toUpperCase();

                        if (input.equals("N")) {
                            break;
                        } else if (input.equals("Y")) {
                            purchaseFlag = 1;
                            break;
                        } else {
                            throw new IllegalArgumentException("\n" + Constants.INVALID_INPUT);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.print(Constants.PROCEED_PURCHASE);
                    }
                }
            }catch (NumberFormatException e) {
                System.out.println(Constants.INVALID_QUANTITY);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        //결제 로직
        if (runConvenienceFlag == 0) {
            while (true) {
                try {
                    System.out.print("\n" + Constants.ENTER_AMOUNT);
                    int amountPaid = Integer.parseInt(scanner.nextLine());

                    if (amountPaid < totalPrice) {
                        System.out.println(Constants.INSUFFICIENT_FUNDS);
                    } else if (amountPaid == totalPrice) {
                        System.out.println("\n" + Constants.PAYMENT_COMPLETED);
                        break;
                    } else {
                        System.out.println("\n계산이 완료되었습니다. 거스름돈: " + (amountPaid - totalPrice) + "원");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n" + Constants.INVALID_AMOUNT);
                }
            }

            scanner.close();
        }
    }
}
