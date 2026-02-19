package com.gabrielspassos.patterns.creational.prototype;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeTest {

    @Test
    void shouldCloneUser() {
        var user = new UserDTO();

        assertTrue(user.loadData());
        assertNotNull(user.getRoles());
        assertEquals(1, user.getRoles().size());
        assertEquals("subscriber", user.getRoles().getFirst());

        var clone = user.clone();
        assertTrue(clone.addRole("admin"));
        assertTrue(clone.removeRole("subscriber"));
        assertFalse(clone.removeRole("foo"));
        assertEquals(1, clone.getRoles().size());
        assertEquals("admin", clone.getRoles().getFirst());

        assertEquals(1, user.getRoles().size());
        assertEquals("subscriber", user.getRoles().getFirst());

        assertNotEquals(user, clone);

    }

}