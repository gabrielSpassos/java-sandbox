package com.gabrielspassos.patterns.behavioral.templateMethod;

public class GlassHouse extends HouseTemplate {

    @Override
    void buildWalls() {
        IO.println("Building Glass Walls");
    }

    @Override
    void buildPillars() {
        IO.println("Building Pillars with glass coating");
    }

}
