package lrucache;

import lrucache.entities.LRUCache;

public class Main {
    public static void main(String[] args)
    {

        System.out.println("===== Test 1: Basic Put/Get =====");
        LRUCache<String, Integer> cache1 = new LRUCache<>(2);
        cache1.put("A", 1);
        cache1.put("B", 2);
        System.out.println(cache1.get("A")); // Expected: 1


        System.out.println("\n===== Test 2: Eviction Order =====");
        LRUCache<String, Integer> cache2 = new LRUCache<>(2);
        cache2.put("A", 1);
        cache2.put("B", 2);
        cache2.put("C", 3); // evicts A
        System.out.println(cache2.get("A")); // Expected: null
        System.out.println(cache2.get("B")); // Expected: 2
        System.out.println(cache2.get("C")); // Expected: 3


        System.out.println("\n===== Test 3: Access Updates LRU =====");
        LRUCache<String, Integer> cache3 = new LRUCache<>(2);
        cache3.put("A", 1);
        cache3.put("B", 2);
        cache3.get("A");     // A becomes MRU
        cache3.put("C", 3);  // evicts B
        System.out.println(cache3.get("B")); // Expected: null
        System.out.println(cache3.get("A")); // Expected: 1
        System.out.println(cache3.get("C")); // Expected: 3


        System.out.println("\n===== Test 4: Update Existing Key =====");
        LRUCache<String, Integer> cache4 = new LRUCache<>(2);
        cache4.put("A", 1);
        cache4.put("A", 10); // update
        System.out.println(cache4.get("A")); // Expected: 10


        System.out.println("\n===== Test 5: Complex Sequence =====");
        LRUCache<String, Integer> cache5 = new LRUCache<>(2);
        cache5.put("A", 1);
        cache5.put("B", 2);
        System.out.println(cache5.get("A")); // 1
        cache5.put("C", 3); // evicts B
        System.out.println(cache5.get("B")); // null
        cache5.put("D", 4); // evicts A
        System.out.println(cache5.get("A")); // null
        System.out.println(cache5.get("C")); // 3
        System.out.println(cache5.get("D")); // 4


        System.out.println("\n===== Test 6: Repeated Access =====");
        LRUCache<String, Integer> cache6 = new LRUCache<>(2);
        cache6.put("A", 1);
        cache6.put("B", 2);
        cache6.get("A");
        cache6.get("A");
        cache6.get("A");
        cache6.put("C", 3); // evicts B
        System.out.println(cache6.get("B")); // Expected: null


        System.out.println("\n===== Test 7: Remove API =====");
        LRUCache<String, Integer> cache7 = new LRUCache<>(2);
        cache7.put("A", 1);
        cache7.put("B", 2);
        cache7.remove("A");
        System.out.println(cache7.get("A")); // Expected: null
        System.out.println(cache7.get("B")); // Expected: 2


        System.out.println("\n===== Test 8: Remove Non-Existing =====");
        LRUCache<String, Integer> cache8 = new LRUCache<>(2);
        cache8.put("A", 1);
        cache8.remove("Z"); // should not crash
        System.out.println(cache8.get("A")); // Expected: 1


        System.out.println("\n===== Test 9: Capacity = 1 =====");
        LRUCache<String, Integer> cache9 = new LRUCache<>(1);
        cache9.put("A", 1);
        cache9.put("B", 2); // evicts A
        System.out.println(cache9.get("A")); // Expected: null
        System.out.println(cache9.get("B")); // Expected: 2


        System.out.println("\n===== Test 10: Order Validation =====");
        LRUCache<String, Integer> cache10 = new LRUCache<>(3);
        cache10.put("A", 1);
        cache10.put("B", 2);
        cache10.put("C", 3);
        cache10.get("B");
        cache10.put("D", 4); // evicts A
        System.out.println(cache10.get("A")); // Expected: null
        System.out.println(cache10.get("B")); // Expected: 2
        System.out.println(cache10.get("C")); // Expected: 3
        System.out.println(cache10.get("D")); // Expected: 4


        System.out.println("\n===== Test 11: Stress Test =====");
        LRUCache<String, Integer> cache11 = new LRUCache<>(1000);
        for (int i = 0; i < 1000; i++) {
            cache11.put("Key" + i, i * 10);
        }
        boolean passed = true;
        for (int i = 0; i < 1000; i++) {
            Integer val = cache11.get("Key" + i);
            if (val == null || val != i * 10) {
                passed = false;
                break;
            }
        }
        System.out.println("Stress Test Passed: " + passed);


        System.out.println("\n===== Test 12: Mixed Operations =====");
        LRUCache<String, Integer> cache12 = new LRUCache<>(2);
        cache12.put("X", 1);
        cache12.put("X", 2);
        System.out.println(cache12.get("X")); // Expected: 2
        cache12.put("Y", 1);
        cache12.put("Z", 1); // evicts X
        System.out.println(cache12.get("X")); // Expected: null
    }
}
