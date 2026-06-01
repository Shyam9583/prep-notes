/*
 * Kruskal's Algorithm (MST)
 * Sort edges by weight. Union-Find with path compression (halving:
 * parent[x] = parent[parent[x]]) and union by rank. For each edge, union(u, v)
 * — if they're in different components (different roots), merge and add weight.
 * Skip edges that would form a cycle (same root). O(E log E).
 */
import java.util.*;

class KruskalsAlgorithmMST {
    public int spanningTree(int V, int[][] edges) {
        int res = 0;

        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        for (int[] e : edges) {
            if (union(parent, rank, e[0], e[1])) {
                res += e[2];
            }
        }

        return res;
    }

    private boolean union(int[] parent, int[] rank, int a, int b) {
        int pa = find(parent, a);
        int pb = find(parent, b);

        if (pa == pb) return false;

        if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
            rank[pa]++;
        } else {
            parent[pa] = pb;
            rank[pb]++;
        }
        return true;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]]; // path halving
            x = parent[x];
        }
        return x;
    }
}
