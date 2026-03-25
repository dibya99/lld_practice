package coffeevendingmachine.entity;

import coffeevendingmachine.enums.CoffeeType;
import coffeevendingmachine.enums.Ingredient;

import java.util.Map;

public abstract class Coffee {
    private CoffeeType coffeeType;
    private int price;

    public Coffee()
    {

    }

    public Coffee(CoffeeType coffeeType, int price) {
        this.coffeeType = coffeeType;
        this.price = price;
    }

    public void groundBeans()
    {
        System.out.println("Grinding coffee beans");
    }

    public void pourIntoCup()
    {
        System.out.println("Pouring into cup");
    }

    public abstract void addCondiments();

    public void brew()
    {
        System.out.println("Brewing Coffee");
    }

    public abstract Map<Ingredient, Integer> getRecipe();

    public void prepare()
    {
        groundBeans();
        addCondiments();
        pourIntoCup();
        brew();
    }


    public CoffeeType getCoffeeType() {
        return coffeeType;
    }


    public int getPrice() {
        return price;
    }

}
