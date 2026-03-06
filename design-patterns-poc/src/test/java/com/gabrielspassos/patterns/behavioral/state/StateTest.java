package com.gabrielspassos.patterns.behavioral.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StateTest {

    @Test
    void shouldStartState() {
        TVContext tvContext = new TVContext();
        State tvStartState = new TvStartState();

        tvContext.setState(tvStartState);

        assertTrue(tvContext.doAction());
    }

    @Test
    void shouldStopState() {
        TVContext tvContext = new TVContext();
        State tvStopState = new TVStopState();

        tvContext.setState(tvStopState);

        assertFalse(tvContext.doAction());
    }
}