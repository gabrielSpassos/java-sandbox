package com.gabrielspassos.patterns.behavioral.mediator;

public class UserImpl extends User {

    public UserImpl(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public boolean send(String message) {
        IO.println(this.getName() + ": Sending Message = " + message);
        return this.getMediator().sendMessage(message, this);
    }

    @Override
    public boolean receive(String message) {
        IO.println(this.getName() + ": Received Message = " + message);
        return true;
    }
}
