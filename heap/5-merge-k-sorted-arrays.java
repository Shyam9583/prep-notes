/*
 * Merge k Sorted Arrays
 *
 * Min-heap of (row, col) comparator on mat[row][col]. Seed with first element
 * of each row. Each poll gives the current minimum; push (i, j+1) if within
 * bounds.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class MergeKSortedArrays {
    static List<Integer> mergeKArrays(int[][] mat, int k) {
        // min-heap: [value, row, col]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < k; i++) minHeap.offer(new int[]{mat[i][0], i, 0});

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            result.add(curr[0]);
            int row = curr[1], col = curr[2];
            if (col + 1 < mat[row].length) minHeap.offer(new int[]{mat[row][col+1], row, col+1});
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(mergeKArrays(mat, 3)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
