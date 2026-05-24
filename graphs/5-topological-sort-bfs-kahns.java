/*
Compute indegree for all nodes; seed queue with all zero-indegree nodes. Each
dequeue adds node to topo order and decrements neighbors' indegrees — enqueue a
neighbor when its indegree hits 0. If result size < V, a cycle exists.
*/
import java.util.*;

class TopologicalSortBfsKahns {
    public int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++)
            for (int v : adj.get(u)) indegree[v]++;

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++)
            if (indegree[i] == 0) q.add(i);

        int[] topo = new int[V];
        int idx = 0;
        while (!q.isEmpty()) {
            int node = q.remove();
            topo[idx++] = node;
            for (int neighbour : adj.get(node))
                if (--indegree[neighbour] == 0) q.add(neighbour);
        }
        // if idx < V, graph has a cycle
        return topo;
    }
}
