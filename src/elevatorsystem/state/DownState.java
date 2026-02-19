package elevatorsystem.state;

import elevatorsystem.entity.Elevator;
import elevatorsystem.entity.Request;
import elevatorsystem.enums.Direction;
import elevatorsystem.enums.RequestType;

import java.util.TreeSet;

public class DownState implements ElevatorState {
    @Override
    public void addRequest(Elevator elevator, Request request) {
        if (request.getRequestType() == RequestType.EXTERNAL) {
            if (request.getDirection() == Direction.DOWN && request.getTargetFloor() < elevator.getCurrentFloor())
                elevator.getDownRequests().add(request.getTargetFloor());
            else
                elevator.getUpRequests().add(request.getTargetFloor());
        } else {
            if (request.getTargetFloor() < elevator.getCurrentFloor())
                elevator.getDownRequests().add(request.getTargetFloor());
            else
                elevator.getUpRequests().add(request.getTargetFloor());
        }
    }

    @Override
    public void move(Elevator elevator) {
        elevator.notifyObservers();
        TreeSet<Integer> downRequests = (TreeSet<Integer>) elevator.getDownRequests();
        if (downRequests.isEmpty()) {
            elevator.setElevatorState(new IdleState());
            return;
        }
        int nextFloor = downRequests.first();
        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
        if (nextFloor == elevator.getCurrentFloor())
            downRequests.pollFirst();
    }

    @Override
    public String toString() {
        return "DownState";
    }

    @Override
    public Direction getDirection()
    {
        return Direction.DOWN;
    }
}
