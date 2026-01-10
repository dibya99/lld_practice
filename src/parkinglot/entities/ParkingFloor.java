package parkinglot.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private int floorId;
    private List<ParkingSpot> spotList;

    public ParkingFloor(int floorId) {
        this.floorId = floorId;
        spotList = new ArrayList<>();
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        spotList.add(parkingSpot);
    }

    public Optional<ParkingSpot> getParkingSpot(Vehicle vehicle) {
        for (ParkingSpot parkingSpot : spotList) {
            if (!parkingSpot.isOccupied() && parkingSpot.getVehicleSize() == vehicle.getVehicleSize())
                return Optional.of(parkingSpot);
        }
        return Optional.empty();
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public List<ParkingSpot> getSpotList() {
        return spotList;
    }

    public void setSpotList(List<ParkingSpot> spotList) {
        this.spotList = spotList;
    }
}
