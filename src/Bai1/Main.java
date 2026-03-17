package Bai1;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Device> deviceList = new ArrayList<>();
        HardwareConnection connection = null;
        int choice;
        do {
            System.out.println("\n MENU ");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị mới");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Tắt thiết bị");
            System.out.println("0. Thoát");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    connection = HardwareConnection.getInstance();
                    connection.connect();
                    break;
                case 2:
                    System.out.println("Chọn loại thiết bị:");
                    System.out.println("1. Đèn");
                    System.out.println("2. Quạt");
                    System.out.println("3. Điều hòa");

                    int type = sc.nextInt();

                    DeviceFactory factory = null;
                    if (type == 1) {
                        factory = new LightFactory();
                    } else if (type == 2) {
                        factory = new FanFactory();
                    } else if (type == 3) {
                        factory = new AirConditionerFactory();
                    }
                    if (factory != null) {
                        Device device = factory.createDevice();
                        deviceList.add(device);
                    }

                    break;
                case 3:

                    for (int i = 0; i < deviceList.size(); i++) {
                        System.out.println((i + 1) + ". Thiết bị " + (i + 1));
                    }
                    System.out.print("Chọn thiết bị: ");
                    int on = sc.nextInt();
                    deviceList.get(on - 1).turnOn();
                    break;
                case 4:
                    for (int i = 0; i < deviceList.size(); i++) {
                        System.out.println((i + 1) + ". Thiết bị " + (i + 1));
                    }
                    System.out.print("Chọn thiết bị: ");
                    int off = sc.nextInt();

                    deviceList.get(off - 1).turnOff();
                    break;
            }
        } while (choice != 0);
    }
}