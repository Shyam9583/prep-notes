/*
 * Maximum Sum Combination
 *
 * Sort both arrays; max-heap seeded with (a[n-1]+b[n-1], n-1, n-1). Each poll
 * yields current best sum; push (i-1,j) and (i,j-1) if not visited.
 * HashSet<List<Integer>> tracks seen index pairs to avoid duplicates.
 */

import java.util.*;

class MaximumSumCombination {
    static List<Integer> maxSumCombination(int[] a, int[] b, int k) {
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length;

        // max-heap on sum value
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        Set<List<Integer>> visited = new HashSet<>();

        maxHeap.offer(new int[]{a[n-1] + b[n-1], n-1, n-1});
        visited.add(Arrays.asList(n-1, n-1));

        List<Integer> result = new ArrayList<>();
        while (result.size() < k) {
            int[] curr = maxHeap.poll();
            result.add(curr[0]);
            int i = curr[1], j = curr[2];

            if (i - 1 >= 0 && visited.add(Arrays.asList(i-1, j))) {
                maxHeap.offer(new int[]{a[i-1] + b[j], i-1, j});
            }
            if (j - 1 >= 0 && visited.add(Arrays.asList(i, j-1))) {
                maxHeap.offer(new int[]{a[i] + b[j-1], i, j-1});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxSumCombination(new int[]{1,4,2,3}, new int[]{2,5,1,6}, 4));
        // [10, 9, 9, 8]
    }
}
