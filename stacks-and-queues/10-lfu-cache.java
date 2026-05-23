/*
 * LFU Cache
 *
 * Heap approach is O(n) due to arbitrary removal. O(1): freqMap of
 * freq -> LinkedHashSet<key> (LRU order within bucket) + store map +
 * explicit minFreq. On access, move key to next freq bucket; increment
 * minFreq only if old bucket emptied. Reset minFreq=1 on new insert.
 */
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LfuCache {
    private final int capacity;
    private int minFreq;
    private final Map<Integer, Integer> store = new HashMap<>();      // key -> val
    private final Map<Integer, Integer> freq = new HashMap<>();       // key -> freq
    private final Map<Integer, LinkedHashSet<Integer>> freqMap = new HashMap<>(); // freq -> keys (LRU order)

    public LfuCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!store.containsKey(key)) return -1;
        incrementFreq(key);
        return store.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;
        if (store.containsKey(key)) {
            store.put(key, value);
            incrementFreq(key);
        } else {
            if (store.size() == capacity) {
                evict();
            }
            store.put(key, value);
            freq.put(key, 1);
            freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            minFreq = 1;
        }
    }

    private void incrementFreq(int key) {
        int f = freq.get(key);
        freq.put(key, f + 1);
        freqMap.get(f).remove(key);
        if (freqMap.get(f).isEmpty()) {
            freqMap.remove(f);
            if (minFreq == f) minFreq++;
        }
        freqMap.computeIfAbsent(f + 1, k -> new LinkedHashSet<>()).add(key);
    }

    private void evict() {
        LinkedHashSet<Integer> bucket = freqMap.get(minFreq);
        int lruKey = bucket.iterator().next();
        bucket.remove(lruKey);
        if (bucket.isEmpty()) freqMap.remove(minFreq);
        store.remove(lruKey);
        freq.remove(lruKey);
    }
}
