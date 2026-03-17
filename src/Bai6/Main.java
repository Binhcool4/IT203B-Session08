package Bai6;

import java.util.Scanner;
import Bai6.factory.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Chọn kênh bán hàng:");
        System.out.println("1. Website");
        System.out.println("2. Mobile App");
        System.out.println("3. POS");

        int choice = sc.nextInt();

        SalesChannelFactory factory = null;

        if (choice == 1) {

            factory = new WebsiteFactory();
            System.out.println("Bạn đã chọn kênh Website");

        } else if (choice == 2) {

            factory = new MobileAppFactory();
            System.out.println("Bạn đã chọn kênh Mobile App");

        } else if (choice == 3) {

            factory = new POSFactory();
            System.out.println("Bạn đã chọn kênh POS");

        }

        System.out.print("Nhập giá sản phẩm: ");
        double price = sc.nextDouble();

        System.out.print("Nhập số lượng: ");
        int quantity = sc.nextInt();

        OrderService service = new OrderService(factory);

        service.processOrder(price, quantity);

    }

}