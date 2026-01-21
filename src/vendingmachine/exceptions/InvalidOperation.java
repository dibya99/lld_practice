package vendingmachine.exceptions;

public class InvalidOperation extends RuntimeException {
    public InvalidOperation() {
        super("Invalid Vending Machine operation");
    }
}
