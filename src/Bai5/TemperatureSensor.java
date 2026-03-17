package Bai5;

import java.util.ArrayList;

public class TemperatureSensor implements Subject {

    private ArrayList<Observer> observers = new ArrayList<>();

    private int temperature;

    public void attach(Observer o) {

        observers.add(o);

    }

    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {

            observers.get(i).update(temperature);

        }

    }

    public void setTemperature(int t) {

        temperature = t;

        System.out.println("Cảm biến: Nhiệt độ = " + temperature);

        notifyObservers();

    }

}