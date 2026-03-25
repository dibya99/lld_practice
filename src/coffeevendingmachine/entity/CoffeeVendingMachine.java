package coffeevendingmachine.entity;

import coffeevendingmachine.decorator.CaramelSyrupCoffeeDecorator;
import coffeevendingmachine.decorator.ExtraSugarCoffeeDecorator;
import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.ToppingType;
import coffeevendingmachine.factory.CoffeeFactory;
import coffeevendingmachine.state.ReadyState;
import coffeevendingmachine.state.VendingMachineState;

import java.util.List;

public class CoffeeVendingMachine {
    private static CoffeeVendingMachine coffeeVendingMachine;
    private VendingMachineState vendingMachineState;
    private Coffee selectedCoffee;
    private int money;
    private CoffeeFactory coffeeFactory;
    private Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    private CoffeeVendingMachine()
    {
        money=0;
        selectedCoffee=null;
        vendingMachineState=new ReadyState();
        coffeeFactory=new CoffeeFactory();

    }

    public void reset()
    {
        money=0;
        selectedCoffee=null;
        vendingMachineState=new ReadyState();
    }

    public void insertMoney(int amount)
    {
        this.vendingMachineState.insertMoney(this,amount);
    }

    public void cancel()
    {
        this.vendingMachineState.cancel(this);
    }

    public void dispenseCoffee()
    {
        this.vendingMachineState.dispenseCoffee(this);
    }

    public void selectCoffee(CoffeeType coffeeType, List<ToppingType> toppingTypeList)
    {
        selectedCoffee=coffeeFactory.createCoffee(coffeeType);
        for(ToppingType toppingType:toppingTypeList)
        {
            if(toppingType == ToppingType.CARAMEL_SYRUP)
                selectedCoffee=new CaramelSyrupCoffeeDecorator(selectedCoffee);
            else if(toppingType == ToppingType.EXTRA_SUGAR)
                selectedCoffee=new ExtraSugarCoffeeDecorator(selectedCoffee);
        }
        this.vendingMachineState.selectCoffee(this,selectedCoffee);
    }

    public static CoffeeVendingMachine getInstance()
    {
        if(coffeeVendingMachine==null)
            coffeeVendingMachine = new CoffeeVendingMachine();
        return coffeeVendingMachine;
    }

    public Coffee getSelectedCoffee() {
        return selectedCoffee;
    }

    public void setSelectedCoffee(Coffee selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void printCurrentState()
    {
        System.out.println(vendingMachineState.getClass().getSimpleName());
    }

    public void setVendingMachineState(VendingMachineState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }
}
