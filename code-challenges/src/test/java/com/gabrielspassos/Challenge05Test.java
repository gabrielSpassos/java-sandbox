package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Challenge05Test {

    @Test
    void shouldReturnPowerByName() {
        var johnPower = Challenge05.getPowerByName("John");
        assertTrue(johnPower.isPresent());
        assertEquals(100, johnPower.get());

        var paulPower = Challenge05.getPowerByName("Paul");
        assertTrue(paulPower.isPresent());
        assertEquals(90, paulPower.get());

        var georgePower = Challenge05.getPowerByName("George");
        assertTrue(georgePower.isPresent());
        assertEquals(80, georgePower.get());

        var ringoPower = Challenge05.getPowerByName("Ringo");
        assertTrue(ringoPower.isPresent());
        assertEquals(70, ringoPower.get());
    }

    @Test
    void shouldReturnEmptyPower() {
        var power = Challenge05.getPowerByName("Gabriel");
        assertTrue(power.isEmpty());
    }

    @Test
    void shouldReturnJohnAsMostPowerful() {
        assertEquals("John", Challenge05.getMostPowerful("John", "John").get());
        assertEquals("John", Challenge05.getMostPowerful("John", "Paul").get());
        assertEquals("John", Challenge05.getMostPowerful("John", "George").get());
        assertEquals("John", Challenge05.getMostPowerful("John", "Ringo").get());
        assertEquals("John", Challenge05.getMostPowerful("Paul", "John").get());
        assertEquals("John", Challenge05.getMostPowerful("George", "John").get());
        assertEquals("John", Challenge05.getMostPowerful("Ringo", "John").get());
    }

    @Test
    void shouldReturnMostPowerfulForPaul() {
        assertEquals("Paul", Challenge05.getMostPowerful("Paul", "Paul").get());
        assertEquals("John", Challenge05.getMostPowerful("Paul", "John").get());
        assertEquals("Paul", Challenge05.getMostPowerful("Paul", "George").get());
        assertEquals("Paul", Challenge05.getMostPowerful("Paul", "Ringo").get());
        assertEquals("John", Challenge05.getMostPowerful("John", "Paul").get());
        assertEquals("Paul", Challenge05.getMostPowerful("George", "Paul").get());
        assertEquals("Paul", Challenge05.getMostPowerful("Ringo", "Paul").get());
    }

    @Test
    void shouldReturnMostPowerfulForGeorge() {
        assertEquals("George", Challenge05.getMostPowerful("George", "George").get());
        assertEquals("John", Challenge05.getMostPowerful("George", "John").get());
        assertEquals("Paul", Challenge05.getMostPowerful("George", "Paul").get());
        assertEquals("George", Challenge05.getMostPowerful("George", "Ringo").get());
        assertEquals("John", Challenge05.getMostPowerful("John", "George").get());
        assertEquals("Paul", Challenge05.getMostPowerful("Paul", "George").get());
        assertEquals("George", Challenge05.getMostPowerful("Ringo", "George").get());
    }

    @Test
    void shouldReturnMostPowerfulForRingo() {
        assertEquals("Ringo", Challenge05.getMostPowerful("Ringo", "Ringo").get());
        assertEquals("John", Challenge05.getMostPowerful("Ringo", "John").get());
        assertEquals("Paul", Challenge05.getMostPowerful("Ringo", "Paul").get());
        assertEquals("George", Challenge05.getMostPowerful("Ringo", "George").get());
        assertEquals("John", Challenge05.getMostPowerful("John", "Ringo").get());
        assertEquals("Paul", Challenge05.getMostPowerful("Paul", "Ringo").get());
        assertEquals("George", Challenge05.getMostPowerful("George", "Ringo").get());
    }

    @Test
    void shouldReturnEmptyName() {
        var result1 = Challenge05.getMostPowerful("John", "Gabriel");
        assertTrue(result1.isEmpty());

        var result2 = Challenge05.getMostPowerful("Gabriel", "Ringo");
        assertTrue(result2.isEmpty());
    }

    @Test
    void playGame() {
        assertEquals("John", Challenge05.play("John", "Paul"));
        var leaderboard1 = Challenge05.getLeaderboard();
        assertEquals(10, leaderboard1.get("John"));
        assertEquals(-5, leaderboard1.get("Paul"));
        assertEquals(0, leaderboard1.get("George"));
        assertEquals(0, leaderboard1.get("Ringo"));

        assertEquals("John", Challenge05.play("John", "George"));
        var leaderboard2 = Challenge05.getLeaderboard();
        assertEquals(20, leaderboard2.get("John"));
        assertEquals(-5, leaderboard2.get("Paul"));
        assertEquals(-5, leaderboard2.get("George"));
        assertEquals(0, leaderboard2.get("Ringo"));

        assertEquals("Paul", Challenge05.play("Ringo", "Paul"));
        var leaderboard3 = Challenge05.getLeaderboard();
        assertEquals(20, leaderboard3.get("John"));
        assertEquals(5, leaderboard3.get("Paul"));
        assertEquals(-5, leaderboard3.get("George"));
        assertEquals(-5, leaderboard3.get("Ringo"));
    }

    @Test
    void shouldPlayWithDraw() {
        assertEquals("Ringo", Challenge05.play("Ringo", "Ringo"));
        var leaderboard3 = Challenge05.getLeaderboard();
        assertEquals(0, leaderboard3.get("John"));
        assertEquals(0, leaderboard3.get("Paul"));
        assertEquals(0, leaderboard3.get("George"));
        assertEquals(10, leaderboard3.get("Ringo"));
    }

    @Test
    void shouldThrowErrorToPlay() {
        assertThrows(IllegalArgumentException.class, () -> Challenge05.play("Ringo", "Gabriel"));
        assertThrows(IllegalArgumentException.class, () -> Challenge05.play("Gabriel", "Paul"));
    }
}