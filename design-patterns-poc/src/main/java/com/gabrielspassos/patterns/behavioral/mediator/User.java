package com.gabrielspassos.patterns.behavioral.mediator;

public abstract class User {

    private ChatMediator mediator;
    private String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract boolean send(String message);

    public abstract boolean receive(String message);

    public String getName() {
        return name;
    }

    public ChatMediator getMediator() {
        return mediator;
    }
}
