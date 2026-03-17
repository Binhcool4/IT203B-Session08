package Bai2;
public class SmartHomeFacade {
    private TemperatureSensor sensor;
    public SmartHomeFacade(TemperatureSensor sensor) {
        this.sensor = sensor;
    }
    public void leaveHome() {
        System.out.println("FACADE: Tắt đèn");
        System.out.println("FACADE: Tắt quạt");
        System.out.println("FACADE: Tắt điều hòa");

    }
    public void sleepMode() {
        System.out.println("FACADE: Tắt đèn");
        System.out.println("FACADE: Điều hòa set 28°C");
        System.out.println("FACADE: Quạt chạy tốc độ thấp");

    }
    public void getCurrentTemperature() {

        double temp = sensor.getTemperatureCelsius();

        System.out.println("Nhiệt độ hiện tại: " + temp + "°C");
    }
}