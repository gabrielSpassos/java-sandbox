package com.gabrielspassos;

import com.gabrielspassos.challenge15.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Challenge15Test {

    @Test
    void shouldCreatePerson() {
        var john = new Person("John", 30);
        john.addFriend(new Person("Paul", 29));
        john.addFriend(new Person("George", 31));
        john.addFriend(new Person("Ringo", 28));

        assertEquals(List.of("Paul", "George", "Ringo"), john.getFriendsNames());
        assertEquals(30, john.getAge());
        assertEquals("John", john.getName());
    }

}