package coffeevendingmachine.state;

import coffeevendingmachine.entity.Coffee;
import coffeevendingmachine.entity.CoffeeVendingMachine;

public class PaidState implements VendingMachineState{
    @Override
    public void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount) {
        System.out.println("Either cancel or dispense coffee");
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine) {
        if(coffeeVendingMachine.getInventory().hasIngredients(coffeeVendingMachine.getSelectedCoffee().getRecipe()))
        {
            coffeeVendingMachine.getInventory().deductIngredients(coffeeVendingMachine.getSelectedCoffee().getRecipe());
            System.out.println("Dispensing Coffee");
            coffeeVendingMachine.getSelectedCoffee().prepare();
            System.out.println("Refunding: " + (coffeeVendingMachine.getMoney() - coffeeVendingMachine.getSelectedCoffee().getPrice()));
            coffeeVendingMachine.reset();
        }
        else
        {
            System.out.println("Required Ingredients not present");
            System.out.println("Refunding: " + coffeeVendingMachine.getMoney());
            coffeeVendingMachine.reset();
        }
    }

    @Override
    public void cancel(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Transaction cancelled");
        System.out.println("Refunding: " + coffeeVendingMachine.getMoney());
        coffeeVendingMachine.reset();
    }

    @Override
    public void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffee) {
        System.out.println("Either cancel or dispense coffee");
    }
}
