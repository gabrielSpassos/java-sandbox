package com.gabrielspassos.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class MyTopic implements Subject {

    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    public MyTopic() {
        this.observers = new ArrayList<>();
    }

    @Override
    public boolean register(Observer observer) {
        if (null == observer) {
            throw new IllegalArgumentException("invalid observer");
        }

        synchronized (MUTEX) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }

        return true;
    }

    @Override
    public boolean unregister(Observer observer) {
        synchronized (MUTEX) {
            observers.remove(observer);
        }

        return true;
    }

    @Override
    public boolean notifyObservers() {
        List<Observer> observersLocal = new ArrayList<>();
        synchronized (MUTEX) {
            if (!changed) {
                return false;
            }
            observersLocal.addAll(this.observers);
            this.changed = false;
        }

        return observersLocal.stream().allMatch(Observer::update);
    }

    @Override
    public Object getUpdate(Observer observer) {
        return this.message;
    }

    public boolean postMessage(String message) {
        IO.println("Message posted to topic: " + message);
        this.message = message;
        this.changed = true;
        return this.notifyObservers();
    }
}
