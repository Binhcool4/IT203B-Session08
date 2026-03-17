package BTTH.device;

import BTTH.observer.Observer;
import BTTH.singleton.HardwareConnection;

public class Fan extends AbstractDevice implements Observer {

    private String speed = "LOW";

    public Fan(String name) {
        super(name);
    }

    public void setSpeed(String speed) {
        this.speed = speed.toUpperCase();
        if (!on) {
            on = true;
        }
        HardwareConnection.getInstance().sendCommand(name, "Đặt tốc độ " + this.speed);
    }

    public String getSpeed() {
        return speed;
    }

    @Override
    public void update(double temperatureCelsius) {
        if (temperatureCelsius >= 30) {
            setSpeed("HIGH");
            System.out.printf("%s: Nhiệt độ %.1f C, chạy tốc độ cao.%n", name, temperatureCelsius);
        } else if (temperatureCelsius >= 26) {
            setSpeed("MEDIUM");
            System.out.printf("%s: Nhiệt độ %.1f C, chạy tốc độ trung bình.%n", name, temperatureCelsius);
        } else {
            setSpeed("LOW");
            System.out.printf("%s: Nhiệt độ %.1f C, chạy tốc độ thấp.%n", name, temperatureCelsius);
        }
    }

    @Override
    public String getStatus() {
        return name + " - " + (on ? "Đang bật" : "Đang tắt") + ", tốc độ " + speed;
    }
}
