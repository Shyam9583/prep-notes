/*
 * Coin Change II (Count Ways)
 * Unbounded knapsack for counts. dp[i] = number of ways to make amount i.
 * For each coin, iterate i from c to amount: dp[i] += dp[i-c].
 * Outer loop over coins (not amounts) ensures each combination is counted once
 * — avoids permutation duplicates. dp[0] = 1 base.
 */

class CoinChangeIICountWays {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c : coins) {
            for (int i = c; i <= amount; i++) {
                dp[i] += dp[i - c];
            }
        }

        return dp[amount];
    }
}
