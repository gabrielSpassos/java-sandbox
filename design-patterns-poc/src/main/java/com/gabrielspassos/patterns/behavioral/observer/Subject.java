package com.gabrielspassos.patterns.behavioral.observer;

public interface Subject {

    boolean register(Observer observer);
    boolean unregister(Observer observer);

    boolean notifyObservers();

    Object getUpdate(Observer observer);

}
