package com.gabrielspassos.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

import static com.gabrielspassos.patterns.structural.flyweight.ShapeType.OVAL_FILL;
import static com.gabrielspassos.patterns.structural.flyweight.ShapeType.OVAL_NO_FILL;
import static com.gabrielspassos.patterns.structural.flyweight.ShapeType.LINE;

public class ShapeFactory {

    private static final Map<ShapeType, Shape> shapes = new HashMap<>();

    public static Shape getShape(ShapeType shapeType) {
        Shape shape = shapes.get(shapeType);

        if (null != shape) {
            return shape;
        }

        if (OVAL_FILL.equals(shapeType)) {
            shape = new Oval(true);
        } else if (OVAL_NO_FILL.equals(shapeType)) {
            shape = new Oval(false);
        } else if (LINE.equals(shapeType)) {
            shape = new Line();
        }

        shapes.put(shapeType, shape);
        return shape;
    }
}
