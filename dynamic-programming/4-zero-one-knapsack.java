/*
 * 0-1 Knapsack
 * dp[w][i] = max value using items 0..i with capacity w.
 * If wt[i] <= w: max of (include: dp[w-wt[i]][i-1] + val[i], exclude: dp[w][i-1]).
 * Else: carry forward dp[w][i-1].
 * get() helper returns 0 for negative indices, avoiding a sentinel row/col.
 */

class ZeroOneKnapsack {
    public int knapsack(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[W + 1][n];

        for (int w = 0; w <= W; w++) {
            for (int i = 0; i < n; i++) {
                if (wt[i] <= w) {
                    dp[w][i] = Math.max(
                        get(dp, w - wt[i], i - 1) + val[i],
                        get(dp, w, i - 1)
                    );
                } else {
                    dp[w][i] = get(dp, w, i - 1);
                }
            }
        }

        return dp[W][n - 1];
    }

    private int get(int[][] dp, int i, int j) {
        if (i < 0 || j < 0) return 0;
        return dp[i][j];
    }
}
