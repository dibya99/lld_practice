package coffeevendingmachine.entity;

import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.Ingredient;

import java.util.Map;

public class Espresso extends Coffee{

    public Espresso()
    {
        super(CoffeeType.ESPRESSO,50);
    }
    @Override
    public void addCondiments() {
        System.out.println("Adding only Espresso shot and water");
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.WATER,2,Ingredient.COFFEE_BEANS,3);
    }
}
