/*
 * Prim's Algorithm (MST)
 * Min-heap of {weight, node}. Start from node 0. On each poll, if node already
 * in MST skip (lazy deletion). Otherwise mark it in MST, add edge weight to
 * total, and enqueue all non-MST neighbors. Greedy: always extends the tree via
 * the cheapest available crossing edge. O((V + E) log V).
 */
import java.util.*;

class PrimsAlgorithmMST {
    public int primsMST(int V, int[][] edges) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], k -> new ArrayList<>()).add(new int[]{e[1], e[2]});
            graph.computeIfAbsent(e[1], k -> new ArrayList<>()).add(new int[]{e[0], e[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0}); // {weight, node}

        boolean[] inMST = new boolean[V];
        int total = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int w = curr[0], u = curr[1];

            if (inMST[u]) continue;
            inMST[u] = true;
            total += w;

            for (int[] neighbor : graph.getOrDefault(u, Collections.emptyList())) {
                int v = neighbor[0], weight = neighbor[1];
                if (!inMST[v]) {
                    pq.add(new int[]{weight, v});
                }
            }
        }

        return total;
    }
}
