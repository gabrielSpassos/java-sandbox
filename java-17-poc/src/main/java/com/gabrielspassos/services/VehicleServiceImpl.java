package com.gabrielspassos.services;

import com.gabrielspassos.domain.Boat;
import com.gabrielspassos.domain.Car;
import com.gabrielspassos.domain.Motorbike;
import com.gabrielspassos.domain.Truck;
import com.gabrielspassos.domain.Vehicle;
import com.gabrielspassos.domain.enumerators.CarType;

import java.util.Arrays;
import java.util.List;

public final class VehicleServiceImpl implements VehicleService {

    private static final List<CarType> CAR_WITH_ROOF_RACK_ATTACHABLE = Arrays.asList(CarType.SEDAN, CarType.SUV);

    @Override
    public int getMaxSeatedPassengers(Vehicle vehicle) {
        return switch (vehicle) {
            case Car c -> c.getMaxSeatedPassengers();
            case Truck t -> t.getMaxSeatedPassengers();
            case Motorbike m -> m.getMaxSeatedPassengers();
            case Boat b -> b.getMaxSeatedPassengers();
        };
    }

    @Override
    public Boolean isRoofRackAttachable(Vehicle vehicle) {
        return switch (vehicle) {
            case Truck t -> Boolean.TRUE;
            case Car c && CAR_WITH_ROOF_RACK_ATTACHABLE.contains(c.getCarType()) -> Boolean.TRUE;
            default -> Boolean.FALSE;
        };
    }
}
