package com.gabrielspassos.patterns.structural.flyweight;

import java.awt.*;

public class Oval implements Shape {

    //intrinsic property
    private boolean fill;

    public Oval(boolean fill) {
        this.fill = fill;
        IO.println("Creating oval object with fill="+fill);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics circle, int x, int y, int width, int height, Color color) {
        circle.setColor(color);
        circle.drawOval(x, y, width, height);
        if (fill) {
            circle.fillOval(x, y, width, height);
        }
    }
}
