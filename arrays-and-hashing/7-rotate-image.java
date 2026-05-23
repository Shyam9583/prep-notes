/*
 * 90° clockwise = transpose + reverse each row. Transpose swaps `[i][j]` with
 * `[j][i]` (upper triangle only, start `j=i` to avoid double-swap).
 * Anti-clockwise = reverse each row + transpose.
 */
class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        for (int[] row : matrix) {
            int l = 0, r = n - 1;
            while (l < r) {
                int t = row[l]; row[l] = row[r]; row[r] = t;
                l++; r--;
            }
        }
    }
}
