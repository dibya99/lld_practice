package parkinglot.entities;

import parkinglot.strategies.ParkingStrategy;
import parkinglot.strategies.FeeStrategy;
import java.time.LocalDateTime;
import java.util.*;

// singleton class to manage the parking lot
public class ParkingLot {
    private static ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;
    private FeeStrategy feeStrategy;
    private Map<String, ParkingTicket> activeTickets;
    private Map<Integer, ParkingFloor> floorMap;

    private ParkingLot() {
        parkingStrategy = null;
        feeStrategy = null;
        activeTickets = new HashMap<>();
        floorMap = new TreeMap<>();
    }

    public static ParkingLot getInstance() {
        if (parkingLot == null)
            parkingLot = new ParkingLot();
        return parkingLot;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> parkingSpotOpt = parkingStrategy.getParkingSpot(floorMap,vehicle);
        if (parkingSpotOpt.isEmpty())
            return Optional.empty();
        ParkingSpot parkingSpot = parkingSpotOpt.get();
        parkingSpot.parkVehicle(vehicle);
        ParkingTicket parkingTicket = new ParkingTicket(UUID.randomUUID().toString(),vehicle,LocalDateTime.now(),null,parkingSpot);
        activeTickets.put(parkingTicket.getTicketId(), parkingTicket);
        return Optional.of(parkingTicket);
    }

    public double unparkVehicle(String ticketId) {
        if (!activeTickets.containsKey(ticketId))
            throw new RuntimeException("Ticket does not exits");
        ParkingTicket parkingTicket = activeTickets.get(ticketId);
        parkingTicket.setEndTime(LocalDateTime.now());
        double fees = feeStrategy.getFee(parkingTicket);
        ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
        parkingSpot.unparkVehicle();
        activeTickets.remove(ticketId);
        return fees;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void addParkingFloor(ParkingFloor parkingFloor) {
        floorMap.put(parkingFloor.getFloorId(), parkingFloor);
    }


}
