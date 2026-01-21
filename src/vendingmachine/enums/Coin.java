package vendingmachine.enums;

public enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private int value;

    public int getValue() {
        return value;
    }

    Coin(int v) {
        value = v;
    }
}
