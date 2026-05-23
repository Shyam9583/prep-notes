/*
 * Largest Rectangle in Histogram
 *
 * Monotonic increasing stack (sentinel -1 at bottom). When heights[i] breaks
 * the order, pop the top as the height; the new top is the left boundary, i
 * is the right boundary, so width = i - st.peek() - 1. Drain remaining stack
 * at end using n as right boundary.
 */
import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1); // sentinel
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && heights[st.peek()] >= heights[i]) {
                int h = heights[st.pop()];
                int w = i - st.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            st.push(i);
        }

        // drain remaining elements
        while (st.peek() != -1) {
            int h = heights[st.pop()];
            int w = n - st.peek() - 1;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }
}
