/*
 * M-Coloring Problem
 *
 * Backtracking on node index 0..v-1: try colors 1..m, check isSafe (no
 * neighbor already has that color), recurse on node+1, reset to 0 on
 * failure. Use adjacency matrix for O(1) neighbor lookup. Base case node == v
 * means all nodes colored successfully.
 */

class MColoringProblem {
    public static boolean graphColoring(boolean[][] graph, int m, int v) {
        int[] color = new int[v];
        return backtrack(graph, m, v, color, 0);
    }

    private static boolean backtrack(boolean[][] graph, int m, int v, int[] color, int node) {
        if (node == v) return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(graph, color, node, c)) {
                color[node] = c;
                if (backtrack(graph, m, v, color, node + 1)) return true;
                color[node] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(boolean[][] graph, int[] color, int node, int c) {
        for (int neighbor = 0; neighbor < graph.length; neighbor++) {
            if (graph[node][neighbor] && color[neighbor] == c) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // 4-node graph: 0-1, 1-2, 2-3, 3-0, 0-2
        boolean[][] graph = {
            {false, true,  true,  true},
            {true,  false, true,  false},
            {true,  true,  false, true},
            {true,  false, true,  false}
        };
        System.out.println(graphColoring(graph, 3, 4)); // true
        System.out.println(graphColoring(graph, 2, 4)); // false
    }
}
