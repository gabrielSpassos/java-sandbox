package com.gabrielspassos.services;

import com.gabrielspassos.domain.*;
import com.gabrielspassos.domain.enumerators.CarType;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGeneratorFactory;

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

    @Override
    public int getRandomNumberOfPassengers() {
        return RandomGeneratorFactory
                .getDefault()
                .create()
                .nextInt(1, 7);
    }

    @Override
    public Boolean isAbleToTransportAllPassengersSeated(Vehicle vehicle, int numberOfPassengers) {
        int maxSeatedPassengers = getMaxSeatedPassengers(vehicle);
        return maxSeatedPassengers >= numberOfPassengers;
    }
}
