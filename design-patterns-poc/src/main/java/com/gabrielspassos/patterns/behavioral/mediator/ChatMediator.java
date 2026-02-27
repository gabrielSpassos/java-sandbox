package com.gabrielspassos.patterns.behavioral.mediator;

public interface ChatMediator {

    boolean sendMessage(String message, User user);

    boolean addUser(User user);

}
