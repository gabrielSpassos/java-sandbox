package com.gabrielspassos.patterns.structural.flyweight;

import java.awt.*;

public class Line implements Shape {

    public Line() {
        IO.println("Creating line object");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics line, int x1, int y1, int x2, int y2, Color color) {
        line.setColor(color);
        line.drawLine(x1, y1, x2, y2);
    }
}
