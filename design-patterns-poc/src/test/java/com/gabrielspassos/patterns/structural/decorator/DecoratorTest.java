package com.gabrielspassos.patterns.structural.decorator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoratorTest {

    @Test
    void shouldCreateSportsCar() {
        Car sportsCar = new SportsCar(new BasicCar());

        assertEquals("Sports Car | Basic Car", sportsCar.assemble());
    }

    @Test
    void shouldCreateLuxuryCar() {
        Car luxuryCar = new LuxuryCar(new BasicCar());

        assertEquals("Luxury Car | Basic Car", luxuryCar.assemble());
    }

    @Test
    void shouldCreateLuxurySportsCar() {
        Car luxuryCar = new LuxuryCar(new SportsCar(new BasicCar()));

        assertEquals("Luxury Car | Sports Car | Basic Car", luxuryCar.assemble());
    }

    @Test
    void shouldCreateSportsLuxuryCar() {
        Car sportsCar = new SportsCar(new LuxuryCar(new BasicCar()));

        assertEquals("Sports Car | Luxury Car | Basic Car", sportsCar.assemble());
    }
}
