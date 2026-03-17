package BTTH.factory;

import BTTH.device.Device;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class DeviceFactoryRegistry {

    private final Map<String, DeviceFactory> factories = new LinkedHashMap<>();

    public void register(DeviceFactory factory) {
        factories.put(factory.getType().toLowerCase(Locale.ROOT), factory);
    }

    public Device createDevice(String type, String name) {
        DeviceFactory factory = factories.get(type.toLowerCase(Locale.ROOT));
        if (factory == null) {
            return null;
        }
        return factory.create(name);
    }

    public Collection<String> supportedTypes() {
        return factories.keySet();
    }
}
