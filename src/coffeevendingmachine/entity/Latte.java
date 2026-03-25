package coffeevendingmachine.entity;

import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.Ingredient;

import java.util.Map;

public class Latte extends Coffee{

    public Latte()
    {
        super(CoffeeType.LATTE,100);
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding water, coffee beans, and more milk");
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.WATER,3, Ingredient.COFFEE_BEANS,3, Ingredient.MILK, 6);
    }
}
