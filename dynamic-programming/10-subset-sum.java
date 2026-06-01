/*
 * Subset Sum
 * 0/1 knapsack on booleans. dp[i] = can we reach sum i.
 * Iterate each num; update dp right-to-left (i down to num) to avoid reusing
 * the same element. dp[i] |= dp[i-num]. Early exit if dp[sum] is true.
 * dp[0] = true base.
 */

class SubsetSum {
    static Boolean isSubsetSum(int arr[], int sum) {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : arr) {
            for (int i = sum; i >= num; i--) {
                dp[i] |= dp[i - num];
            }
            if (dp[sum]) return true;
        }

        return false;
    }
}
