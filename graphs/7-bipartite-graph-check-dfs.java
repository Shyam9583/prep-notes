/*
BFS/DFS 2-coloring: assign alternating colors to neighbors; if a neighbor already
has the same color, not bipartite. Use a colors[] array (default -1) and iterate
over graph[curr] (the adjacency list), not all nodes.
*/
import java.util.*;

class BipartiteGraphCheckDfs {
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] != -1) continue;
            if (!dfs(i, 0, graph, colors)) return false;
        }
        return true;
    }

    private boolean dfs(int curr, int color, int[][] graph, int[] colors) {
        colors[curr] = color;
        for (int next : graph[curr]) {
            if (colors[next] == -1) {
                if (!dfs(next, 1 - color, graph, colors)) return false;
            } else if (colors[next] == color) {
                return false;
            }
        }
        return true;
    }
}
