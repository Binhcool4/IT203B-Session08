package BTTH.adapter;

import java.util.concurrent.ThreadLocalRandom;

public class OldThermometer {

    public int getTemperatureFahrenheit() {
        return ThreadLocalRandom.current().nextInt(72, 90);
    }
}
