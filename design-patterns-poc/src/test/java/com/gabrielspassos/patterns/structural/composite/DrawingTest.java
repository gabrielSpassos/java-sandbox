package com.gabrielspassos.patterns.structural.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawingTest {

    @Test
    void shouldDrawCircle() {
        var circle = new Circle();
        var drawing = new Drawing();

        assertTrue(drawing.addShape(circle));
        assertTrue(drawing.draw("red"));
    }

    @Test
    void shouldDrawTriangle() {
        var triangle = new Triangle();
        var drawing = new Drawing();

        assertTrue(drawing.addShape(triangle));
        assertTrue(drawing.draw("blue"));
    }

    @Test
    void shouldDrawManyShapes() {
        var circle = new Circle();
        var triangle = new Triangle();
        var drawing = new Drawing();

        assertTrue(drawing.addShape(circle));
        assertTrue(drawing.addShape(triangle));
        assertTrue(drawing.draw("yellow"));
    }

    @Test
    void shouldRemoveShapes() {
        var circle = new Circle();
        var triangle = new Triangle();
        var drawing = new Drawing();

        assertTrue(drawing.addShape(circle));
        assertTrue(drawing.addShape(triangle));

        assertTrue(drawing.removeShape(circle));
        assertTrue(drawing.removeShape(triangle));
    }

    @Test
    void shouldCleanShapes() {
        var circle = new Circle();
        var triangle = new Triangle();
        var drawing = new Drawing();

        assertTrue(drawing.addShape(circle));
        assertTrue(drawing.addShape(triangle));

        assertTrue(drawing.clearShapes());
    }


}