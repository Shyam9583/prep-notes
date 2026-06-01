/*
 * Dijkstra's Algorithm
 * Min-heap of {dist, node}. Lazy deletion: skip a node if already settled
 * (dist[node] != MAX_VALUE). On first visit, set dist[curr] = dist and enqueue
 * all unvisited neighbors with updated cost. Works only on non-negative weights
 * — negative edges break the greedy settlement assumption.
 */
import java.util.*;

class DijkstrasAlgorithm {
    public int[] dijkstra(int V, int[][] edges, int src) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], k -> new HashMap<>()).put(e[1], e[2]);
            graph.computeIfAbsent(e[1], k -> new HashMap<>()).put(e[0], e[2]);
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.add(new int[]{0, src});

        int[] res = new int[V];
        Arrays.fill(res, Integer.MAX_VALUE);

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int dist = node[0], curr = node[1];

            if (res[curr] != Integer.MAX_VALUE) {
                continue;
            }
            res[curr] = dist;

            for (var e : graph.get(curr).entrySet()) {
                int next = e.getKey(), weight = e.getValue();
                // cast to long to avoid overflow wrapping to negative
                if (res[next] == Integer.MAX_VALUE && (long) dist + weight <= Integer.MAX_VALUE) {
                    q.add(new int[]{dist + weight, next});
                }
            }
        }

        return res;
    }
}
