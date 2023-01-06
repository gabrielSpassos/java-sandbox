package com.gabrielspassos.domain;

public final class Truck extends Vehicle {

    private static final int MAX_SEATED_PASSENGERS = 5;

    private int axles;

    public Truck(String id, int axles) {
        super(id, MAX_SEATED_PASSENGERS);
        this.axles = axles;
    }

    @Override
    public String getVehicleInfo() {
        return String.format("This vehicle is a %s, it's id is %s and it has %s. axles",
                this.getClass(), this.getId(), this.getAxles());
    }

    public int getAxles() {
        return axles;
    }

    public void setAxles(int axles) {
        this.axles = axles;
    }
}
