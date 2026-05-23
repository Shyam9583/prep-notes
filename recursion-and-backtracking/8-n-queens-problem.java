/*
 * N-Queens Problem
 *
 * Place one queen per row; track col[j], leftDia[i-j+n-1], rightDia[i+j] to
 * reject conflicts in O(1). No need for a row array — row index is implicit
 * in recursion depth.
 */

import java.util.ArrayList;
import java.util.List;

class NQueensProblem {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[] col = new boolean[n];
        boolean[] leftDia = new boolean[2 * n - 1];   // indexed by i - j + n - 1
        boolean[] rightDia = new boolean[2 * n - 1];  // indexed by i + j
        char[][] board = new char[n][n];
        for (char[] row : board) java.util.Arrays.fill(row, '.');

        backtrack(board, 0, n, col, leftDia, rightDia, result);
        return result;
    }

    private static void backtrack(char[][] board, int row, int n,
                                   boolean[] col, boolean[] leftDia, boolean[] rightDia,
                                   List<List<String>> result) {
        if (row == n) {
            List<String> snapshot = new ArrayList<>();
            for (char[] r : board) snapshot.add(new String(r));
            result.add(snapshot);
            return;
        }
        for (int c = 0; c < n; c++) {
            int ld = row - c + n - 1;
            int rd = row + c;
            if (col[c] || leftDia[ld] || rightDia[rd]) continue;

            board[row][c] = 'Q';
            col[c] = leftDia[ld] = rightDia[rd] = true;
            backtrack(board, row + 1, n, col, leftDia, rightDia, result);
            board[row][c] = '.';
            col[c] = leftDia[ld] = rightDia[rd] = false;
        }
    }

    public static void main(String[] args) {
        List<List<String>> solutions = solveNQueens(4);
        for (List<String> sol : solutions) {
            sol.forEach(System.out::println);
            System.out.println();
        }
    }
}
