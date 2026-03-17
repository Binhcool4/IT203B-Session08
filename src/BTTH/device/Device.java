package BTTH.device;

public interface Device {
    String getName();

    void turnOn();

    void turnOff();

    boolean isOn();

    String getStatus();
}
