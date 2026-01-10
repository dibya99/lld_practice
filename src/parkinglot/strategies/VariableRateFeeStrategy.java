package parkinglot.strategies;

import parkinglot.entities.ParkingTicket;
import parkinglot.enums.VehicleSize;

import java.time.Duration;
import java.util.Map;

public class VariableRateFeeStrategy implements FeeStrategy {
    private Map<VehicleSize, Double> rateMap;

    public VariableRateFeeStrategy(Map<VehicleSize, Double> rateMap) {
        this.rateMap = rateMap;
    }

    public double getFee(ParkingTicket parkingTicket) {
        Duration duration = Duration.between(parkingTicket.getStartTime(), parkingTicket.getEndTime());
        return Math.max(1, duration.toHours()) * rateMap.get(parkingTicket.getVehicle().getVehicleSize());
    }
}
