package com.gabrielspassos.patterns.behavioral.mediator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MediatorTest {

    @Test
    void shouldTestChat() {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Gabriel");
        User user2 = new UserImpl(mediator, "Fernanda");
        User user3 = new UserImpl(mediator, "John");
        User user4 = new UserImpl(mediator, "Mary");

        assertTrue(mediator.addUser(user1));
        assertTrue(mediator.addUser(user2));
        assertTrue(mediator.addUser(user3));
        assertTrue(mediator.addUser(user4));

        assertTrue(user1.send("Hello from Gabriel"));
    }
}
