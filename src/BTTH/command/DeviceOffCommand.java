package BTTH.command;

import BTTH.device.Device;

public class DeviceOffCommand implements Command {

    private final Device device;

    public DeviceOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }

    @Override
    public void undo() {
        device.turnOn();
    }
}
