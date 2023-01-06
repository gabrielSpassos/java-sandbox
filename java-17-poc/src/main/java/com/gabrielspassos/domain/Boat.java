package com.gabrielspassos.domain;

public non-sealed class Boat extends Vehicle {

    private static final int MAX_SEATED_PASSENGERS = 6;

    public Boat(String id) {
        super(id, MAX_SEATED_PASSENGERS);
    }

    @Override
    public String getVehicleInfo() {
        return String.format("This vehicle is a %s, it's id is %s",
                this.getClass(), this.getId());
    }
}
