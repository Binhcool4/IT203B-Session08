package Bai5;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.attach(fan);
        sensor.attach(ac);
        SleepModeCommand sleepMode = new SleepModeCommand(light, fan, ac);
        int choice;
        do {
            System.out.println("\n MENU ");
            System.out.println("1. Kích hoạt chế độ ngủ");
            System.out.println("2. Thay đổi nhiệt độ");
            System.out.println("0. Thoát");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sleepMode.execute();
                    break;
                case 2:
                    System.out.print("Mời bạn nhập nhiệt độ: ");int t = sc.nextInt();
                    sensor.setTemperature(t);
                    break;
            }
        } while (choice != 0);

    }

}