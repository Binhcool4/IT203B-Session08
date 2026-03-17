package BTTH;

import BTTH.adapter.OldThermometer;
import BTTH.adapter.ThermometerAdapter;
import BTTH.command.DeviceOffCommand;
import BTTH.command.DeviceOnCommand;
import BTTH.command.SetACTemperatureCommand;
import BTTH.device.AirConditioner;
import BTTH.device.Device;
import BTTH.device.Fan;
import BTTH.device.Humidifier;
import BTTH.device.Light;
import BTTH.facade.SmartHomeFacade;
import BTTH.factory.AirConditionerFactory;
import BTTH.factory.AirPurifierFactory;
import BTTH.factory.DeviceFactoryRegistry;
import BTTH.factory.FanFactory;
import BTTH.factory.HumidifierFactory;
import BTTH.factory.LightFactory;
import BTTH.remote.RemoteControl;
import BTTH.singleton.HardwareConnection;
import BTTH.subject.TemperatureSensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        HardwareConnection.getInstance();

        DeviceFactoryRegistry factoryRegistry = new DeviceFactoryRegistry();
        registerFactories(factoryRegistry);

        Light light = (Light) factoryRegistry.createDevice("light", "Đèn phòng khách");
        Fan fan = (Fan) factoryRegistry.createDevice("fan", "Quạt trần");
        AirConditioner airConditioner = (AirConditioner) factoryRegistry.createDevice("airconditioner",
                "Điều hòa phòng khách");
        Humidifier humidifier = (Humidifier) factoryRegistry.createDevice("humidifier", "Máy tạo ẩm");

        List<Device> devices = new ArrayList<>();
        devices.add(light);
        devices.add(fan);
        devices.add(airConditioner);
        devices.add(humidifier);

        RemoteControl remoteControl = new RemoteControl();

        TemperatureSensor roomTemperatureSensor = new TemperatureSensor();
        roomTemperatureSensor.attach(fan);
        roomTemperatureSensor.attach(humidifier);

        ThermometerAdapter thermometerAdapter = new ThermometerAdapter(new OldThermometer());

        SmartHomeFacade facade = new SmartHomeFacade(
                light,
                airConditioner,
                fan,
                devices,
                remoteControl,
                roomTemperatureSensor);

        while (true) {
            printMenu();
            int choice = readInt("Chọn: ");

            switch (choice) {
                case 1:
                    handleToggleDevice(devices, remoteControl);
                    break;
                case 2:
                    handleSetAcTemperature(airConditioner, roomTemperatureSensor, remoteControl);
                    break;
                case 3:
                    handleReadCurrentTemperature(thermometerAdapter, roomTemperatureSensor);
                    break;
                case 4:
                    facade.sleepMode();
                    break;
                case 5:
                    facade.leaveHomeMode();
                    break;
                case 6:
                    handleAddNewDevice(factoryRegistry, devices, roomTemperatureSensor);
                    break;
                case 7:
                    remoteControl.undo();
                    break;
                case 8:
                    remoteControl.redo();
                    break;
                case 9:
                    showAllDeviceStatus(devices);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void registerFactories(DeviceFactoryRegistry factoryRegistry) {
        factoryRegistry.register(new LightFactory());
        factoryRegistry.register(new FanFactory());
        factoryRegistry.register(new AirConditionerFactory());
        factoryRegistry.register(new HumidifierFactory());

        factoryRegistry.register(new AirPurifierFactory());
    }

    private static void printMenu() {
        System.out.println("\n=== SMART HOME CONTROL ===");
        System.out.println("1. Bật/tắt thiết bị");
        System.out.println("2. Điều chỉnh nhiệt độ điều hòa");
        System.out.println("3. Xem nhiệt độ hiện tại");
        System.out.println("4. Kích hoạt chế độ ngủ");
        System.out.println("5. Kích hoạt chế độ rời nhà");
        System.out.println("6. Thêm thiết bị mới");
        System.out.println("7. Undo lệnh gần nhất");
        System.out.println("8. Redo lệnh vừa undo");
        System.out.println("9. Xem trạng thái tất cả thiết bị");
        System.out.println("0. Thoát");
    }

    private static void handleToggleDevice(List<Device> devices, RemoteControl remoteControl) {
        if (devices.isEmpty()) {
            System.out.println("Chưa có thiết bị nào.");
            return;
        }

        printDeviceList(devices);
        int index = readInt("Chọn thiết bị: ") - 1;

        if (index < 0 || index >= devices.size()) {
            System.out.println("Chỉ số không hợp lệ.");
            return;
        }

        Device selected = devices.get(index);
        System.out.print("Nhập hành động (on/off hoặc bật/tắt): ");
        String action = SCANNER.nextLine().trim().toLowerCase(Locale.ROOT);

        switch (action) {
            case "on":
            case "bật":
                remoteControl.press(new DeviceOnCommand(selected));
                break;
            case "off":
            case "tắt":
                remoteControl.press(new DeviceOffCommand(selected));
                break;
            default:
                System.out.println("Hành động không hợp lệ.");
        }
    }

    private static void handleSetAcTemperature(
            AirConditioner airConditioner,
            TemperatureSensor roomTemperatureSensor,
            RemoteControl remoteControl) {
        double newTemp = readDouble("Nhập nhiệt độ mới (C): ");
        remoteControl.press(new SetACTemperatureCommand(airConditioner, newTemp));

        // Nhiệt độ phòng thay đổi sẽ thông báo observer (quạt, máy tạo ẩm).
        roomTemperatureSensor.setTemperatureCelsius(newTemp);
    }

    private static void handleReadCurrentTemperature(
            ThermometerAdapter thermometerAdapter,
            TemperatureSensor roomTemperatureSensor) {
        double sensorTemp = thermometerAdapter.getTemperatureCelsius();
        System.out.printf("[Adapter] Nhiệt độ từ cảm biến cũ: %.1f C%n", sensorTemp);
        roomTemperatureSensor.setTemperatureCelsius(sensorTemp);
    }

    private static void handleAddNewDevice(
            DeviceFactoryRegistry factoryRegistry,
            List<Device> devices,
            TemperatureSensor roomTemperatureSensor) {
        System.out.println("Loại thiết bị hỗ trợ: " + String.join(", ", factoryRegistry.supportedTypes()));
        System.out.print("Nhập loại cần thêm: ");
        String type = SCANNER.nextLine().trim().toLowerCase(Locale.ROOT);

        System.out.print("Nhập tên thiết bị: ");
        String name = SCANNER.nextLine().trim();

        Device newDevice = factoryRegistry.createDevice(type, name);

        if (newDevice == null) {
            System.out.println("Không tìm thấy factory cho loại này.");
            return;
        }

        devices.add(newDevice);

        if (newDevice instanceof BTTH.observer.Observer) {
            roomTemperatureSensor.attach((BTTH.observer.Observer) newDevice);
        }

        System.out.println("Đã thêm thiết bị mới: " + newDevice.getName());
    }

    private static void printDeviceList(List<Device> devices) {
        System.out.println("Danh sách thiết bị:");
        for (int i = 0; i < devices.size(); i++) {
            System.out.println((i + 1) + ". " + devices.get(i).getStatus());
        }
    }

    private static void showAllDeviceStatus(List<Device> devices) {
        if (devices.isEmpty()) {
            System.out.println("Chưa có thiết bị nào.");
            return;
        }

        System.out.println("\n--- Trạng thái hệ thống ---");
        for (Device device : devices) {
            System.out.println("- " + device.getStatus());
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ.");
            }
        }
    }
}
