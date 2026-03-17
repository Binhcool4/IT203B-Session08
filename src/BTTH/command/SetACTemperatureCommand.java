package BTTH.command;

import BTTH.device.AirConditioner;

public class SetACTemperatureCommand implements Command {

    private final AirConditioner airConditioner;
    private final double newTemperature;
    private double oldTemperature;

    public SetACTemperatureCommand(AirConditioner airConditioner, double newTemperature) {
        this.airConditioner = airConditioner;
        this.newTemperature = newTemperature;
    }

    @Override
    public void execute() {
        oldTemperature = airConditioner.getTargetTemperature();
        airConditioner.setTemperature(newTemperature);
    }

    @Override
    public void undo() {
        airConditioner.setTemperature(oldTemperature);
    }
}
