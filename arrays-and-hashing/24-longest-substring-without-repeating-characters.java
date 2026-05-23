/*
 * Sliding window + HashMap of last seen index. On duplicate, jump `l` to
 * `lastSeen[s[r]] + 1` — but only if that position is `>= l` (guard against
 * stale entries from before the current window).
 */
import java.util.HashMap;
import java.util.Map;

class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int l = 0, best = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= l) {
                l = lastSeen.get(c) + 1;
            }
            lastSeen.put(c, r);
            best = Math.max(best, r - l + 1);
        }
        return best;
    }
}
