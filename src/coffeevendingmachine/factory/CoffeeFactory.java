package coffeevendingmachine.factory;

import coffeevendingmachine.entity.Cappuccino;
import coffeevendingmachine.entity.Coffee;
import coffeevendingmachine.entity.Espresso;
import coffeevendingmachine.entity.Latte;
import coffeevendingmachine.enums.CoffeeType;

public class CoffeeFactory {
    public Coffee createCoffee(CoffeeType coffeeType)
    {
        if(coffeeType == CoffeeType.CAPPUCCINO)
            return new Cappuccino();
        else if(coffeeType == CoffeeType.ESPRESSO)
            return new Espresso();
        else if(coffeeType == CoffeeType.LATTE)
            return new Latte();

        return null;
    }
}
