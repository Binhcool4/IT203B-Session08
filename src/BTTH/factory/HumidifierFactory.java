package BTTH.factory;

import BTTH.device.Device;
import BTTH.device.Humidifier;

public class HumidifierFactory implements DeviceFactory {

    @Override
    public String getType() {
        return "humidifier";
    }

    @Override
    public Device create(String name) {
        return new Humidifier(name);
    }
}
