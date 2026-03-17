package BTTH.device;

import BTTH.singleton.HardwareConnection;

public abstract class AbstractDevice implements Device {

    protected final String name;
    protected boolean on;

    protected AbstractDevice(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turnOn() {
        on = true;
        HardwareConnection.getInstance().sendCommand(name, "Bật");
    }

    @Override
    public void turnOff() {
        on = false;
        HardwareConnection.getInstance().sendCommand(name, "Tắt");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
