package vendingmachine;

import vendingmachine.entities.Inventory;
import vendingmachine.entities.Item;
import vendingmachine.entities.VendingMachine;
import vendingmachine.enums.Coin;

public class Client {
    public static void main(String[] args) {
//        testHappyPath();
//        testInvalidItem();
//        testOutOfStockItem();
//        testInsufficientBalance();
//        testDispenseWithoutSelectingItem();
//        testStateNotChangedOnFailure();
//        testStockNotReducedOnFailure();
        testMultipleCoinInsert();
        testOverPaying();
        testSelectItemTwice();
        testInsertCoinBeforeSelectingItem();
        testDispenseTwice();
        testQuantityDecrementOnSuccess();
        testBalanceResetAfterSuccess();
        testNewSelectionAfterFailure();
        testPartialThenCompletePayment();
    }

    static void testHappyPath() {
        System.out.println("Running testHappyPath");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();

        vm.selectItem("A1");
        vm.insertCoin(Coin.QUARTER);
        vm.dispense();

        System.out.println("State: " + vm.getVendingMachineState().getClass().getSimpleName());
        System.out.println("Balance: " + vm.getBalance());
        System.out.println("----------------------");
    }

    static void testInvalidItem() {
        System.out.println("Running testInvalidItem");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();

        try {
            vm.selectItem("Z9");
            System.out.println("❌ FAIL");
        } catch (Exception e) {
            System.out.println("✅ PASS: " + e.getClass().getSimpleName());
        }

        System.out.println("----------------------");
    }

    static void testOutOfStockItem() {
        System.out.println("Running testOutOfStockItem");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();

        try {
            vm.selectItem("B2"); // qty = 0
            System.out.println("❌ FAIL");
        } catch (Exception e) {
            System.out.println("✅ PASS: " + e.getClass().getSimpleName());
        }

        System.out.println("----------------------");
    }

    static void testInsufficientBalance() {
        System.out.println("Running testInsufficientBalance");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();

        vm.selectItem("A1");       // price = 25
        vm.insertCoin(Coin.DIME); // 10

        try {
            vm.dispense();
            System.out.println("❌ FAIL");
        } catch (Exception e) {
            System.out.println("✅ PASS: " + e.getClass().getSimpleName());
        }

        System.out.println("----------------------");
    }

    static void testDispenseWithoutSelectingItem() {
        System.out.println("Running testDispenseWithoutSelectingItem");
        resetAndSetup();
        try {
            VendingMachine vm = VendingMachine.getInstance();
            vm.insertCoin(Coin.QUARTER);
            vm.dispense();
            System.out.println("❌ FAIL");
        } catch (Exception e) {
            System.out.println("✅ PASS: " + e.getClass().getSimpleName());
        }

        System.out.println("----------------------");
    }

    static void testStateNotChangedOnFailure() {
        System.out.println("Running testStateNotChangedOnFailure");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();
        vm.selectItem("A1");

        String before = vm.getVendingMachineState().getClass().getSimpleName();

        try {
            vm.dispense(); // no money
        } catch (Exception ignored) {
        }

        String after = vm.getVendingMachineState().getClass().getSimpleName();

        System.out.println("Before: " + before);
        System.out.println("After: " + after);
        System.out.println("----------------------");
    }

    static void testStockNotReducedOnFailure() {
        System.out.println("Running testStockNotReducedOnFailure");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();

        int before = vm.getInventory().getQuantityMap().get("A1");

        vm.selectItem("A1");
        vm.insertCoin(Coin.PENNY); // insufficient

        try {
            vm.dispense();
        } catch (Exception ignored) {
        }

        int after = vm.getInventory().getQuantityMap().get("A1");

        System.out.println("Stock before: " + before);
        System.out.println("Stock after: " + after);
        System.out.println("----------------------");
    }


    static void resetAndSetup() {
        VendingMachine vm = VendingMachine.getInstance();
        vm.reset(); // resets state, balance, selected item
        setupInventory(vm);
    }

    static void setupInventory(VendingMachine vm) {
        Inventory inv = vm.getInventory();

        inv.addItem(new Item("A1", "Coke", 25), 5);
        inv.addItem(new Item("A2", "Pepsi", 30), 3);
        inv.addItem(new Item("B1", "Chips", 20), 10);
        inv.addItem(new Item("B2", "Chocolate", 15), 0); // out of stock
    }

    static void testMultipleCoinInsert() {
        System.out.println("Running testMultipleCoinInsert");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();
        vm.selectItem("A1");
        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.NICKEL);
        vm.insertCoin(Coin.DIME);
        vm.dispense();

        System.out.println("State: " + vm.getVendingMachineState().getClass().getSimpleName());
        System.out.println("----------------------");
    }

    // 9️⃣ Overpaying
    static void testOverPaying() {
        System.out.println("Running testOverPaying");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();
        vm.selectItem("A1");
        vm.insertCoin(Coin.QUARTER);
        vm.insertCoin(Coin.DIME);
        vm.dispense();

        System.out.println("Balance after dispense: " + vm.getBalance());
        System.out.println("----------------------");
    }

    // 🔟 Select item twice
    static void testSelectItemTwice() {
        System.out.println("Running testSelectItemTwice");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();
        vm.selectItem("A1");

        try {
            vm.selectItem("A2");
            System.out.println("❌ FAIL");
        } catch (Exception e) {
            System.out.println("✅ PASS: " + e.getClass().getSimpleName());
        }

        System.out.println("----------------------");
    }

    // 1️⃣1️⃣ Insert coin before selecting item
    static void testInsertCoinBeforeSelectingItem() {
        System.out.println("Running testInsertCoinBeforeSelectingItem");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();

        try {
            vm.insertCoin(Coin.QUARTER);
            System.out.println("❌ FAIL");
        } catch (Exception e) {
            System.out.println("✅ PASS: " + e.getClass().getSimpleName());
        }

        System.out.println("----------------------");
    }

    // 1️⃣2️⃣ Dispense twice
    static void testDispenseTwice() {
        System.out.println("Running testDispenseTwice");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();
        vm.selectItem("A1");
        vm.insertCoin(Coin.QUARTER);
        vm.dispense();

        try {
            vm.dispense();
            System.out.println("❌ FAIL");
        } catch (Exception e) {
            System.out.println("✅ PASS: " + e.getClass().getSimpleName());
        }

        System.out.println("----------------------");
    }

    // 1️⃣3️⃣ Quantity decreases on success
    static void testQuantityDecrementOnSuccess() {
        System.out.println("Running testQuantityDecrementOnSuccess");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();
        int before = vm.getInventory().getQuantityMap().get("A1");

        vm.selectItem("A1");
        vm.insertCoin(Coin.QUARTER);
        vm.dispense();

        int after = vm.getInventory().getQuantityMap().get("A1");

        System.out.println("Before: " + before);
        System.out.println("After: " + after);
        System.out.println("----------------------");
    }

    // 1️⃣4️⃣ Balance resets after success
    static void testBalanceResetAfterSuccess() {
        System.out.println("Running testBalanceResetAfterSuccess");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();
        vm.selectItem("A1");
        vm.insertCoin(Coin.QUARTER);
        vm.dispense();

        System.out.println("Balance: " + vm.getBalance());
        System.out.println("----------------------");
    }

    // 1️⃣5️⃣ New selection after failure
    static void testNewSelectionAfterFailure() {
        System.out.println("Running testNewSelectionAfterFailure");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();

        try {
            vm.selectItem("B2"); // out of stock
        } catch (Exception ignored) {
        }

        vm.selectItem("A1");
        vm.insertCoin(Coin.QUARTER);
        vm.dispense();

        System.out.println("State: " + vm.getVendingMachineState().getClass().getSimpleName());
        System.out.println("----------------------");
    }

    // 1️⃣6️⃣ Partial then complete payment
    static void testPartialThenCompletePayment() {
        System.out.println("Running testPartialThenCompletePayment");
        resetAndSetup();

        VendingMachine vm = VendingMachine.getInstance();
        vm.selectItem("A1");
        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.NICKEL);
        vm.dispense();

        System.out.println("State: " + vm.getVendingMachineState().getClass().getSimpleName());
        System.out.println("----------------------");
    }


}
