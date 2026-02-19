package elevatorsystem.entity;

import elevatorsystem.enums.Direction;
import elevatorsystem.enums.RequestType;
import elevatorsystem.exceptions.NoElevatorFoundException;
import elevatorsystem.observer.ElevatorObserver;
import elevatorsystem.observer.ElevatorObserverImpl;
import elevatorsystem.strategy.ElevatorSelectionStrategy;
import elevatorsystem.strategy.ElevatorSelectionStrategyImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorSystem {
    private static ElevatorSystem elevatorSystem;
    private Map<Integer, Elevator> elevatorMap;
    private ElevatorSelectionStrategy elevatorSelectionStrategy;
    private ExecutorService executorService;

    private ElevatorSystem(int numElevators)
    {
        elevatorMap = new HashMap<>();
        for(int i=1;i<=numElevators;i++)
        {
            Elevator elevator = new Elevator(i);
            elevatorMap.put(i,elevator);
        }
        elevatorSelectionStrategy = new ElevatorSelectionStrategyImpl();
        executorService = Executors.newFixedThreadPool(numElevators);

    }

    public static ElevatorSystem getInstance(int numElevators)
    {
        if(elevatorSystem == null)
            elevatorSystem=new ElevatorSystem(numElevators);
        return elevatorSystem;
    }

    public void startSystem()
    {
        for(Elevator elevator:elevatorMap.values())
        {
            executorService.submit(elevator);
        }
    }

    public void stopSystem()
    {
        for(Elevator elevator:elevatorMap.values())
            elevator.setRunning(false);
        executorService.shutdown();
    }

    public Elevator handleExternalRequest(Direction direction, int targetFloor) throws NoElevatorFoundException
    {
        Request request = new Request(RequestType.EXTERNAL,direction,targetFloor);
        Elevator bestElevator = elevatorSelectionStrategy.getBestElevator(elevatorMap,request);
        bestElevator.addRequest(request);
        return bestElevator;
    }

    public void handleInternalRequest(int elevatorId, int targetFloor)
    {
        Elevator elevator= elevatorMap.get(elevatorId);
        elevator.addRequest(new Request(RequestType.INTERNAL,Direction.IDLE,targetFloor));
    }


}