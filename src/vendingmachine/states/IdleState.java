package vendingmachine.states;

import vendingmachine.entities.VendingMachine;
import vendingmachine.enums.Coin;
import vendingmachine.exceptions.InvalidOperation;
import vendingmachine.exceptions.ItemIdNotFoundException;
import vendingmachine.exceptions.QuantityNotPresentException;

public class IdleState implements VendingMachineState{
    private VendingMachine vendingMachine;
    public IdleState(VendingMachine vendingMachine)
    {
        this.vendingMachine=vendingMachine;
    }
    @Override
    public void selectItem(String itemId)
    {
        if(!this.vendingMachine.getInventory().isItemAvailable(itemId))
            throw new ItemIdNotFoundException();
        if(!this.vendingMachine.getInventory().isQuantityAvailable(itemId))
            throw new QuantityNotPresentException();
        this.vendingMachine.setSelectedItemId(itemId);
        this.vendingMachine.setVendingMachineState(new ItemSelectedState(vendingMachine));
    }

    @Override
    public void insertCoin(Coin coin) {
        throw new InvalidOperation();
    }

    @Override
    public void refund() {
        throw new InvalidOperation();
    }

    @Override
    public void dispense() {
        throw new InvalidOperation();
    }

}
