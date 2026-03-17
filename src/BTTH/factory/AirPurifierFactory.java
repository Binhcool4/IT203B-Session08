package BTTH.factory;

import BTTH.device.AirPurifier;
import BTTH.device.Device;

public class AirPurifierFactory implements DeviceFactory {

    @Override
    public String getType() {
        return "airpurifier";
    }

    @Override
    public Device create(String name) {
        return new AirPurifier(name);
    }
}
