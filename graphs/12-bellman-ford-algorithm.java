/*
 * Bellman-Ford Algorithm
 * Relax all edges V-1 times (shortest path has at most V-1 edges).
 * Skip edges from unreachable nodes (dist[u] == INF).
 * One more pass to detect negative cycles — if any edge still relaxes,
 * a negative cycle exists; return {-1}. Handles negative weights unlike Dijkstra.
 */
import java.util.*;

class BellmanFordAlgorithm {
    private static final int INFINITY = (int) 1e8;

    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, INFINITY);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (dist[u] != INFINITY && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dist[u] != INFINITY && dist[u] + w < dist[v]) {
                return new int[]{-1};
            }
        }

        return dist;
    }
}
