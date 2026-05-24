/*
Grid: DFS from each unvisited '1' cell, flood-fill all 4 neighbors, return true
only at the entry point to count.
Graph: count connected components — DFS from each unvisited node, increment
counter each time a new DFS starts.
*/
import java.util.*;

class NumberOfIslands {
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // Grid version
    public int numIslands(char[][] grid) {
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (dfs(grid, i, j, visited)) res++;
        return res;
    }

    private boolean dfs(char[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == '0' || visited[i][j])
            return false;
        visited[i][j] = true;
        for (int[] d : dir) dfs(grid, i + d[0], j + d[1], visited);
        return true;
    }

    // Graph version (count connected components)
    public int numIslandsGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        int count = 0;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsGraph(i, adj, visited);
                count++;
            }
        }
        return count;
    }

    private void dfsGraph(int curr, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[curr] = true;
        for (int next : adj.get(curr))
            if (!visited[next]) dfsGraph(next, adj, visited);
    }
}
