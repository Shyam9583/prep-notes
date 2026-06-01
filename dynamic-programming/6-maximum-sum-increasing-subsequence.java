/*
 * Maximum Sum Increasing Subsequence
 * LIS variant: dp[i] = max sum of increasing subsequence ending at i,
 * seeded with arr[i]. For each j < i where arr[j] < arr[i],
 * update dp[i] = max(dp[i], dp[j] + arr[i]). Track global max across all dp[i]. O(n²).
 */

class MaximumSumIncreasingSubsequence {
    public int maxSumIS(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];
        int res = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
