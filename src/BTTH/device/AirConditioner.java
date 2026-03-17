package BTTH.device;

import BTTH.singleton.HardwareConnection;

public class AirConditioner extends AbstractDevice {

    private double targetTemperature = 27.0;

    public AirConditioner(String name) {
        super(name);
    }

    public void setTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
        if (!on) {
            on = true;
        }
        HardwareConnection.getInstance().sendCommand(name, String.format("Đặt nhiệt độ %.1f C", targetTemperature));
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    @Override
    public String getStatus() {
        return name + " - " + (on ? "Đang bật" : "Đang tắt") + String.format(", nhiệt độ %.1f C", targetTemperature);
    }
}
