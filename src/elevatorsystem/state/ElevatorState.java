package elevatorsystem.state;

import elevatorsystem.entity.Elevator;
import elevatorsystem.entity.Request;
import elevatorsystem.enums.Direction;

public interface ElevatorState {
    void addRequest(Elevator elevator, Request request);
    void move(Elevator elevator);
    Direction getDirection();
}
