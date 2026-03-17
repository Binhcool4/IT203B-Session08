package BTTH.factory;

import BTTH.device.AirConditioner;
import BTTH.device.Device;

public class AirConditionerFactory implements DeviceFactory {

    @Override
    public String getType() {
        return "airconditioner";
    }

    @Override
    public Device create(String name) {
        return new AirConditioner(name);
    }
}
