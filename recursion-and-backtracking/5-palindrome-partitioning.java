/*
 * Palindrome Partitioning
 *
 * Precompute dp[start][end] bottom-up: s[start]==s[end] && (size<3 ||
 * dp[start+1][end-1]). Then backtrack: at start, try every end >= start; if
 * dp[start][end], recurse from end+1. Base case start == len(s) adds the
 * partition. Precomputing avoids repeated O(n) palindrome checks during
 * backtracking.
 */

import java.util.ArrayList;
import java.util.List;

class PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // precompute palindrome table bottom-up (increasing length)
        for (int len = 1; len <= n; len++) {
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                dp[start][end] = s.charAt(start) == s.charAt(end)
                        && (len < 3 || dp[start + 1][end - 1]);
            }
        }

        List<List<String>> result = new ArrayList<>();
        backtrack(s, dp, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, boolean[][] dp, int start,
                                   List<String> current, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (dp[start][end]) {
                current.add(s.substring(start, end + 1));
                backtrack(s, dp, end + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(partition("aab")); // [[a, a, b], [aa, b]]
    }
}
