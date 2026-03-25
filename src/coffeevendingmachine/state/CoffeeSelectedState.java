package coffeevendingmachine.state;

import coffeevendingmachine.entity.Coffee;
import coffeevendingmachine.entity.CoffeeVendingMachine;

public class CoffeeSelectedState implements VendingMachineState{
    @Override
    public void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount) {
        coffeeVendingMachine.setMoney(coffeeVendingMachine.getMoney()+amount);
        if(coffeeVendingMachine.getMoney() >= coffeeVendingMachine.getSelectedCoffee().getPrice())
            coffeeVendingMachine.setVendingMachineState(new PaidState());
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Either insert required amount or cancel");
    }

    @Override
    public void cancel(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Transaction cancelled");
        System.out.println("Refunding: " + coffeeVendingMachine.getMoney());
        coffeeVendingMachine.reset();
    }

    @Override
    public void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffee) {
        System.out.println("Either insert required amount or cancel");
    }
}
