package elevatorsystem.state;

import elevatorsystem.entity.Elevator;
import elevatorsystem.entity.Request;
import elevatorsystem.enums.Direction;
import elevatorsystem.enums.RequestType;

import java.util.TreeSet;

public class UpState implements ElevatorState {
    @Override
    public void addRequest(Elevator elevator, Request request) {
        if (request.getRequestType() == RequestType.EXTERNAL) {
            if (request.getDirection() == Direction.UP && request.getTargetFloor() > elevator.getCurrentFloor())
                elevator.getUpRequests().add(request.getTargetFloor());
            else
                elevator.getDownRequests().add(request.getTargetFloor());
        } else {
            if (request.getTargetFloor() > elevator.getCurrentFloor())
                elevator.getUpRequests().add(request.getTargetFloor());
            else
                elevator.getDownRequests().add(request.getTargetFloor());
        }
    }

    @Override
    public void move(Elevator elevator) {
        elevator.notifyObservers();
        if (elevator.getUpRequests().isEmpty()) {
            elevator.setElevatorState(new IdleState());
            return;
        }
        TreeSet<Integer> upRequests = (TreeSet<Integer>) elevator.getUpRequests();
        int nextFloor = upRequests.first();

        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
        if (elevator.getCurrentFloor() == nextFloor)
            upRequests.pollFirst();

    }

    @Override
    public String toString() {
        return "UpState";
    }

    @Override
    public Direction getDirection()
    {
        return Direction.UP;
    }

}
