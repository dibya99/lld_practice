package elevatorsystem.observer;

import elevatorsystem.entity.Elevator;

public class ElevatorObserverImpl implements ElevatorObserver {

    @Override
    public void display(Elevator elevator) {
        System.out.println("Elevator: " + elevator.getId() + " currently at floor: " + elevator.getCurrentFloor() +
                " State: " + elevator.getElevatorState());


    }
}
