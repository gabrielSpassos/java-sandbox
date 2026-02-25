package com.gabrielspassos.patterns.structural.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BridgePatternTest {

    @Test
    void shouldHaveRedTriangle() {
        var redTriangle = new Triangle(new RedColor());

        var color = redTriangle.applyColor();

        assertNotNull(color);
    }

    @Test
    void shouldHaveGreenPentagon() {
        var greenPentagon = new Pentagon(new GreenColor());

        var color = greenPentagon.applyColor();

        assertNotNull(color);
    }
}
