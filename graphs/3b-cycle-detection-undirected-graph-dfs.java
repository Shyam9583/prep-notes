/*
DFS: pass prev and curr, skip edge back to prev; if curr already visited, cycle
found. Loop over all nodes for disconnected components.
*/
import java.util.*;

class CycleDetectionUndirectedGraphDfs {
    public boolean isCycle(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < V; i++) {
            if (visited.contains(i)) continue;
            if (hasCycle(-1, i, adj, visited)) return true;
        }
        return false;
    }

    private boolean hasCycle(int prev, int curr, List<List<Integer>> adj, Set<Integer> visited) {
        if (visited.contains(curr)) return true;
        visited.add(curr);
        for (int next : adj.get(curr)) {
            if (next == prev) continue;
            if (hasCycle(curr, next, adj, visited)) return true;
        }
        return false;
    }
}
