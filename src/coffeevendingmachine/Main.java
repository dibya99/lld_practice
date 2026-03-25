package coffeevendingmachine;

import coffeevendingmachine.entity.CoffeeVendingMachine;
import coffeevendingmachine.entity.Inventory;
import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.Ingredient;
import coffeevendingmachine.enums.ToppingType;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        CoffeeVendingMachine machine = CoffeeVendingMachine.getInstance();
        Inventory inventory = Inventory.getInstance();
        machine.setInventory(inventory);

        System.out.println("========== INITIAL INVENTORY ==========");
        inventory.refillIngredients(Map.of(
                Ingredient.WATER, 50,
                Ingredient.MILK, 50,
                Ingredient.COFFEE_BEANS, 50,
                Ingredient.CARAMEL_SYRUP, 20,
                Ingredient.EXTRA_SUGAR, 20
        ));
        inventory.printInventory();

        System.out.println("\n========== TEST 1 : Espresso normal flow ==========");
        machine.printCurrentState();
        machine.selectCoffee(CoffeeType.ESPRESSO, List.of());
        machine.printCurrentState();
        machine.insertMoney(50);
        machine.printCurrentState();
        machine.dispenseCoffee();
        machine.reset();

        inventory.printInventory();

        System.out.println("\n========== TEST 2 : Latte with extra sugar ==========");
        machine.selectCoffee(CoffeeType.LATTE, List.of(ToppingType.EXTRA_SUGAR));
        machine.insertMoney(120);
        machine.dispenseCoffee();
        machine.reset();

        inventory.printInventory();

        System.out.println("\n========== TEST 3 : Cappuccino with caramel syrup ==========");
        machine.selectCoffee(CoffeeType.CAPPUCCINO, List.of(ToppingType.CARAMEL_SYRUP));
        machine.insertMoney(150);
        machine.dispenseCoffee();
        machine.reset();

        inventory.printInventory();

        System.out.println("\n========== TEST 4 : Multiple toppings ==========");
        machine.selectCoffee(
                CoffeeType.LATTE,
                List.of(ToppingType.EXTRA_SUGAR, ToppingType.CARAMEL_SYRUP)
        );
        machine.insertMoney(200);
        machine.dispenseCoffee();
        machine.reset();

        inventory.printInventory();


        System.out.println("\n========== TEST 5 : Insufficient money ==========");
        machine.selectCoffee(CoffeeType.LATTE, List.of());
        machine.insertMoney(20);
        machine.dispenseCoffee();   // should not dispense
        machine.insertMoney(80);
        machine.dispenseCoffee();
        machine.reset();

        inventory.printInventory();


        System.out.println("\n========== TEST 6 : Cancel order ==========");
        machine.selectCoffee(CoffeeType.CAPPUCCINO, List.of());
        machine.insertMoney(40);
        machine.cancel();
        machine.printCurrentState();

        inventory.printInventory();

        System.out.println("\n========== TEST 7 : Dispense without selecting coffee ==========");
        machine.dispenseCoffee();

        System.out.println("\n========== TEST 8 : Insert money before selecting coffee ==========");
        machine.insertMoney(50);

        System.out.println("\n========== TEST 9 : Multiple coffees sequentially ==========");

        for(int i=0;i<3;i++) {
            machine.selectCoffee(CoffeeType.ESPRESSO, List.of());
            machine.insertMoney(50);
            machine.dispenseCoffee();
            machine.reset();
        }




        System.out.println("\n========== TEST 10 : Inventory after many orders ==========");
        inventory.printInventory();

        System.out.println("\n========== TEST 11 : Stress test multiple topping combinations ==========");

        machine.selectCoffee(
                CoffeeType.CAPPUCCINO,
                List.of(ToppingType.EXTRA_SUGAR, ToppingType.CARAMEL_SYRUP)
        );
        machine.insertMoney(200);
        machine.dispenseCoffee();
        machine.reset();

        machine.selectCoffee(
                CoffeeType.ESPRESSO,
                List.of(ToppingType.EXTRA_SUGAR)
        );
        machine.insertMoney(100);
        machine.dispenseCoffee();
        machine.reset();

        System.out.println("\n========== TEST 12 : Order many coffees ==========");
        for(int i=0;i<5;i++) {
            machine.selectCoffee(CoffeeType.LATTE, List.of());
            machine.insertMoney(100);
            machine.dispenseCoffee();
            machine.reset();
        }

        System.out.println("\n========== FINAL INVENTORY ==========");
        inventory.printInventory();

        System.out.println("\n========== ALL TESTS COMPLETED ==========");
    }
}

