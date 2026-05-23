/*
 * Use first row & col as markers. Track first-col separately (`firstColZero` flag)
 * since `matrix[0][0]` is shared. Pass 1: mark rows/cols. Pass 2: zero out inner
 * cells. Pass 3: handle first row, then first col last.
 */
class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstColZero = false;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) firstColZero = true;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) matrix[0][j] = 0;
        }

        if (firstColZero) {
            for (int i = 0; i < m; i++) matrix[i][0] = 0;
        }
    }
}
