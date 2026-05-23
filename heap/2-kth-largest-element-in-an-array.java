/*
 * Kth Largest Element in an Array
 *
 * Min-heap of size k: invariant is the heap holds the k largest elements seen
 * so far. For each num, add if size < k; else if num > peek(), poll then add.
 * Root is the answer.
 */

import java.util.PriorityQueue;

class KthLargestElementInAnArray {
    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (minHeap.size() < k) minHeap.add(num);
            else if (num > minHeap.peek()) { minHeap.poll(); minHeap.add(num); }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));       // 5
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4)); // 4
    }
}
