package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public class Bike extends Vehicle {
    public Bike(String vehicleId) {
        super(vehicleId, VehicleSize.SMALL);
    }
}
