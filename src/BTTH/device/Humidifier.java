package BTTH.device;

import BTTH.observer.Observer;
import BTTH.singleton.HardwareConnection;

public class Humidifier extends AbstractDevice implements Observer {

    private int humidityLevel = 50;

    public Humidifier(String name) {
        super(name);
    }

    @Override
    public void update(double temperatureCelsius) {
        if (temperatureCelsius >= 30) {
            humidityLevel = 65;
        } else if (temperatureCelsius >= 26) {
            humidityLevel = 55;
        } else {
            humidityLevel = 45;
        }

        if (!on) {
            on = true;
        }

        HardwareConnection.getInstance().sendCommand(name, "Đặt độ ẩm " + humidityLevel + "%");
        System.out.printf("%s: Điều chỉnh độ ẩm theo nhiệt độ %.1f C -> %d%%%n", name, temperatureCelsius, humidityLevel);
    }

    @Override
    public String getStatus() {
        return name + " - " + (on ? "Đang bật" : "Đang tắt") + ", độ ẩm mức " + humidityLevel + "%";
    }
}
