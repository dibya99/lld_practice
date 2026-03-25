package coffeevendingmachine.decorator;

import coffeevendingmachine.entity.Coffee;
import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class CaramelSyrupCoffeeDecorator extends CoffeeDecorator{

    private int CARAMEL_PRICE;
    private Map<Ingredient,Integer> extraIngredients;

    public CaramelSyrupCoffeeDecorator(Coffee coffee)
    {
        super(coffee);
        CARAMEL_PRICE = 40;
        extraIngredients = Map.of(Ingredient.CARAMEL_SYRUP, 1);
    }

    @Override
    public void addCondiments()
    {
        super.addCondiments();
        System.out.println("Also adding extra caramel syrup");
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        Map<Ingredient,Integer> parentMap = new HashMap<>(super.getRecipe());
        extraIngredients.forEach((k,v) -> parentMap.merge(k,v,Integer::sum));
        return parentMap;
    }

    @Override
    public CoffeeType getCoffeeType()
    {
        return super.getCoffeeType();
    }

    @Override
    public int getPrice()
    {
        return super.getPrice() + CARAMEL_PRICE;
    }
}
