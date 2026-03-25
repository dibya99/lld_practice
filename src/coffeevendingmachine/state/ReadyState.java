package coffeevendingmachine.state;

import coffeevendingmachine.entity.Coffee;
import coffeevendingmachine.entity.CoffeeVendingMachine;

public class ReadyState implements VendingMachineState{
    @Override
    public void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount) {
        System.out.println("Please select coffee");
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Please select coffee");
    }

    @Override
    public void cancel(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Please select coffee");
    }

    @Override
    public void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffee) {
        coffeeVendingMachine.setSelectedCoffee(coffee);
        coffeeVendingMachine.setVendingMachineState(new CoffeeSelectedState());
    }
}
