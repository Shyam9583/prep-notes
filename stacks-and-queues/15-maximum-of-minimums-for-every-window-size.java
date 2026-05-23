/*
 * Maximum of Minimums for Every Window Size
 *
 * O(n^2): for each window size k, run a monotonic-increasing deque over the
 * array to find the min of each window, track the max. O(n) alternative exists
 * via PSE/NSE but is non-obvious.
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumOfMinimumsForEveryWindowSize {
    public int[] maxOfMins(int[] arr) {
        int n = arr.length;
        int[] result = new int[n + 1]; // result[k] = max of minimums for window size k

        for (int k = 1; k <= n; k++) {
            Deque<Integer> dq = new ArrayDeque<>(); // monotonic increasing, stores indices
            int maxMin = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                // maintain increasing deque
                while (!dq.isEmpty() && arr[dq.peekLast()] >= arr[i]) {
                    dq.pollLast();
                }
                dq.addLast(i);

                // remove front if outside window
                if (dq.peekFirst() <= i - k) {
                    dq.pollFirst();
                }

                // window fully formed
                if (i >= k - 1) {
                    maxMin = Math.max(maxMin, arr[dq.peekFirst()]);
                }
            }
            result[k] = maxMin;
        }

        return result;
    }
}
