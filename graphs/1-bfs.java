/*
Iterative with a queue. Mark visited when enqueuing (not dequeuing) to avoid
duplicates. Seed the queue with node 0, then for each dequeued node add unvisited
neighbors.
*/
import java.util.*;

class Bfs {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        q.add(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            int node = q.remove();
            result.add(node);
            for (int neighbour : adj.get(node)) {
                if (!visited[neighbour]) {
                    q.add(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
        return result;
    }
}
