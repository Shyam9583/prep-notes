/*
 * Top K Frequent Elements
 *
 * Frequency map, then min-heap of size k keyed by frequency — same pattern as
 * Kth Largest but comparator is count.get(a) - count.get(b).
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class TopKFrequentElements {
    static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) count.merge(n, 1, Integer::sum);

        // min-heap on frequency
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> count.get(a) - count.get(b));

        for (int num : count.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) minHeap.poll();
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) result[i] = minHeap.poll();
        return result;
    }

    public static void main(String[] args) {
        int[] res = topKFrequent(new int[]{1,1,1,2,2,3}, 2);
        for (int r : res) System.out.print(r + " "); // 1 2
        System.out.println();
    }
}
