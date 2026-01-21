package vendingmachine.entities;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Item> itemMap;
    private Map<String, Integer> quantityMap;

    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<String, Item> itemMap) {
        this.itemMap = itemMap;
    }

    public Map<String, Integer> getQuantityMap() {
        return quantityMap;
    }

    public void setQuantityMap(Map<String, Integer> quantityMap) {
        this.quantityMap = quantityMap;
    }

    public Inventory() {
        itemMap = new HashMap<>();
        quantityMap = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        itemMap.put(item.getItemId(), item);
        quantityMap.put(item.getItemId(), quantity);
    }

    public void reduceStock(String itemId) {
        quantityMap.put(itemId, quantityMap.get(itemId) - 1);
    }

    public Item getItem(String itemId) {
        return itemMap.get(itemId);
    }

    public boolean isItemAvailable(String itemId) {
        return itemMap.get(itemId) != null;
    }

    public boolean isQuantityAvailable(String itemId) {
        return quantityMap.get(itemId) > 0;
    }

}
