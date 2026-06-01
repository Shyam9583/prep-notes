/*
 * Palindrome Partitioning (DP)
 * Precompute dp[i][j] bottom-up: s[i]==s[j] && (size<3 || dp[i+1][j-1]).
 * Backtrack: at start, try every end >= start; if dp[start][end], recurse
 * from end+1. Precomputing avoids repeated O(n) palindrome checks during backtracking.
 */
import java.util.*;

class PalindromePartitioningDP {
    public List<List<String>> partition(String s) {
        boolean[][] dp = createCheck(s);
        List<List<String>> res = new ArrayList<>();
        generate(res, new ArrayList<>(), 0, s, dp);
        return res;
    }

    private void generate(List<List<String>> res, List<String> acc, int start, String s, boolean[][] check) {
        if (start == s.length()) {
            res.add(new ArrayList<>(acc));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (!check[start][end]) continue;
            acc.add(s.substring(start, end + 1));
            generate(res, acc, end + 1, s, check);
            acc.remove(acc.size() - 1);
        }
    }

    private boolean[][] createCheck(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                int size = j - i;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (size < 3 || dp[i + 1][j - 1]);
            }
        }
        return dp;
    }
}
