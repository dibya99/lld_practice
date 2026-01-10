package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public abstract class Vehicle {
    private String vehicleId;
    private VehicleSize vehicleSize;

    public Vehicle(String vehicleId, VehicleSize vehicleSize) {
        this.vehicleId = vehicleId;
        this.vehicleSize = vehicleSize;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }
}
