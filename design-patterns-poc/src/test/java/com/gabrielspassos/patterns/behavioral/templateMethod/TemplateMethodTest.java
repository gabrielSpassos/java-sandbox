package com.gabrielspassos.patterns.behavioral.templateMethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TemplateMethodTest {

    @Test
    void shouldCreateWoodenHouse() {
        var house = new WoodenHouse();

        var result = house.buildHouse();

        assertTrue(result);
    }

    @Test
    void shouldCreateGlassHouse() {
        var house = new GlassHouse();

        var result = house.buildHouse();

        assertTrue(result);
    }

}