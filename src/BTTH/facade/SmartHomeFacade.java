package BTTH.facade;

import BTTH.command.DeviceOffCommand;
import BTTH.command.SetACTemperatureCommand;
import BTTH.command.SetFanSpeedCommand;
import BTTH.device.AirConditioner;
import BTTH.device.Device;
import BTTH.device.Fan;
import BTTH.device.Light;
import BTTH.remote.RemoteControl;
import BTTH.subject.TemperatureSensor;

import java.util.List;

public class SmartHomeFacade {

    private final Light light;
    private final AirConditioner airConditioner;
    private final Fan fan;
    private final List<Device> allDevices;
    private final RemoteControl remoteControl;
    private final TemperatureSensor roomTemperatureSensor;

    public SmartHomeFacade(
            Light light,
            AirConditioner airConditioner,
            Fan fan,
            List<Device> allDevices,
            RemoteControl remoteControl,
            TemperatureSensor roomTemperatureSensor
    ) {
        this.light = light;
        this.airConditioner = airConditioner;
        this.fan = fan;
        this.allDevices = allDevices;
        this.remoteControl = remoteControl;
        this.roomTemperatureSensor = roomTemperatureSensor;
    }

    public void sleepMode() {
        System.out.println("[Facade] Kích hoạt chế độ ngủ...");
        remoteControl.press(new DeviceOffCommand(light));
        remoteControl.press(new SetACTemperatureCommand(airConditioner, 28));
        roomTemperatureSensor.setTemperatureCelsius(28);
        remoteControl.press(new SetFanSpeedCommand(fan, "LOW"));
    }

    public void leaveHomeMode() {
        System.out.println("[Facade] Kích hoạt chế độ rời nhà...");
        for (Device device : allDevices) {
            remoteControl.press(new DeviceOffCommand(device));
        }
    }
}
