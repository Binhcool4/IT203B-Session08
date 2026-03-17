package BTTH.adapter;

public class ThermometerAdapter implements TemperatureSensor {

    private final OldThermometer oldThermometer;

    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }

    @Override
    public double getTemperatureCelsius() {
        int fahrenheit = oldThermometer.getTemperatureFahrenheit();
        return (fahrenheit - 32) * 5.0 / 9.0;
    }
}
