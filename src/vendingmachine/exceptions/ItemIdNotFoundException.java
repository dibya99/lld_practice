package vendingmachine.exceptions;

public class ItemIdNotFoundException extends RuntimeException{
    public ItemIdNotFoundException()
    {
        super("Item Id set by user not found");
    }
}
