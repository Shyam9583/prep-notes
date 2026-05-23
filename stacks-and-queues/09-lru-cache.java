/*
 * LRU Cache
 *
 * HashMap<key, Node> + doubly linked list with sentinel head/tail.
 * MRU end is tail.prev, LRU end is head.next.
 * get/put both call bringToFront: remove node, re-add before tail.
 * Evict head.next when at capacity. Node stores key so eviction can clean
 * up the map.
 * Shortcut: LinkedHashMap(cap, 0.75f, true) + override removeEldestEntry.
 */
import java.util.HashMap;
import java.util.Map;

public class LruCache {
    private static class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) { this.key = key; this.val = val; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0); // LRU sentinel
    private final Node tail = new Node(0, 0); // MRU sentinel

    public LruCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        bringToFront(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            bringToFront(node);
        } else {
            if (map.size() == capacity) {
                Node lru = head.next;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addBeforeTail(newNode);
            map.put(key, newNode);
        }
    }

    private void bringToFront(Node node) {
        remove(node);
        addBeforeTail(node);
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addBeforeTail(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }
}
