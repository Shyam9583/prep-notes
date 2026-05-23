/*
 * Sliding Window Maximum
 *
 * Monotonic decreasing deque (stores indices). For each r: evict from back
 * while nums[back] < nums[r], then evict front if front < l. Front is always
 * the max of the current window.
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices, decreasing by value

        for (int r = 0; r < n; r++) {
            // remove from back while back element is smaller than current
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[r]) {
                dq.pollLast();
            }
            dq.addLast(r);

            int l = r - k + 1;
            // remove front if it's outside the window
            if (dq.peekFirst() < l) {
                dq.pollFirst();
            }

            // window is fully formed
            if (r >= k - 1) {
                result[l] = nums[dq.peekFirst()];
            }
        }
        return result;
    }
}
