package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public class Truck extends Vehicle{
    public Truck(String vehicleId)
    {
        super(vehicleId, VehicleSize.LARGE);
    }
}
