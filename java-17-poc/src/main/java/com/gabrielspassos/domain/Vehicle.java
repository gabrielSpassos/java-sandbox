package com.gabrielspassos.domain;

public sealed abstract class Vehicle permits Car, Motorbike, Truck, Boat {

    private String id;
    private int maxSeatedPassengers;

    public Vehicle(String id, int maxSeatedPassengers) {
        this.id = id;
        this.maxSeatedPassengers = maxSeatedPassengers;
    }

    public abstract String getVehicleInfo();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxSeatedPassengers() {
        return maxSeatedPassengers;
    }

    public void setMaxSeatedPassengers(int maxSeatedPassengers) {
        this.maxSeatedPassengers = maxSeatedPassengers;
    }
}
