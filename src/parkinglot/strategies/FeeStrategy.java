package parkinglot.strategies;

import parkinglot.entities.ParkingTicket;

public interface FeeStrategy {
    double getFee(ParkingTicket parkingTicket);
}
