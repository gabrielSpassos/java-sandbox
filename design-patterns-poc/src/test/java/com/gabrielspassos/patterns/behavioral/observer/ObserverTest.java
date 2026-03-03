package com.gabrielspassos.patterns.behavioral.observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObserverTest {

    @Test
    void shouldObserverTopicWithoutMessages() {
        MyTopic topic = new MyTopic();
        Observer obs1 = new MyTopicSubscriber("obs1");

        topic.register(obs1);
        obs1.setSubject(topic);

        assertFalse(obs1.update());
    }

    @Test
    void shouldObserverTopic() {
        MyTopic topic = new MyTopic();

        Observer obs1 = new MyTopicSubscriber("obs1");
        Observer obs2 = new MyTopicSubscriber("obs2");
        Observer obs3 = new MyTopicSubscriber("obs3");

        topic.register(obs1);
        topic.register(obs2);
        topic.register(obs3);

        obs1.setSubject(topic);
        obs2.setSubject(topic);
        obs3.setSubject(topic);

        assertTrue(topic.postMessage("Test Message"));
    }

}