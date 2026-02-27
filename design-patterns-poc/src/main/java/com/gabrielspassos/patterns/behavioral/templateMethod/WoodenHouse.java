package com.gabrielspassos.patterns.behavioral.templateMethod;

public class WoodenHouse extends HouseTemplate {

    @Override
    void buildWalls() {
        IO.println("Building Wooden Walls");
    }

    @Override
    void buildPillars() {
        IO.println("Building Pillars with Wood coating");
    }

}
