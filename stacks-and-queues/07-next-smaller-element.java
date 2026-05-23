/*
 * Next Smaller Element
 *
 * Monotonic increasing stack (stores indices). While arr[stack.top] > curr,
 * pop and set result at that index to curr. Unpopped elements default to -1.
 */
import java.util.Arrays;
import java.util.Stack;

public class NextSmallerElement {
    public int[] nextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>(); // monotonic increasing, stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return result;
    }
}
