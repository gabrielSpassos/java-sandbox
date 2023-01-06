package com.gabrielspassos.domain;

import com.gabrielspassos.domain.enumerators.CarType;

public final class Car extends Vehicle {

    private static final int MAX_SEATED_PASSENGERS = 5;

    private CarType carType;

    public Car(String id, CarType carType) {
        super(id, MAX_SEATED_PASSENGERS);
        this.carType = carType;
    }

    @Override
    public String getVehicleInfo() {
        return String.format("This vehicle is a %s, it's id is %s and it's type is %s.",
                this.getClass(), this.getId(), this.getCarType());
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
