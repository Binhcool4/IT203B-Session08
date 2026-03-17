package BTTH.factory;

import BTTH.device.Device;
import BTTH.device.Fan;

public class FanFactory implements DeviceFactory {

    @Override
    public String getType() {
        return "fan";
    }

    @Override
    public Device create(String name) {
        return new Fan(name);
    }
}
