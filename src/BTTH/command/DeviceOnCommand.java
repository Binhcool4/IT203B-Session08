package BTTH.command;

import BTTH.device.Device;

public class DeviceOnCommand implements Command {

    private final Device device;

    public DeviceOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }

    @Override
    public void undo() {
        device.turnOff();
    }
}
