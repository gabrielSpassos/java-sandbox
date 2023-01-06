package com.gabrielspassos.domain;

public final class Motorbike extends Vehicle {

    private static final int MAX_SEATED_PASSENGERS = 2;

    public Motorbike(String id) {
        super(id, MAX_SEATED_PASSENGERS);
    }

    @Override
    public String getVehicleInfo() {
        return String.format("This vehicle is a %s, it's id is %s",
                this.getClass(), this.getId());
    }
}
