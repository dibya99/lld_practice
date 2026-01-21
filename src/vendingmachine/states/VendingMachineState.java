package vendingmachine.states;

import vendingmachine.enums.Coin;

public interface VendingMachineState {
    void selectItem(String itemId);
    void insertCoin(Coin coin);
    void refund();
    void dispense();

}
