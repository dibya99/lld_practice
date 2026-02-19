package elevatorsystem.strategy;

import elevatorsystem.entity.Elevator;
import elevatorsystem.entity.Request;
import elevatorsystem.exceptions.NoElevatorFoundException;

import java.util.Map;

public interface ElevatorSelectionStrategy {
    Elevator getBestElevator(Map<Integer, Elevator> elevatorMap, Request request) throws NoElevatorFoundException;
}
