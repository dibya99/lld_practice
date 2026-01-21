package vendingmachine.states;

import vendingmachine.entities.VendingMachine;
import vendingmachine.enums.Coin;
import vendingmachine.exceptions.InvalidOperation;

public class ItemSelectedState implements VendingMachineState {

    private VendingMachine vendingMachine;

    public ItemSelectedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemId) {
        throw new InvalidOperation();
    }

    @Override
    public void insertCoin(Coin coin) {
        this.vendingMachine.setBalance(this.vendingMachine.getBalance() + coin.getValue());
        System.out.println("Current Balance: " + this.vendingMachine.getBalance());
        this.vendingMachine.setVendingMachineState(new InsertCoinState(this.vendingMachine));
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
