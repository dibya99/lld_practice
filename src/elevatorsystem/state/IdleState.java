package elevatorsystem.state;

import elevatorsystem.entity.Elevator;
import elevatorsystem.entity.Request;
import elevatorsystem.enums.Direction;
import elevatorsystem.enums.RequestType;

public class IdleState implements ElevatorState{

    @Override
    public void addRequest(Elevator elevator, Request request) {
            if(request.getTargetFloor() > elevator.getCurrentFloor())
                elevator.getUpRequests().add(request.getTargetFloor());
            else
                elevator.getDownRequests().add(request.getTargetFloor());
    }

    @Override
    public void move(Elevator elevator) {
        elevator.notifyObservers();
        if(!elevator.getUpRequests().isEmpty())
            elevator.setElevatorState(new UpState());
        if(!elevator.getDownRequests().isEmpty())
            elevator.setElevatorState(new DownState());
    }

    @Override
    public String toString()
    {
        return "IdleState";
    }

    @Override
    public Direction getDirection()
    {
        return Direction.IDLE;
    }
}
