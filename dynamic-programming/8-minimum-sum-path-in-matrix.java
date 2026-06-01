/*
 * Minimum Sum Path in Matrix
 * Fill in-place bottom-up: seed last row (right-to-left) and last col
 * (bottom-to-top) with running sums. Then for each inner cell,
 * grid[i][j] += min(grid[i+1][j], grid[i][j+1]). Answer at grid[0][0].
 * Backtrack the path: from (0,0), always step toward the smaller neighbor
 * (down vs right).
 * Count paths: same structure but dp[i][j] = dp[i+1][j] + dp[i][j+1];
 * seed last row and col with 1.
 */

class MinimumSumPathInMatrix {
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        for (int i = n - 2; i >= 0; i--) {
            grid[i][m - 1] += grid[i + 1][m - 1];
        }
        for (int j = m - 2; j >= 0; j--) {
            grid[n - 1][j] += grid[n - 1][j + 1];
        }
        for (int j = m - 2; j >= 0; j--) {
            for (int i = n - 2; i >= 0; i--) {
                grid[i][j] += Math.min(grid[i + 1][j], grid[i][j + 1]);
            }
        }

        return grid[0][0];
    }

    public List<int[]> minPath(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        minPathSum(grid); // fills grid with min costs
        List<int[]> path = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n - 1 || j < m - 1) {
            path.add(new int[]{i, j});
            if (i == n - 1) j++;
            else if (j == m - 1) i++;
            else if (grid[i + 1][j] < grid[i][j + 1]) i++;
            else j++;
        }
        path.add(new int[]{n - 1, m - 1});
        return path;
    }

    public int countPaths(int n, int m) {
        int[] dp = new int[m];
        Arrays.fill(dp, 1); // last row
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                dp[j] += dp[j + 1];
            }
        }
        return dp[0];
    }
}
