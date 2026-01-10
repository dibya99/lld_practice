package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public class Car extends Vehicle{
    public Car(String vehicleId)
    {
        super(vehicleId, VehicleSize.MEDIUM);
    }
}
