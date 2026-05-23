/*
 * Sudoku Solver
 *
 * Backtracking: for each empty cell try digits 1-9, validate against
 * row/col/3x3 box before placing, recurse, undo on failure. Box index:
 * (r/3)*3 + c/3. Return true as soon as the board is fully filled and
 * propagate that true up immediately to stop further recursion.
 */

class SudokuSolver {
    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    private static boolean solve(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') continue;
                for (char d = '1'; d <= '9'; d++) {
                    if (isValid(board, r, c, d)) {
                        board[r][c] = d;
                        if (solve(board)) return true;
                        board[r][c] = '.';
                    }
                }
                return false;  // no digit worked — backtrack
            }
        }
        return true;  // all cells filled
    }

    private static boolean isValid(char[][] board, int r, int c, char d) {
        int box = (r / 3) * 3 + (c / 3);
        for (int k = 0; k < 9; k++) {
            if (board[r][k] == d) return false;           // same row
            if (board[k][c] == d) return false;           // same col
            int br = (box / 3) * 3 + k / 3;
            int bc = (box % 3) * 3 + k % 3;
            if (board[br][bc] == d) return false;         // same box
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
        for (char[] row : board) System.out.println(new String(row));
    }
}
