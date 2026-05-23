/*
 * Minimum Number of Coins (LC "Coin Change" variant)
 *
 * DP: dp[i] = min coins for amount i. For each amount, try every coin: skip if
 * i < c or dp[i-c] == -1 (unreachable). dp[0] = 0 is the base; unreachable
 * amounts stay -1. Note: this is the LC "Coin Change" variant (unlimited coins,
 * minimise count) — not the greedy "minimum coins with given denominations".
 */

import java.util.Arrays;

class MinimumNumberOfCoins {
    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i < c || dp[i - c] == -1) continue;
                if (dp[i] == -1 || dp[i - c] + 1 < dp[i]) {
                    dp[i] = dp[i - c] + 1;
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 5, 6, 9}, 11)); // 2
        System.out.println(coinChange(new int[]{2}, 3));            // -1
    }
}
