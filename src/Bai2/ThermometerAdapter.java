package Bai2;

public class ThermometerAdapter implements TemperatureSensor {
    private OldThermometer oldThermometer;
    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }
    public double getTemperatureCelsius() {
        int f = oldThermometer.getTemperatureFahrenheit();
        double c = (f - 32) * 5.0 / 9.0;
        return c;
    }
}