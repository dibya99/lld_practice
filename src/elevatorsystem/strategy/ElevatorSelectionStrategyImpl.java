package elevatorsystem.strategy;

import elevatorsystem.entity.Elevator;
import elevatorsystem.entity.Request;
import elevatorsystem.enums.Direction;
import elevatorsystem.exceptions.NoElevatorFoundException;

import java.util.Map;

public class ElevatorSelectionStrategyImpl implements ElevatorSelectionStrategy{
    public boolean isSuitable(Elevator elevator, Request request)
    {
        if(!elevator.isRunning())
            return false;

        if(elevator.getElevatorState().getDirection() == Direction.IDLE)
            return true;

        if(request.getDirection() == elevator.getElevatorState().getDirection() && request.getDirection() == Direction.UP
            && request.getTargetFloor() >= elevator.getCurrentFloor())
            return true;
        if(request.getDirection() == elevator.getElevatorState().getDirection() && request.getDirection() == Direction.DOWN
            && request.getTargetFloor() <= elevator.getCurrentFloor())
            return true;

        return false;
    }
    public Elevator getBestElevator(Map<Integer,Elevator> elevatorMap, Request request) throws NoElevatorFoundException
    {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator elevator:elevatorMap.values())
        {
            if(isSuitable(elevator,request))
            {
                if(Math.abs(request.getTargetFloor() - elevator.getCurrentFloor()) < minDistance)
                {
                    minDistance=Math.abs(request.getTargetFloor() - elevator.getCurrentFloor());
                    bestElevator=elevator;
                }
            }
        }
        if(bestElevator == null)
            throw new NoElevatorFoundException();
        return bestElevator;
    }
}
