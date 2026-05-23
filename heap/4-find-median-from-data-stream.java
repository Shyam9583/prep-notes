/*
 * Find Median from Data Stream
 *
 * Two heaps: left max-heap (smaller half), right min-heap (larger half). On
 * insert, route to left if num < left.peek(), else right; then rebalance so
 * sizes differ by at most 1. Median is average of both peaks (even) or peak
 * of larger heap (odd).
 */

import java.util.Collections;
import java.util.PriorityQueue;

class FindMedianFromDataStream {
    private final PriorityQueue<Integer> left  = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
    private final PriorityQueue<Integer> right = new PriorityQueue<>();                             // min-heap

    void addNum(int num) {
        if (left.isEmpty() || num < left.peek()) left.add(num);
        else right.add(num);

        // Rebalance: sizes can differ by at most 1
        if (left.size() > right.size() + 1) right.add(left.poll());
        else if (right.size() > left.size()) left.add(right.poll());
    }

    double findMedian() {
        if (left.size() == right.size()) return (left.peek() + right.peek()) / 2.0;
        return left.peek(); // left always holds the extra element when odd
    }

    public static void main(String[] args) {
        FindMedianFromDataStream mf = new FindMedianFromDataStream();
        mf.addNum(1); mf.addNum(2);
        System.out.println(mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println(mf.findMedian()); // 2.0
    }
}
