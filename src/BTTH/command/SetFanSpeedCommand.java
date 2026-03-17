package BTTH.command;

import BTTH.device.Fan;

public class SetFanSpeedCommand implements Command {

    private final Fan fan;
    private final String newSpeed;
    private String oldSpeed;

    public SetFanSpeedCommand(Fan fan, String newSpeed) {
        this.fan = fan;
        this.newSpeed = newSpeed;
    }

    @Override
    public void execute() {
        oldSpeed = fan.getSpeed();
        fan.setSpeed(newSpeed);
    }

    @Override
    public void undo() {
        fan.setSpeed(oldSpeed);
    }
}
