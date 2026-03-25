package coffeevendingmachine.state;

import coffeevendingmachine.entity.Coffee;
import coffeevendingmachine.entity.CoffeeVendingMachine;

public interface VendingMachineState {
     void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount);
     void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine);
     void cancel(CoffeeVendingMachine coffeeVendingMachine);
     void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffee);
}
