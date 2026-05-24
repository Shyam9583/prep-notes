/*
DFS: recurse into all unvisited neighbors first, then prepend curr to result
(res.add(0, curr)). Nodes finish in reverse topological order — prepending builds
the correct order without a separate reverse step.
*/
import java.util.*;

class TopologicalSortDfs {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);

        Set<Integer> visited = new HashSet<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) dfs(i, adj, visited, res);
        return res;
    }

    private void dfs(int curr, List<List<Integer>> adj, Set<Integer> visited, ArrayList<Integer> res) {
        if (visited.contains(curr)) return;
        visited.add(curr);
        for (int next : adj.get(curr)) dfs(next, adj, visited, res);
        res.add(0, curr);
    }
}
