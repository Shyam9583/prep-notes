/*
DFS with two sets: visited (fully explored) and path (current recursion stack).
If curr is in path, back-edge found → cycle. If already in visited, skip.
Backtrack by removing from path after recursing.
*/
import java.util.*;

class CycleDetectionDirectedGraphDfs {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] pre : prerequisites) adj.get(pre[1]).add(pre[0]);

        Set<Integer> visited = new HashSet<>();
        Set<Integer> path = new HashSet<>();
        for (int i = 0; i < numCourses; i++)
            if (hasCycle(i, visited, path, adj)) return false;
        return true;
    }

    private boolean hasCycle(int curr, Set<Integer> visited, Set<Integer> path, List<List<Integer>> adj) {
        if (path.contains(curr)) return true;
        if (visited.contains(curr)) return false;
        visited.add(curr);
        path.add(curr);
        for (int next : adj.get(curr))
            if (hasCycle(next, visited, path, adj)) return true;
        path.remove(curr);
        return false;
    }
}
