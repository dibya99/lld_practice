package parkinglot.strategies;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class UpperFloorFirstStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> getParkingSpot(Map<Integer, ParkingFloor> floorMap, Vehicle vehicle) {
        System.out.println("Upper floor first ");
        TreeMap<Integer, ParkingFloor> treeMap = (TreeMap<Integer, ParkingFloor>) floorMap;
        for (ParkingFloor parkingFloor : treeMap.descendingMap().values()) {
            System.out.println("Searching in floor: " + parkingFloor.getFloorId() );
            Optional<ParkingSpot> parkingSpotOptional = parkingFloor.getParkingSpot(vehicle);
            if (parkingSpotOptional.isPresent())
                return parkingSpotOptional;
        }
        return Optional.empty();
    }
}
