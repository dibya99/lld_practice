package elevatorsystem;

import elevatorsystem.entity.Elevator;
import elevatorsystem.entity.ElevatorSystem;
import elevatorsystem.enums.Direction;
import elevatorsystem.exceptions.NoElevatorFoundException;

public class Main {
    public static void main(String [] args)
    {
        try
        {
            ElevatorSystem elevatorSystem = ElevatorSystem.getInstance(2);
            elevatorSystem.startSystem();
            Elevator elevator1 = elevatorSystem.handleExternalRequest(Direction.UP,5);
            Thread.sleep(25000);
            Elevator elevator2 = elevatorSystem.handleExternalRequest(Direction.DOWN, 3);
            Thread.sleep(15000);
            System.out.println(elevator1);
            System.out.println(elevator2);
            elevatorSystem.handleInternalRequest(elevator1.getId(),8);
            elevatorSystem.handleInternalRequest(elevator2.getId(),1);
            Thread.sleep(200000);
            elevatorSystem.stopSystem();
        }
        catch (NoElevatorFoundException exception)
        {
            System.out.println(exception);
            exception.printStackTrace();
        }
        catch (Exception exception)
        {
            System.out.println(exception);
            exception.printStackTrace();
        }
    }
}
