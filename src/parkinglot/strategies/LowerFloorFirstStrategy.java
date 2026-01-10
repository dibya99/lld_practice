package parkinglot.strategies;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.Vehicle;

import java.util.Map;
import java.util.Optional;

public class LowerFloorFirstStrategy implements ParkingStrategy {
    public Optional<ParkingSpot> getParkingSpot(Map<Integer, ParkingFloor> floorMap, Vehicle vehicle) {
        System.out.println("LowerFloorFirst");
        for (ParkingFloor parkingFloor : floorMap.values()) {
            System.out.println("Searching in floor: " + parkingFloor.getFloorId());
            Optional<ParkingSpot> parkingSpotOptional = parkingFloor.getParkingSpot(vehicle);
            if (parkingSpotOptional.isPresent())
                return parkingSpotOptional;
        }
        return Optional.empty();
    }
}
