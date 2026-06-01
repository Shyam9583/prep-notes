/*
 * Maximum Number of Non-Overlapping Substrings
 * For each char, compute [first, last] interval; expand it by scanning inside
 * and pulling in the full range of any char whose occurrence extends beyond
 * current bounds (invalid if any char's first occurrence is before left).
 * Sort valid intervals by end; greedily pick non-overlapping ones
 * (same as N Meetings in One Room).
 */
import java.util.*;

class MaximumNumberOfNonOverlappingSubstrings {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        Map<Character, int[]> pos = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (!pos.containsKey(ch)) {
                pos.put(ch, new int[]{Integer.MAX_VALUE, 0});
            }
            pos.get(ch)[0] = Math.min(pos.get(ch)[0], i);
            pos.get(ch)[1] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char start = s.charAt(i);
            if (pos.get(start)[0] != i) {
                continue;
            }
            int left = i;
            int right = pos.get(start)[1];
            boolean valid = true;
            for (int j = left; j <= right; j++) {
                char curr = s.charAt(j);
                if (pos.get(curr)[0] < left) {
                    valid = false;
                    break;
                }
                right = Math.max(right, pos.get(curr)[1]);
            }
            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        intervals.sort((a, b) -> a[1] - b[1]);

        List<int[]> validRange = new ArrayList<>();
        for (int[] curr : intervals) {
            if (validRange.isEmpty()) {
                validRange.add(curr);
                continue;
            }
            int[] prev = validRange.get(validRange.size() - 1);
            if (prev[1] < curr[0]) {
                validRange.add(curr);
            }
        }

        List<String> res = new ArrayList<>();
        for (int[] range : validRange) {
            res.add(s.substring(range[0], range[1] + 1));
        }
        return res;
    }
}
