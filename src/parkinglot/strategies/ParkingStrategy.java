package parkinglot.strategies;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.Vehicle;

import java.util.Map;
import java.util.Optional;

public interface ParkingStrategy {
    Optional <ParkingSpot> getParkingSpot(Map<Integer, ParkingFloor> floorMap, Vehicle vehicle);
}
