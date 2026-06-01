/*
 * Rod Cutting
 * Unbounded knapsack: outer loop over piece lengths i (1..n), inner loop over
 * rod lengths j (i..n) left-to-right (reuse allowed).
 * dp[j] = max(dp[j], price[i-1] + dp[j-i]).
 * Same structure as Coin Change II with price[i-1] as value and i as weight.
 */

class RodCutting {
    public int cutRod(int[] price) {
        int n = price.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int value = price[i - 1];
            for (int j = i; j <= n; j++) {
                dp[j] = Math.max(dp[j], value + dp[j - i]);
            }
        }

        return dp[n];
    }
}
