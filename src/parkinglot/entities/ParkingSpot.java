package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public class ParkingSpot {
    private String spotId;
    private Vehicle vehicle;
    private boolean occupied;
    private VehicleSize vehicleSize;

    public ParkingSpot(String spotId, VehicleSize vehicleSize)
    {
        this.spotId=spotId;
        this.vehicleSize=vehicleSize;
        this.vehicle=null;
        this.occupied=false;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public boolean isOccupied()
    {
        return occupied;
    }

    public void parkVehicle(Vehicle vehicle)
    {
        this.occupied=true;
        this.vehicle=vehicle;
    }

    public void unparkVehicle()
    {
        this.occupied=false;
        this.vehicle=null;
    }

}
