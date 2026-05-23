/*
 * Rotten Oranges
 *
 * Multi-source BFS from all initially-rotten cells simultaneously; track
 * nFresh and decrement on each spread; process level by level (snapshot
 * q.size() before inner loop) and increment time after each full level;
 * return -1 if nFresh > 0 at the end.
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class RottenOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        int nFresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) q.add(new int[]{r, c});
                else if (grid[r][c] == 1) nFresh++;
            }
        }

        if (nFresh == 0) return 0;

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                for (int[] d : dirs) {
                    int nr = cell[0] + d[0], nc = cell[1] + d[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        nFresh--;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            time++;
        }

        return nFresh > 0 ? -1 : time - 1;
    }
}
