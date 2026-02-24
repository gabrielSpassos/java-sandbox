package com.gabrielspassos.patterns.structural.flyweight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlyweightTest {

    @Test
    void shouldCreateOnlyOneInstanceOfLine() {
        var shape1 = ShapeFactory.getShape(ShapeType.LINE);
        var shape2 = ShapeFactory.getShape(ShapeType.LINE);

        assertEquals(shape1, shape2);
    }

    @Test
    void shouldCreateOnlyOneInstanceOfOvalNoFill() {
        var shape1 = ShapeFactory.getShape(ShapeType.OVAL_NO_FILL);
        var shape2 = ShapeFactory.getShape(ShapeType.OVAL_NO_FILL);

        assertEquals(shape1, shape2);
    }

    @Test
    void shouldCreateOnlyOneInstanceOfOvalFill() {
        var shape1 = ShapeFactory.getShape(ShapeType.OVAL_FILL);
        var shape2 = ShapeFactory.getShape(ShapeType.OVAL_FILL);

        assertEquals(shape1, shape2);
    }
}
