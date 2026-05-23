/*
 * Prefix XOR + HashMap (count of occurrences). Subarray `[l+1..r]` has XOR k
 * iff `prefix[r] ^ prefix[l] == k` → lookup `prefix[r] ^ k`. Seed map with
 * `{0:1}` to handle subarrays starting at index 0.
 */
import java.util.HashMap;
import java.util.Map;

class SubarraysWithXorK {
    public int subarraysWithXorK(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefix = 0, count = 0;
        for (int num : arr) {
            prefix ^= num;
            count += map.getOrDefault(prefix ^ k, 0);
            map.merge(prefix, 1, Integer::sum);
        }
        return count;
    }
}
