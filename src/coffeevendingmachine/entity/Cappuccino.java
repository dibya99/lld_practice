package coffeevendingmachine.entity;

import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.Ingredient;

import java.util.Map;

public class Cappuccino extends Coffee{

    public Cappuccino()
    {
        super(CoffeeType.CAPPUCCINO,80);
    }
    @Override
    public void addCondiments() {
        System.out.println("Adding milk, water, coffee");
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.WATER,2, Ingredient.COFFEE_BEANS,3, Ingredient.MILK,3);
    }
}
