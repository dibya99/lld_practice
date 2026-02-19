package elevatorsystem.exceptions;

public class NoElevatorFoundException extends RuntimeException {
    public NoElevatorFoundException() {
        super("Could not find a suitable elevator");
    }
}
