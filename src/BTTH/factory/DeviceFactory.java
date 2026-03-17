package BTTH.factory;

import BTTH.device.Device;

public interface DeviceFactory {
    String getType();

    Device create(String name);
}
