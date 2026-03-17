package BTTH.subject;

import BTTH.observer.Observer;

public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();
}
