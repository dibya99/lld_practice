package lrucache.entities;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private int capacity;
    private Map<K, Node<K, V>> nodeMap;
    private DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        dll = new DoublyLinkedList<>();
    }

    public void remove(K key) {
        if (!nodeMap.containsKey(key))
            return;
        dll.remove(nodeMap.get(key));
        nodeMap.remove(key);
    }

    public V get(K key) {
        if (!nodeMap.containsKey(key))
            return null;
        V value = nodeMap.get(key).getValue();
        dll.moveToFront(nodeMap.get(key));
        return value;
    }

    public void put(K key, V value) {
        if (nodeMap.containsKey(key))
        {
            nodeMap.get(key).setValue(value);
            dll.moveToFront(nodeMap.get(key));
            return;
        }
        Node<K, V> node = new Node<>(key, value);
        nodeMap.put(key, node);
        dll.addFirst(node);

        if (nodeMap.size() > capacity) {
            Node<K, V> deletedNode = dll.removeLast();
            nodeMap.remove(deletedNode.getKey());
        }
    }

    public void printdll() {
        System.out.println(dll);
    }

    public void printMap() {
        System.out.println(nodeMap);
    }
}
