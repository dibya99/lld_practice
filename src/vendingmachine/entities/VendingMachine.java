package vendingmachine.entities;

import vendingmachine.enums.Coin;
import vendingmachine.states.IdleState;
import vendingmachine.states.VendingMachineState;

public class VendingMachine {
    private static VendingMachine vendingMachine;
    private Inventory inventory;
    private String selectedItemId;
    private VendingMachineState vendingMachineState;
    private int balance;

    private VendingMachine() {
        vendingMachine = null;
        inventory = new Inventory();
        selectedItemId = null;
        balance = 0;
        vendingMachineState = new IdleState(this);
    }

    public static VendingMachine getInstance() {
        if (vendingMachine == null)
            vendingMachine = new VendingMachine();
        return vendingMachine;
    }

    public void reset() {
        this.balance = 0;
        this.selectedItemId = null;
        this.setVendingMachineState(new IdleState(this));
    }

    public void addItem(Item item, int quantity) {
        this.inventory.addItem(item, quantity);
    }

    public void selectItem(String itemId) {
        this.vendingMachineState.selectItem(itemId);
    }

    public void insertCoin(Coin coin) {
        this.vendingMachineState.insertCoin(coin);
    }

    public void refund() {
        this.vendingMachineState.refund();
    }

    public void dispense() {
        this.vendingMachineState.dispense();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(String selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public VendingMachineState getVendingMachineState() {
        return vendingMachineState;
    }

    public void setVendingMachineState(VendingMachineState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
