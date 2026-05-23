/*
 * Job Sequencing Problem
 *
 * Sort by profit descending. Use Union-Find to track the latest free slot <=
 * deadline: find(parent, d) returns the best available slot; after scheduling,
 * set parent[slot] = slot - 1 to redirect future queries past it.
 * O(n log n) vs O(n * maxDeadline) for the naive boolean-array approach.
 */

import java.util.Arrays;

class JobSequencingProblem {
    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static int[] jobSequencing(int[] deadline, int[] profit, int n) {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> profit[b] - profit[a]);

        int maxD = 0;
        for (int d : deadline) maxD = Math.max(maxD, d);

        parent = new int[maxD + 1];
        for (int i = 0; i <= maxD; i++) parent[i] = i;

        int jobs = 0, totalProfit = 0;
        for (int i : idx) {
            int slot = find(Math.min(deadline[i], maxD));
            if (slot > 0) {
                jobs++;
                totalProfit += profit[i];
                parent[slot] = slot - 1;
            }
        }
        return new int[]{jobs, totalProfit};
    }

    public static void main(String[] args) {
        int[] deadline = {2, 1, 2, 1, 3};
        int[] profit   = {100, 19, 27, 25, 15};
        int[] res = jobSequencing(deadline, profit, deadline.length);
        System.out.println(res[0] + " " + res[1]); // 2 127
    }
}
