/*
 * Next Greater Element
 *
 * Monotonic decreasing stack (stores indices). Iterate nums2; while stack top
 * < curr, pop and record curr as NGE in a map. Remaining stack elements get -1.
 * Query map for each nums1 element.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nge = new HashMap<>();
        Stack<Integer> stack = new Stack<>(); // monotonic decreasing

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                nge.put(stack.pop(), num);
            }
            stack.push(num);
        }
        while (!stack.isEmpty()) {
            nge.put(stack.pop(), -1);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nge.get(nums1[i]);
        }
        return result;
    }
}
