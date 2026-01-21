package vendingmachine.exceptions;

public class QuantityNotPresentException extends RuntimeException {
    public QuantityNotPresentException()
    {
        super("Quantity of this product is zero in inventory");
    }
}
