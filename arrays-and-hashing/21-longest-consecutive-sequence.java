/*
 * Add all elements to a HashSet. Only start counting from sequence heads
 * (`item-1` not in set). Extend with `item+1` until chain breaks. O(n) —
 * each element is visited at most twice.
 */
import java.util.HashSet;
import java.util.Set;

class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int best = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curr = num, length = 1;
                while (set.contains(curr + 1)) { curr++; length++; }
                best = Math.max(best, length);
            }
        }
        return best;
    }
}
