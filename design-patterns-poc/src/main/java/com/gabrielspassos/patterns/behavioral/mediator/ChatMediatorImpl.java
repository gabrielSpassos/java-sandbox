package com.gabrielspassos.patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator {

    private final List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public boolean sendMessage(String message, User user) {
        return users.stream()
                .filter(u -> !u.equals(user))
                .map(u -> u.receive(message))
                .allMatch(result -> result.equals(true));
    }

    @Override
    public boolean addUser(User user) {
        this.users.add(user);
        return true;
    }

}
