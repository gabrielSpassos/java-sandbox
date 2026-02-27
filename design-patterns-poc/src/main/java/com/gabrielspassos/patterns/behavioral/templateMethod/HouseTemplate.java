package com.gabrielspassos.patterns.behavioral.templateMethod;

public abstract class HouseTemplate {

    public final boolean buildHouse() {
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        IO.println("House is built.");
        return true;
    }

    abstract void buildWalls();
    abstract void buildPillars();

    private void buildWindows() {
        IO.println("Building Glass Windows");
    }

    private void buildFoundation() {
        IO.println("Building foundation with cement, iron rods and sand");
    }
}
