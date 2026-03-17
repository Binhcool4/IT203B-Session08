package BTTH.subject;

import BTTH.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private double temperatureCelsius = 27.0;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperatureCelsius);
        }
    }

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
        System.out.printf("[Observer] Nhiệt độ phòng thay đổi: %.1f C%n", temperatureCelsius);
        notifyObservers();
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }
}
