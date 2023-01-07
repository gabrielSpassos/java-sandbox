package com.gabrielspassos.services;

import com.gabrielspassos.domain.Vehicle;

public sealed interface VehicleService permits VehicleServiceImpl {
    int getMaxSeatedPassengers(Vehicle vehicle);

    int getRandomNumberOfPassengers();

    Boolean isRoofRackAttachable(Vehicle vehicle);

    Boolean isAbleToTransportAllPassengersSeated(Vehicle vehicle, int numberOfPassengers);
}
