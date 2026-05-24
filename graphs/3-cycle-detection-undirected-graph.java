/*
BFS with {node, parent} pairs in the queue. If a visited neighbor is not the
parent, a cycle exists. Loop over all nodes to handle disconnected components.
*/
import java.util.*;

class CycleDetectionUndirectedGraph {
    boolean hasCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            if (!visited[i] && bfsCheck(i, adj, visited)) return true;
        return false;
    }

    boolean bfsCheck(int src, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        Queue<int[]> q = new LinkedList<>(); // {node, parent}
        q.add(new int[]{src, -1});
        visited[src] = true;
        while (!q.isEmpty()) {
            int[] curr = q.remove();
            int node = curr[0], parent = curr[1];
            for (int neighbour : adj.get(node)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    q.add(new int[]{neighbour, node});
                } else if (neighbour != parent) {
                    return true;
                }
            }
        }
        return false;
    }
}
