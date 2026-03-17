package Bai3;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();
        RemoteControl remote = new RemoteControl();
        int choice;
        do {
            System.out.println("\n MENU ");
            System.out.println("1. Gán nút bật đèn");
            System.out.println("2. Gán nút tắt đèn");
            System.out.println("3. Gán nút bật quạt");
            System.out.println("4. Gán nút tắt quạt");
            System.out.println("5. Gán nút set điều hòa");
            System.out.println("6. Nhấn nút");
            System.out.println("7. Undo");
            System.out.println("0. Thoát");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    remote.setCommand(1, new LightOnCommand(light));
                    break;
                case 2:
                    remote.setCommand(2, new LightOffCommand(light));
                    break;
                case 3:
                    remote.setCommand(3, new FanOnCommand(fan));
                    break;
                case 4:
                    remote.setCommand(4, new FanOffCommand(fan));
                    break;
                case 5:
                    System.out.print("Nhập nhiệt độ: ");int temp = sc.nextInt();
                    remote.setCommand(0, new ACSetTemperatureCommand(ac, temp));

                    break;
                case 6:
                    System.out.print("Nhấn nút số: ");
                    int btn = sc.nextInt();
                    remote.pressButton(btn);
                    break;
                case 7:
                    remote.undo();
                    break;
            }
        } while (choice != 0);
    }
}