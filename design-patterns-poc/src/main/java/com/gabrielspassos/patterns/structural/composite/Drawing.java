package com.gabrielspassos.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Drawing implements Shape {

    private List<Shape> shapes;

    public Drawing() {
        this.shapes = new ArrayList<>();
    }

    @Override
    public boolean draw(String color) {
        return shapes.stream().allMatch(shape -> shape.draw(color));
    }

    public boolean addShape(Shape shape) {
        this.shapes.add(shape);
        return true;
    }

    public boolean removeShape(Shape shape) {
        return this.shapes.remove(shape);
    }

    public boolean clearShapes() {
        this.shapes.clear();
        return true;
    }
}
