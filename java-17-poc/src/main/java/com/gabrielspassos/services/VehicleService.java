package com.gabrielspassos.services;

import com.gabrielspassos.domain.Vehicle;

public sealed interface VehicleService permits VehicleServiceImpl {
    int getMaxSeatedPassengers(Vehicle vehicle);

    Boolean isRoofRackAttachable(Vehicle vehicle);
}
