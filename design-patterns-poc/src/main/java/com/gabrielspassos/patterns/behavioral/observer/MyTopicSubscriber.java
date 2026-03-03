package com.gabrielspassos.patterns.behavioral.observer;

public class MyTopicSubscriber implements Observer {

    private String name;
    private Subject topic;

    public MyTopicSubscriber(String name) {
        this.name = name;
    }

    @Override
    public boolean update() {
        String message = (String) topic.getUpdate(this);
        if (null == message) {
            IO.println("Observer: " + name + " no new messages");
            return false;
        }

        IO.println("Observer: " + name + " new message: " + message);
        return true;
    }

    @Override
    public boolean setSubject(Subject subject) {
        this.topic = subject;
        return true;
    }

}
