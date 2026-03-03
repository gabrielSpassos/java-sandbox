package com.gabrielspassos.patterns.behavioral.observer;

public interface Observer {

    boolean update();

    boolean setSubject(Subject subject);

}
