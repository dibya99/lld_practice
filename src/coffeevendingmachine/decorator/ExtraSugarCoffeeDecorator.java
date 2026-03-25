package coffeevendingmachine.decorator;

import coffeevendingmachine.entity.Coffee;
import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class ExtraSugarCoffeeDecorator extends CoffeeDecorator{

    private int SUGAR_PRICE ;
    private Map<Ingredient,Integer> extraIngredients;

    public ExtraSugarCoffeeDecorator(Coffee coffee)
    {
        super(coffee);
        SUGAR_PRICE=20;
        extraIngredients=Map.of(Ingredient.EXTRA_SUGAR,1);
    }

    @Override
    public void addCondiments()
    {
        super.addCondiments();
        System.out.println("Also adding extra sugar");
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
        return super.getPrice() + SUGAR_PRICE;
    }




}
