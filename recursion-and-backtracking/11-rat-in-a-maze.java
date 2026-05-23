/*
 * Rat in a Maze
 *
 * Backtracking: try all 4 directions (D/L/R/U) from each cell; mark
 * visited[r][c]=true before recursing, reset to false after the loop so
 * other paths can reuse the cell. Base case: r==n-1 && c==n-1 -> append path.
 * Sort directions alphabetically to get lexicographic output.
 */

import java.util.ArrayList;
import java.util.List;

class RatInAMaze {
    // Directions in alphabetical order: D, L, R, U
    private static final int[] DR = {1, 0, 0, -1};
    private static final int[] DC = {0, -1, 1, 0};
    private static final char[] DIR = {'D', 'L', 'R', 'U'};

    public static List<String> findPath(int[][] maze) {
        int n = maze.length;
        List<String> result = new ArrayList<>();
        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0) return result;
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        backtrack(maze, n, 0, 0, new StringBuilder(), visited, result);
        return result;
    }

    private static void backtrack(int[][] maze, int n, int r, int c,
                                   StringBuilder path, boolean[][] visited,
                                   List<String> result) {
        if (r == n - 1 && c == n - 1) {
            result.add(path.toString());
            return;
        }
        for (int d = 0; d < 4; d++) {
            int nr = r + DR[d];
            int nc = c + DC[d];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if (maze[nr][nc] == 0 || visited[nr][nc]) continue;

            visited[nr][nc] = true;
            path.append(DIR[d]);
            backtrack(maze, n, nr, nc, path, visited, result);
            path.deleteCharAt(path.length() - 1);
            visited[nr][nc] = false;
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
        };
        System.out.println(findPath(maze)); // [DDRDRR, DRDDRR]
    }
}
