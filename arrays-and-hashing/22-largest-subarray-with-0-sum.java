/*
 * Prefix sum + HashMap. Subarray `[l+1..r]` sums to k iff
 * `prefix[r] - prefix[l] == k`. Store earliest index of each prefix sum
 * (`putIfAbsent`); lookup `prefix[r] - k` to find longest span.
 */
import java.util.HashMap;
import java.util.Map;

class LargestSubarrayWith0Sum {
    public int maxLen(int[] arr, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefix = 0, maxLen = 0;
        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            if (map.containsKey(prefix)) {
                maxLen = Math.max(maxLen, i - map.get(prefix));
            } else {
                map.put(prefix, i);
            }
        }
        return maxLen;
    }
}
