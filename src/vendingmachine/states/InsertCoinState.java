package vendingmachine.states;

import vendingmachine.entities.Item;
import vendingmachine.entities.VendingMachine;
import vendingmachine.enums.Coin;
import vendingmachine.exceptions.InvalidOperation;
import vendingmachine.exceptions.NotEnoughBalanceException;

public class InsertCoinState implements VendingMachineState {

    private VendingMachine vendingMachine;

    public InsertCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemId) {
        throw new InvalidOperation();
    }

    @Override
    public void insertCoin(Coin coin) {
        this.vendingMachine.setBalance(this.vendingMachine.getBalance() + coin.getValue());
        System.out.println("Current balance: " + this.vendingMachine.getBalance());
    }

    @Override
    public void refund() {
        System.out.println("Refunding: " + this.vendingMachine.getBalance());
        this.vendingMachine.reset();
        this.vendingMachine.setVendingMachineState(new IdleState(this.vendingMachine));
    }

    @Override
    public void dispense() {
        Item selectedItem = vendingMachine.getInventory().getItem(vendingMachine.getSelectedItemId());
        if (vendingMachine.getBalance() < selectedItem.getPrice())
            throw new NotEnoughBalanceException();
        System.out.println("Dispensing: " + selectedItem.getName());
        vendingMachine.getInventory().reduceStock(selectedItem.getItemId());
        vendingMachine.setBalance(vendingMachine.getBalance() - selectedItem.getPrice());
        System.out.println("Refunding: " + this.vendingMachine.getBalance());
        vendingMachine.reset();
        vendingMachine.setVendingMachineState(new IdleState(vendingMachine));
    }
}
