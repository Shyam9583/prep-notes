/*
BFS 2-coloring: assign alternating colors to neighbors; if a neighbor already
has the same color, not bipartite. Use a colors[] array (default -1) and iterate
over graph[curr] (the adjacency list), not all nodes.
*/
import java.util.*;

class BipartiteGraphCheckBfs {
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] != -1) continue;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            colors[i] = 0;
            while (!q.isEmpty()) {
                int curr = q.remove();
                for (int next : graph[curr]) {
                    if (colors[next] == -1) {
                        colors[next] = 1 - colors[curr];
                        q.add(next);
                    } else if (colors[next] == colors[curr]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
