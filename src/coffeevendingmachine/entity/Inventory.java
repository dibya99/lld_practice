package coffeevendingmachine.entity;

import coffeevendingmachine.enums.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private static Inventory inventory;
    private Map<Ingredient, Integer> ingredientMap;

    private Inventory() {
        ingredientMap = new HashMap<>(Map.of(Ingredient.CARAMEL_SYRUP, 0,
                Ingredient.COFFEE_BEANS, 0,
                Ingredient.MILK, 0,
                Ingredient.WATER, 0,
                Ingredient.EXTRA_SUGAR, 0));
    }

    public static Inventory getInstance() {
        if (inventory == null)
            inventory = new Inventory();
        return inventory;
    }

    public void printInventory() {
        System.out.println("Current Inventory");
        System.out.println(ingredientMap);
    }

    public void addStock(Ingredient ingredient, int count)
    {
        ingredientMap.put(ingredient,count);
    }

    public void refillIngredients(Map<Ingredient, Integer> ingredientMap)
    {
        this.ingredientMap=new HashMap<>(ingredientMap);
    }

    public boolean hasIngredients(Map<Ingredient, Integer> recipe)
    {
        for(Map.Entry<Ingredient,Integer> entry:recipe.entrySet()) {
            if (ingredientMap.get(entry.getKey()) < entry.getValue())
                return false;
        }
        return true;
    }

    public void deductIngredients(Map<Ingredient, Integer> recipe)
    {
        for(Map.Entry<Ingredient, Integer> entry:recipe.entrySet())
        {
            ingredientMap.put(entry.getKey(), ingredientMap.get(entry.getKey()) - entry.getValue());
        }
    }


}
