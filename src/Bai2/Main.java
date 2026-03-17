package Bai2;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OldThermometer oldThermometer = new OldThermometer();
        TemperatureSensor adapter = new ThermometerAdapter(oldThermometer);
        SmartHomeFacade facade = new SmartHomeFacade(adapter);
        int choice;
        do {
            System.out.println("\n MENU ");
            System.out.println("1. Xem nhiệt độ hiện tại");
            System.out.println("2. Chế độ rời nhà");
            System.out.println("3. Chế độ ngủ");
            System.out.println("0. Thoát");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    facade.getCurrentTemperature();
                    break;
                case 2:
                    facade.leaveHome();
                    break;
                case 3:
                    facade.sleepMode();
                    break;
            }
        } while (choice != 0);
    }
}