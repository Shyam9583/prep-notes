/*
 * DP: paths at `[i][j]` = right + down. Space-optimised to 1D column: fill
 * bottom-up, each cell = `currCol[i+1] + nextCol[i]`. Last row is always 1
 * (only one way: go all right). Roll columns right to left.
 */
class GridUniquePaths {
    public int uniquePaths(int m, int n) {
        int[] col = new int[m];
        java.util.Arrays.fill(col, 1);
        for (int j = n - 2; j >= 0; j--) {
            for (int i = m - 2; i >= 0; i--) {
                col[i] += col[i + 1];
            }
        }
        return col[0];
    }
}
