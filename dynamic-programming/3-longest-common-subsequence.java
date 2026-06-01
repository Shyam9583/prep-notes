/*
 * Longest Common Subsequence
 * 2D DP space-optimised to two 1D arrays. Match: curr[j] = prev[j-1] + 1.
 * Mismatch: curr[j] = max(prev[j], curr[j-1]) (best of skip-in-s1 or skip-in-s2).
 * Answer is prev[n] after all rows. 1-indexed arrays avoid bounds-check helpers.
 */

class LongestCommonSubsequence {
    static int lcs(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }

        return prev[n];
    }
}
