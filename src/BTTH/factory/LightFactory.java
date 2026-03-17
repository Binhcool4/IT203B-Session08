package BTTH.factory;

import BTTH.device.Device;
import BTTH.device.Light;

public class LightFactory implements DeviceFactory {

    @Override
    public String getType() {
        return "light";
    }

    @Override
    public Device create(String name) {
        return new Light(name);
    }
}
