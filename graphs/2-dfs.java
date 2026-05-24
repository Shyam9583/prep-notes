/*
Recursive traversal with a visited[] boolean array. Mark visited before recursing
to handle cycles. Iterate over adj.get(curr) (adjacency list) for each neighbor.
*/
import java.util.ArrayList;

class Dfs {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        dfs(0, visited, res, adj);
        return res;
    }

    private void dfs(int curr, boolean[] visited, ArrayList<Integer> res, ArrayList<ArrayList<Integer>> adj) {
        if (visited[curr]) return;
        visited[curr] = true;
        res.add(curr);
        for (int next : adj.get(curr)) {
            dfs(next, visited, res, adj);
        }
    }
}
