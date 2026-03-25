package coffeevendingmachine.decorator;

import coffeevendingmachine.entity.Coffee;
import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.Ingredient;

import java.util.Map;

public class CoffeeDecorator extends Coffee {

    private Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee)
    {
        decoratedCoffee = coffee;
    }

    @Override
    public void addCondiments() {
        decoratedCoffee.addCondiments();
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return decoratedCoffee.getRecipe();
    }

    @Override
    public CoffeeType getCoffeeType()
    {
        return decoratedCoffee.getCoffeeType();
    }

    @Override
    public int getPrice()
    {
        return decoratedCoffee.getPrice();
    }
}
