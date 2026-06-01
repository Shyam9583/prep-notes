/*
 * Super Egg Drop
 * Invert the problem: dp[t][e] = max floors checkable in t trials with e eggs.
 * Recurrence: dp[t][e] = dp[t-1][e-1] + 1 + dp[t-1][e]
 * (egg breaks → check below, egg survives → check above).
 * Increment t until dp[t][k] >= n. Answer is t.
 */

class SuperEggDrop {
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];

        int t = 0;
        while (dp[t][k] < n) {
            t++;
            for (int eggs = 1; eggs <= k; eggs++) {
                dp[t][eggs] = dp[t - 1][eggs - 1] + 1 + dp[t - 1][eggs];
            }
        }

        return t;
    }
}
