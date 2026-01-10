package parkinglot.strategies;

import parkinglot.entities.ParkingTicket;

import java.time.Duration;
import java.util.Map;

public class FixedHourlyFeeStrategy implements FeeStrategy {
    private double HOURLY_RATE;

    public FixedHourlyFeeStrategy(double rate) {
        HOURLY_RATE = rate;
    }

    @Override
    public double getFee(ParkingTicket parkingTicket) {
        Duration duration = Duration.between(parkingTicket.getEndTime() ,parkingTicket.getStartTime());
        double hrs = Math.max(1,duration.toHours());
        return HOURLY_RATE*hrs;

    }
}
