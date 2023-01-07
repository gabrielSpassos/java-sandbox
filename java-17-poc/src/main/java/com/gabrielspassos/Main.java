package com.gabrielspassos;

import com.gabrielspassos.domain.Boat;
import com.gabrielspassos.domain.Car;
import com.gabrielspassos.domain.Motorbike;
import com.gabrielspassos.domain.Truck;
import com.gabrielspassos.domain.enumerators.CarType;
import com.gabrielspassos.services.HexService;
import com.gabrielspassos.services.VehicleService;
import com.gabrielspassos.services.VehicleServiceImpl;

import java.util.stream.Stream;

public class Main {

    private static final int TRUCK_AXLES = 2;

    public static void main(String[] args) {
        System.out.println("Hello Java 17 PoC!");

        Car hatchCar = new Car("34234", CarType.HATCH);
        Car sedanCar = new Car("64532", CarType.SEDAN);
        Car suvCar = new Car("87845", CarType.SUV);
        Motorbike motorbike = new Motorbike("GGDF533");
        Truck truck = new Truck("323HHGSG", TRUCK_AXLES);
        Boat boat = new Boat("FISH");

        VehicleService vehicleService = new VehicleServiceImpl();

        Stream.of(hatchCar, sedanCar, suvCar, motorbike, truck, boat)
                .forEach(vehicle -> {
                    int numberOfPassengers = vehicleService.getRandomNumberOfPassengers();
                    Boolean isAbleToTransportAllPassengersSeated = vehicleService
                            .isAbleToTransportAllPassengersSeated(vehicle, numberOfPassengers);

                    System.out.println(vehicle.getVehicleInfo());
                    System.out.println("Max setead passagers: " + vehicleService.getMaxSeatedPassengers(vehicle));
                    System.out.println("Is able to transport " + numberOfPassengers + "? " + isAbleToTransportAllPassengersSeated);
                    System.out.println("This vehicle can handle with roof rackers? " + vehicleService.isRoofRackAttachable(vehicle));
                    System.out.println("-------------------------");
                });

        HexService hexService = new HexService();
        hexService.test();
    }


}