/*
 * Word Break
 * dp[i] = can s[0..i] be segmented. For each end, scan backwards for a start
 * where dp[start] is true and s[start..end] is in the dictionary.
 * Break early on first match. dp[0] = true base.
 */
import java.util.*;

class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int end = 1; end <= n; end++) {
            for (int start = end - 1; start >= 0; start--) {
                if (words.contains(s.substring(start, end)) && dp[start]) {
                    dp[end] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
