package com.gabrielspassos.challenge16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldStartWith10Mosquitos() {
        Game game = new Game();

        assertEquals(10, game.getAliveMosquitos());
        assertEquals(0, game.getKilledMosquitos());
    }

    @Test
    void tickShouldRunWithoutErrors() {
        Game game = new Game();

        assertDoesNotThrow(game::tick);
    }

    @Test
    void countersShouldNeverBeNegative() {
        Game game = new Game();

        for (int i = 0; i < 20; i++) {
            game.tick();
        }

        assertTrue(game.getAliveMosquitos() >= 0);
        assertTrue(game.getKilledMosquitos() >= 0);
    }

    @Test
    void exterminatorShouldEventuallyKillMosquito() {
        Game game = new Game();

        int initialAlive = game.getAliveMosquitos();

        for (int i = 0; i < 300; i++) {
            game.tick();
        }

        assertTrue(game.getKilledMosquitos() > 0);
        assertTrue(game.getAliveMosquitos() <= initialAlive);
    }

    @Test
    void mosquitosShouldReproduceAfterMoves() {
        Game game = new Game();

        int initial = game.getAliveMosquitos();

        for (int i = 0; i < 10; i++) {
            game.tick();
        }

        // population may grow
        assertTrue(game.getAliveMosquitos() >= initial);
    }

    @Test
    void simulationShouldBeStableOverManyTicks() {
        Game game = new Game();

        for (int i = 0; i < 1000; i++) {
            game.tick();
        }

        assertDoesNotThrow(game::getAliveMosquitos);
        assertDoesNotThrow(game::getKilledMosquitos);
    }

}